package com.xdcplus.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.xdcplus.biz.common.config.AssemblyBuilder;
import com.xdcplus.biz.common.constants.ZtbConstant;
import com.xdcplus.biz.common.pojo.dto.*;
import com.xdcplus.biz.common.pojo.entity.BidSpecialistScore;
import com.xdcplus.biz.common.pojo.entity.BidVendorDetail;
import com.xdcplus.biz.common.pojo.query.BidSheetQuery;
import com.xdcplus.biz.common.pojo.vo.BidDetailVO;
import com.xdcplus.biz.common.pojo.vo.BidSpecialistVO;
import com.xdcplus.biz.common.pojo.vo.BidVendorVO;
import com.xdcplus.biz.generator.impl.BidSheetBaseServiceImpl;
import com.xdcplus.biz.mapper.BidSheetMapper;
import com.xdcplus.biz.common.pojo.entity.BidSheet;
import com.xdcplus.biz.common.pojo.vo.BidSheetVO;
import com.xdcplus.biz.remote.service.IeService;
import com.xdcplus.biz.remote.service.PermService;
import com.xdcplus.biz.service.*;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.page.PageUtils;
import com.xdcplus.ztb.common.remote.domain.workflow.dto.*;
import com.xdcplus.ztb.common.remote.domain.workflow.vo.ProcessConfigVO;
import com.xdcplus.ztb.common.remote.domain.workflow.vo.RequestVO;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.utils.PageableUtils;
import com.xdcplus.ztb.common.tool.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * bid招标单(BidSheet)表服务实现类
 *
 * @author makejava
 * @since 2021-08-12 14:45:42
 */
@Slf4j
@Service("bidSheetService")
public class BidSheetServiceImpl extends BidSheetBaseServiceImpl<BidSheet, BidSheetVO, BidSheet, BidSheetMapper> implements BidSheetService {

    @Autowired
    private IeService ieService;

    @Autowired
    private BidVendorService bidVendorService;

    @Autowired
    private BidVendorDetailService bidVendorDetailService;

    @Autowired
    private BidDetailService bidDetailService;

    @Autowired
    private PermService permService;

    @Autowired
    private BidSpecialistService bidSpecialistService;

    @Autowired
    private BidSpecialistScoreService bidSpecialistScoreService;

    @Override
    public BidSheetVO saveBidSheetReturnVO(BidSheetDTO bidSheetDTO) {
        BidSheet bidSheet = bidSheetMapper.selectById(bidSheetDTO.getId());
        if (ObjectUtil.isNotNull(bidSheet)) {
            log.error("saveBidSheet() The BidSheet already exists");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        bidSheet = new BidSheet();
        BeanUtil.copyProperties(bidSheetDTO, bidSheet);
        bidSheet.setCreatedTime(DateUtil.current());
        boolean result = this.save(bidSheet);
        BidSheetVO bidSheetVO = BeanUtil.copyProperties(bidSheet, BidSheetVO.class);
        if (result) {
            return bidSheetVO;
        } else {
            return null;
        }
    }

    @Override
    public BidSheetVO showBidSheetByRequestId(Long id) {
        BidSheetFilterDTO bidSheetFilterDTO = new BidSheetFilterDTO();
        bidSheetFilterDTO.setRequestId(id);
        List<BidSheetVO> bidSheetVOS = queryBidSheetVOList(bidSheetFilterDTO);
        if (CollectionUtil.isEmpty(bidSheetVOS)) {
            return null;
        }
        BidSheetVO bidSheetVO = bidSheetVOS.get(NumberConstant.ZERO);
        combineRequest(bidSheetVO);

        BidSpecialistFilterDTO bidSpecialistFilterDTO = new BidSpecialistFilterDTO();
        bidSpecialistFilterDTO.setBidSheetId(bidSheetVO.getId());
        bidSheetVO.setBidSpecialistVOS(bidSpecialistService.queryBidSpecialistVOList(bidSpecialistFilterDTO));

        return bidSheetVO;
    }

    @Override
    public PageVO<BidSheetVO> queryBidSheetWithRequest(BidSheetFilterDTO bidSheetFilterDTO) {

        //查询表单信息
        RequestFilterDTO requestFilterDTO = new RequestFilterDTO();
        requestFilterDTO.setKeyword(bidSheetFilterDTO.getKeyword());
        requestFilterDTO.setStatusId(bidSheetFilterDTO.getStatusId());
        requestFilterDTO.setCurrentPage(-1);

        PageVO<RequestVO> requestVOPageVO = ieService.findRequest(requestFilterDTO);
        List<RequestVO> requestVOS = requestVOPageVO.getRecords();

        if (CollectionUtil.isNotEmpty(requestVOS)) {
            List<Long> requestIds = requestVOS.stream().map(RequestVO::getId).collect(Collectors.toList());
            bidSheetFilterDTO.setRequestIds(requestIds);
        }

        List<BidSheet> bidSheetList = queryBidSheetList(bidSheetFilterDTO);

        //过滤
        List<BidSheetVO> bidSheetVOList = filterBidSheetList(bidSheetList, bidSheetFilterDTO.getUserId());
        if (CollectionUtil.isEmpty(bidSheetVOList)) {
            return null;
        }
        return PageUtils.getPageVO(bidSheetVOList, bidSheetFilterDTO.getCurrentPage(),
                bidSheetFilterDTO.getPageSize() == null ? 0 : bidSheetFilterDTO.getPageSize());

    }

    @Override
    public PageVO<BidSheetVO> queryBidSheetTemplate(BidSheetFilterDTO bidSheetFilterDTO) {
        PageVO<BidSheetVO> pageVO = new PageVO<>();

        if (bidSheetFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(bidSheetFilterDTO.getCurrentPage(), bidSheetFilterDTO.getPageSize(),
                    bidSheetFilterDTO.getOrderType(), bidSheetFilterDTO.getOrderField());
        }

        bidSheetFilterDTO.setDeleted(0);
        BidSheetQuery bidSheetQuery = BeanUtil.copyProperties(bidSheetFilterDTO, BidSheetQuery.class);
        List<BidSheet> bidSheetList = bidSheetMapper.queryBidSheetTemplate(bidSheetQuery);

        PageInfo<BidSheet> pageInfo = new PageInfo<>(bidSheetList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(bidSheetList));

        return pageVO;
    }

    @Override
    public int selectCountByProjectId(Long id) {
        return bidSheetMapper.selectCount(
                new QueryWrapper<BidSheet>().lambda().eq(BidSheet::getProjectSheetId, id).eq(BidSheet::getDeleted, 0));
    }

    @Override
    public void submitBeforeJudgeStatus(BidSheetDTO bidSheetDTO, RequestDTO requestDTO) {
        Circulation circulation = new Circulation();
        circulation.setUserId(bidSheetDTO.getUserId());
        requestDTO.setCirculation(circulation);

        //新增策略条件
        FormFlowStrategyDTO formFlowStrategyDTO = new FormFlowStrategyDTO();

        String json = JSONObject.toJSONString(bidSheetDTO);
        Map map = JSONObject.parseObject(json, Map.class);
        formFlowStrategyDTO.setStrategyConditions(map);

        formFlowStrategyDTO.setTypeId(bidSheetDTO.getTypeId());

        requestDTO.setStrategy(formFlowStrategyDTO);
    }

    @Override
    public void submitAfterJudgeStatus(BidSheetDTO bidSheetDTO) {
        //        BidSheetVO bidSheetVO = bidSheetService.queryBidSheetById(bidSheetDTO.getId());
//
//        RequestVO requestVO = ieService.findRequestById(bidSheetVO.getRequestId());
//
//        if (bidSheetVO.getApprovalProcess() != null && bidSheetVO.getApprovalProcess() != 0) {
//            RequestDTO requestDTO = new RequestDTO();
//            requestDTO.setRuleId(ZtbConstant.SHENGHE_RULE_ID);
//
//            StringBuffer sb = new StringBuffer(requestVO.getTitle());
//            sb.insert(requestVO.getTitle().indexOf("-"), "审批单");
//            requestDTO.setTitle(sb.toString());
//            requestDTO.setCreatedUser(bidSheetVO.getCreatedUser());
//            HashSet<Long> parentIds = new HashSet<>();
//            parentIds.add(bidSheetVO.getRequestId());
//            requestDTO.setParentIds(parentIds);
//
//            //新增策略条件
//            FormFlowStrategyDTO formFlowStrategyDTO = new FormFlowStrategyDTO();
//            formFlowStrategyDTO.setTypeId(ZtbConstant.SHENGHE_TYPE_ID);
//            requestDTO.setStrategy(formFlowStrategyDTO);
//
//            Circulation circulation = new Circulation();
//            circulation.setUserId(bidSheetDTO.getUserId());
//            requestDTO.setCirculation(circulation);
//
//            ieService.saveRequest(requestDTO);
//        }
    }

    @Override
    public ProcessTransforDTO approveBeforeJudgeStatus(RequestFlowDTO requestFlowDTO) {
        RequestVO requestVO = ieService.findRequestById(requestFlowDTO.getRequestId());
        long sheetId = requestFlowDTO.getId();

        //新增vendorDetail和专家评分
        if (requestVO.getStatus().getName().equals(ZtbConstant.DAIFABU)) {
            saveBidVendorDetailBySheetId(sheetId);
            saveBidSpecialistScoreBySheetId(sheetId);
        }

        ProcessTransforDTO processTransforDTO = new ProcessTransforDTO();
        processTransforDTO.setFlowOption(1);
        processTransforDTO.setUserId(requestFlowDTO.getUserId());
        processTransforDTO.setRequestId(requestFlowDTO.getRequestId());
        processTransforDTO.setDescription(requestFlowDTO.getDescription());
        ProcessTransforDTO.Agree agree = new ProcessTransforDTO.Agree();

//        if (requestVO.getStatus().getName().equals(ZtbConstant.TOAUDIT)) {
//            RequestFilterDTO requestFilterDTO = new RequestFilterDTO();
//            requestFilterDTO.setParentId(requestFlowDTO.getRequestId());
//            requestFilterDTO.setCurrentPage(-1);
//            List<RequestVO> requestVOS = ieService.findRequest(requestFilterDTO).getRecords();
//            if (CollectionUtil.isNotEmpty(requestVOS)) {
//                RequestVO childRequestVO = requestVOS.get(requestVOS.size() - 1);
//                AssemblyBuilder assemblyBuilder = AssemblyBuilder.builder();
//                assemblyBuilder.addParameter("requestRelationRequestStatusId", childRequestVO.getStatus().getId() + "L");
//                agree.setFlowConditions(assemblyBuilder.build());
//            }
//        }
        List<Long> roleIds = permService.queryByUserId(requestFlowDTO.getUserId());
        agree.setRoleIds(roleIds);
        processTransforDTO.setAgree(agree);
        return processTransforDTO;
    }

    @Override
    public void approveAfterJudgeStatus(RequestFlowDTO requestFlowDTO) {
        RequestVO requestVO = ieService.findRequestById(requestFlowDTO.getRequestId());
        long sheetId = requestFlowDTO.getId();

        if (requestVO.getStatus().getName().equals(ZtbConstant.TO_BE_AUTHORIZED)) {
            //更新供应商总评分
            BidVendorFilterDTO bidVendorFilterDTO = new BidVendorFilterDTO();
            bidVendorFilterDTO.setBidSheetId(sheetId);
            bidVendorFilterDTO.setVendorStatus(ZtbConstant.VENDOR_REPLIED);
            List<BidVendorVO> bidVendorVOS = bidVendorService.queryBidVendorVOList(bidVendorFilterDTO);
            List<BidVendorDTO> bidVendorDTOS=CollectionUtil.newArrayList();
            for (BidVendorVO bidVendorVO : bidVendorVOS) {
                bidVendorVO.setTotalScore(bidVendorVO.getCommerceScore()+bidVendorVO.getQualificationScore()+bidVendorVO.getTechnologyScore());
                bidVendorDTOS.add(BeanUtil.copyProperties(bidVendorVO,BidVendorDTO.class));
            }
            bidVendorService.saveOrUpdateBatchByDTOList(bidVendorDTOS);
        }
    }

    private void saveBidVendorDetailBySheetId(long sheetId) {
        BidVendorFilterDTO bidVendorFilterDTO = new BidVendorFilterDTO();
        bidVendorFilterDTO.setBidSheetId(sheetId);
        List<BidVendorVO> bidVendorVOS = bidVendorService.queryBidVendorVOList(bidVendorFilterDTO);
        if (CollectionUtil.isEmpty(bidVendorVOS)) {
            log.error("approveBeforeJudgeStatus() bidVendorVOS select faild");
            throw new ZtbWebException(ResponseEnum.BID_VENDOR_SELECT_FAIL);
        }

        //查询出招标明细
        BidDetailFilterDTO bidDetailFilterDTO = new BidDetailFilterDTO();
        bidDetailFilterDTO.setBidSheetId(sheetId);
        List<BidDetailVO> bidDetailVOS = bidDetailService.queryBidDetailVOList(bidDetailFilterDTO);
        if (CollectionUtil.isEmpty(bidDetailVOS)) {
            log.error("approveBeforeJudgeStatus() BidDetailVO select faild");
            throw new ZtbWebException(ResponseEnum.BID_DETAIL_SELECT_ERROR);
        }

        //新增供应商明细
        List<BidVendorDetail> bidVendorDetails = CollectionUtil.newArrayList();
        for (BidVendorVO bidVendorVO : bidVendorVOS) {
            bidDetailVOS.forEach(bidDetailVO -> {
                BidVendorDetail bidVendorDetail = BeanUtil.copyProperties(bidDetailVO, BidVendorDetail.class);
                bidVendorDetail.setId(null);
                bidVendorDetail.setRound(NumberConstant.ZERO);
                bidVendorDetail.setStatus(ZtbConstant.BID_SAVE_STATUS);
                bidVendorDetail.setBidVendorId(bidVendorVO.getId());
                bidVendorDetails.add(bidVendorDetail);
            });
        }

        bidVendorDetailService.saveOrUpdateBatch(bidVendorDetails);
    }

    private void saveBidSpecialistScoreBySheetId(long sheetId) {
        BidSheetVO bidSheetVO = queryBidSheetById(sheetId);
        BidSpecialistFilterDTO bidSpecialistFilterDTO = new BidSpecialistFilterDTO();
        bidSpecialistFilterDTO.setBidSheetId(sheetId);
        List<BidSpecialistVO> bidSpecialistVOS = bidSpecialistService.queryBidSpecialistVOList(bidSpecialistFilterDTO);
        if (CollectionUtil.isEmpty(bidSpecialistVOS)) {
            log.error("approveBeforeJudgeStatus() bidSpecialistVOS select faild");
            throw new ZtbWebException(ResponseEnum.BID_SPECIALIST_SELECT_FAIL);
        }

        //查询出供应商
        BidVendorFilterDTO bidVendorFilterDTO = new BidVendorFilterDTO();
        bidVendorFilterDTO.setBidSheetId(sheetId);
        List<BidVendorVO> bidVendorVOS = bidVendorService.queryBidVendorVOList(bidVendorFilterDTO);
        if (CollectionUtil.isEmpty(bidVendorVOS)) {
            log.error("approveBeforeJudgeStatus() bidVendorVOS select faild");
            throw new ZtbWebException(ResponseEnum.BID_VENDOR_SELECT_FAIL);
        }

        //新增专家评分
        List<BidSpecialistScore> bidSpecialistScores = CollectionUtil.newArrayList();
        for (BidVendorVO bidVendorVO : bidVendorVOS) {
            insertBidSpecialistScore(bidSpecialistVOS, bidSheetVO,
                    bidVendorVO.getId(),bidVendorVO.getVendorName(), bidSpecialistScores);
        }

        bidSpecialistScoreService.saveOrUpdateBatch(bidSpecialistScores);
    }



    private void combineRequest(BidSheetVO bidSheetVO) {
        if (bidSheetVO.getRequestId() != null) {
            RequestVO requestVO = ieService.findRequestById(bidSheetVO.getRequestId());

            bidSheetVO.setRequestVO(requestVO);
            bidSheetVO.setRequestTitle(requestVO.getTitle());
            bidSheetVO.setOddNumber(requestVO.getOddNumber());

            //得到流程所有节点
            ProcessConfigFilterDTO processConfigFilterDTO = new ProcessConfigFilterDTO();
            processConfigFilterDTO.setProcessId(requestVO.getProcess().getId());
            processConfigFilterDTO.setCurrentPage(-1);
            PageVO<ProcessConfigVO> processConfigVOPageVO = ieService.findProcessConfigFilter(processConfigFilterDTO);
            List<ProcessConfigVO> processConfigVOS = processConfigVOPageVO.getRecords();

            List<String> statusNames = processConfigVOS.stream().map(processConfigVO -> processConfigVO.getToStatus().getName()).distinct().collect(Collectors.toList());
            bidSheetVO.setStatusNames(statusNames);

            //查询出子工单信息
//            RequestFilterDTO requestFilterDTO = new RequestFilterDTO();
//            requestFilterDTO.setParentId(bidSheetVO.getRequestId());
//            requestFilterDTO.setCurrentPage(-1);
//
//            PageVO<RequestVO> requestVOPageVO = ieService.findRequest(requestFilterDTO);
//            List<RequestVO> childrequestVOS = requestVOPageVO.getRecords();
//
//            if (CollectionUtil.isEmpty(childrequestVOS)) {
//                return;
//            }
//            for (RequestVO childrequestVO : childrequestVOS) {
//
//                if (!StrUtil.equals(childrequestVO.getStatus().getName(), ZtbConstant.ARCHIVED)
//                        || !StrUtil.equals(childrequestVO.getStatus().getName(), ZtbConstant.CANCELED)) {
//
//                    List<RequestFlowVO> requestFlowVOS = ieService.findRequestFlowByRequestId(childrequestVO.getId());
//
//                    RequestFlowVO newRequestFlowVO = requestFlowVOS.get(requestFlowVOS.size() - 1);
//                    bidSheetVO.setRequestRelationId(childrequestVO.getId());
//                    bidSheetVO.setRequestRelationToUserId(newRequestFlowVO.getToUserId());
//                    bidSheetVO.setRequestRelationToRoleId(newRequestFlowVO.getToRoleId());
//                }
//            }
        }
    }

    private List<BidSheetVO> filterBidSheetList(List<BidSheet> bidSheetList, Long userId) {
        //自己及下属
        //待资质评估/待商业评估/待技术评估状态下专家是否包含自己

        List<BidSheetVO> bidSheetVOList = CollectionUtil.newArrayList();
        for (BidSheet bidSheet : bidSheetList) {
            BidSheetVO bidSheetVO = BeanUtil.copyProperties(bidSheet, BidSheetVO.class);
            //表单状态
            RequestVO requestVO = ieService.findRequestById(bidSheetVO.getRequestId());
            bidSheetVO.setOddNumber(requestVO.getOddNumber());
            bidSheetVO.setRequestStatusName(requestVO.getStatus().getName());
            bidSheetVO.setRequestTitle(requestVO.getTitle());
            if (ZtbConstant.BID_STATUS_PINGGU.contains(requestVO.getStatus().getName())) {
                BidSpecialistFilterDTO bidSpecialistFilterDTO = new BidSpecialistFilterDTO();
                bidSpecialistFilterDTO.setBidSheetId(bidSheetVO.getId());
                List<BidSpecialistVO> bidSpecialistVOS = bidSpecialistService.queryBidSpecialistVOList(bidSpecialistFilterDTO);
                if (CollectionUtil.isNotEmpty(bidSpecialistVOS)) {
                    List<Long> userIds = bidSpecialistVOS.stream().map(BidSpecialistVO::getUserId).collect(Collectors.toList());
                    if (userIds.contains(userId)) {
                        bidSheetVOList.add(bidSheetVO);
                    }
                }
            } else {
                bidSheetVOList.add(bidSheetVO);
            }
        }

        return bidSheetVOList;
    }

    @Override
    public List<BidSpecialistScore> insertBidSpecialistScore(List<BidSpecialistVO> bidSpecialistVOS, BidSheetVO bidSheetVO,
                                                             long bidVendorId,String bidVendorName, List<BidSpecialistScore> bidSpecialistScores) {
        //商业标
        bidSpecialistVOS.forEach(bidSpecialistVO -> {
            BidSpecialistScore bidSpecialistScore = new BidSpecialistScore();
            bidSpecialistScore.setBidVendorId(bidVendorId);
            bidSpecialistScore.setBidVendorName(bidVendorName);
            bidSpecialistScore.setUserId(bidSpecialistVO.getUserId());
            bidSpecialistScore.setBidType(ZtbConstant.COMMERCE);
            bidSpecialistScore.setWhetherView(NumberConstant.ZERO);
            bidSpecialistScore.setStatus(ZtbConstant.SPECIALIST_SCORE_NEW);
            bidSpecialistScores.add(bidSpecialistScore);
        });
        //资质标
        if (NumberConstant.ONE.equals(bidSheetVO.getQualificationTender())) {
            bidSpecialistVOS.forEach(bidSpecialistVO -> {
                BidSpecialistScore bidSpecialistScore = new BidSpecialistScore();
                bidSpecialistScore.setBidVendorId(bidVendorId);
                bidSpecialistScore.setBidVendorName(bidVendorName);
                bidSpecialistScore.setUserId(bidSpecialistVO.getUserId());
                bidSpecialistScore.setBidType(ZtbConstant.QUALIFICATION);
                bidSpecialistScore.setWhetherView(NumberConstant.ZERO);
                bidSpecialistScore.setStatus(ZtbConstant.SPECIALIST_SCORE_NEW);
                bidSpecialistScores.add(bidSpecialistScore);
            });
        }
        //技术标
        if (NumberConstant.ONE.equals(bidSheetVO.getTechnicalTender())) {
            bidSpecialistVOS.forEach(bidSpecialistVO -> {
                BidSpecialistScore bidSpecialistScore = new BidSpecialistScore();
                bidSpecialistScore.setBidVendorId(bidVendorId);
                bidSpecialistScore.setBidVendorName(bidVendorName);
                bidSpecialistScore.setUserId(bidSpecialistVO.getUserId());
                bidSpecialistScore.setBidType(ZtbConstant.TECHNOLOGY);
                bidSpecialistScore.setWhetherView(NumberConstant.ZERO);
                bidSpecialistScore.setStatus(ZtbConstant.SPECIALIST_SCORE_NEW);
                bidSpecialistScores.add(bidSpecialistScore);
            });
        }
        return bidSpecialistScores;
    }

    @Override
    public Boolean updateBidSheetMark(BidSheetVO bidSheetVO, String mark) {
        bidSheetVO.setMark(mark);

        return updateById(BeanUtil.copyProperties(bidSheetVO,BidSheet.class));
    }

}
