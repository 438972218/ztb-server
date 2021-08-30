package com.xdcplus.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.xdcplus.biz.common.constants.ZtbConstant;
import com.xdcplus.biz.common.pojo.dto.*;
import com.xdcplus.biz.common.pojo.entity.BidSheet;
import com.xdcplus.biz.common.pojo.entity.PaidVendor;
import com.xdcplus.biz.common.pojo.vo.PaidMaterialVO;
import com.xdcplus.biz.generator.impl.PaidSheetBaseServiceImpl;
import com.xdcplus.biz.mapper.PaidSheetMapper;
import com.xdcplus.biz.common.pojo.entity.PaidSheet;
import com.xdcplus.biz.common.pojo.vo.PaidSheetVO;
import com.xdcplus.biz.remote.service.IeService;
import com.xdcplus.biz.remote.service.PermService;
import com.xdcplus.biz.service.PaidAttachmentService;
import com.xdcplus.biz.service.PaidMaterialService;
import com.xdcplus.biz.service.PaidSheetService;
import com.xdcplus.biz.service.PaidVendorService;
import com.xdcplus.ztb.common.cache.RedisUtils;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.remote.domain.workflow.dto.ProcessConfigFilterDTO;
import com.xdcplus.ztb.common.remote.domain.workflow.dto.RequestFilterDTO;
import com.xdcplus.ztb.common.remote.domain.workflow.vo.ProcessConfigVO;
import com.xdcplus.ztb.common.remote.domain.workflow.vo.RequestFlowVO;
import com.xdcplus.ztb.common.remote.domain.workflow.vo.RequestVO;
import com.xdcplus.ztb.common.remote.domain.perm.vo.SysUserInfoVO;
import com.xdcplus.ztb.common.tool.constants.CacheConstant;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.utils.PageableUtils;
import com.xdcplus.ztb.common.tool.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 竞价单(PaidSheet)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-08-16 14:02:03
 */
@Slf4j
@Service("paidSheetService")
public class PaidSheetServiceImpl extends PaidSheetBaseServiceImpl<PaidSheet, PaidSheetVO, PaidSheet, PaidSheetMapper> implements PaidSheetService {

    @Autowired
    private PaidMaterialService paidMaterialService;

    @Autowired
    private PaidVendorService paidVendorService;

    @Autowired
    private PaidAttachmentService paidAttachmentService;

    @Autowired
    private IeService ieService;

    @Autowired
    private PermService permService;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public PaidSheetVO savePaidSheetReturnVO(PaidSheetDTO paidSheetDTO) {
        PaidSheet paidSheet = paidSheetMapper.selectById(paidSheetDTO.getId());
        if (ObjectUtil.isNotNull(paidSheet)) {
            log.error("savePaidSheet() The PaidSheet already exists");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        paidSheet = new PaidSheet();
        BeanUtil.copyProperties(paidSheetDTO, paidSheet);
        paidSheet.setCreatedTime(DateUtil.current());

        boolean result = this.save(paidSheet);
        PaidSheetVO paidSheetVO = BeanUtil.copyProperties(paidSheet, PaidSheetVO.class);
        if (result) {
            return paidSheetVO;
        } else {
            return null;
        }
    }

//    @Override
//    public PaidSheetVO showPaidSheetById(Long id) {
//        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
//        PaidSheetVO paidSheetVO = this.objectConversion(this.getById(id));
//
//        PaidMaterialFilterDTO paidMaterialFilterDTO = new PaidMaterialFilterDTO();
//        paidMaterialFilterDTO.setPaidSheetId(id);
//        paidSheetVO.setPaidMaterialVOS(paidMaterialService.queryPaidMaterialVOList(paidMaterialFilterDTO));
//
//        PaidVendorFilterDTO paidVendorFilterDTO = new PaidVendorFilterDTO();
//        paidVendorFilterDTO.setPaidSheetId(id);
//        paidSheetVO.setPaidVendorVOS(paidVendorService.queryPaidVendorVOList(paidVendorFilterDTO));
//
//        PaidAttachmentFilterDTO paidAttachmentFilterDTO = new PaidAttachmentFilterDTO();
//        paidAttachmentFilterDTO.setPaidSheetId(id);
//        paidSheetVO.setPaidAttachmentVOS(paidAttachmentService.queryPaidAttachmentVOList(paidAttachmentFilterDTO));
//
//        return paidSheetVO;
//    }

    @Override
    public PaidSheetVO showPaidSheetByRequestId(Long id) {
        PaidSheetFilterDTO paidSheetFilterDTO = new PaidSheetFilterDTO();
        paidSheetFilterDTO.setRequestId(id);
        List<PaidSheetVO> paidSheetVOS = queryPaidSheetVOList(paidSheetFilterDTO);
        if (CollectionUtil.isEmpty(paidSheetVOS)) {
            return null;
        }
        PaidSheetVO paidSheetVO = paidSheetVOS.get(NumberConstant.ZERO);
        combineRequest(paidSheetVO);

        return paidSheetVO;
    }

    @Override
    public PageVO<PaidSheetVO> queryPaidSheetByVendor(PaidSheetFilterDTO paidSheetFilterDTO) {
        PageVO<PaidSheetVO> pageVO = new PageVO<>();

        if (paidSheetFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(paidSheetFilterDTO.getCurrentPage(), paidSheetFilterDTO.getPageSize(),
                    paidSheetFilterDTO.getOrderType(), paidSheetFilterDTO.getOrderField());
        }
        RequestFilterDTO requestFilterDTO = new RequestFilterDTO();
        requestFilterDTO.setKeyword(paidSheetFilterDTO.getKeyword());
        requestFilterDTO.setStatusId(paidSheetFilterDTO.getStatusId());

        PageVO<RequestVO> requestVOPageVO = ieService.findRequest(requestFilterDTO);
        List<RequestVO> requestVOS = requestVOPageVO.getRecords();
        if (CollectionUtil.isNotEmpty(requestVOS)) {
            List<Long> requestIds = requestVOS.stream().map(RequestVO::getId).collect(Collectors.toList());
            paidSheetFilterDTO.setRequestIds(requestIds);
        }

        List<PaidSheet> paidSheetList = queryPaidSheetList(paidSheetFilterDTO);
        PageInfo<PaidSheet> pageInfo = new PageInfo<>(paidSheetList);

        List<PaidSheetVO> paidSheetVOList = CollectionUtil.newArrayList();

        for (PaidSheet paidSheet : paidSheetList) {
            PaidSheetVO paidSheetVO = BeanUtil.copyProperties(paidSheet, PaidSheetVO.class);
            //供应商状态
            List<PaidVendor> paidVendors = paidVendorService.list(new QueryWrapper<PaidVendor>()
                    .eq("vendor_code", paidSheetFilterDTO.getVendorCode())
                    .eq("paid_sheet_id", paidSheet.getId()));
            if (CollUtil.isEmpty(paidVendors)) {
                continue;
            }
            PaidVendor paidVendor = paidVendors.get(NumberConstant.ZERO);
            if (paidVendor.getVendorStatus() == null) {
                paidSheetVO.setVendorStatus(ZtbConstant.TO_PARTICIPATE);
            } else {
                paidSheetVO.setVendorStatus(paidVendor.getVendorStatus());
            }

            if (paidSheetVO.getRequestId() == null || paidSheetVO.getRequestId() == 0) {
                continue;
            }
            //表单状态
            RequestVO requestVO = ieService.findRequestById(paidSheetVO.getRequestId());

            paidSheetVO.setOddNumber(requestVO.getOddNumber());
            paidSheetVO.setRequestStatusName(requestVO.getStatus().getName());
            paidSheetVO.setRequestTitle(requestVO.getTitle());

            if (!StrUtil.equals(requestVO.getStatus().getName(), ZtbConstant.DAIFABU)
                    && !StrUtil.equals(requestVO.getStatus().getName(), ZtbConstant.TOAUDIT)) {
                if (StrUtil.equals(requestVO.getStatus().getName(), ZtbConstant.YIFABU)) {
                    if (paidVendor.getVendorStatus() == null) {
                        paidSheetVO.setVendorStatus(ZtbConstant.TO_PARTICIPATE);
                    } else {
                        paidSheetVO.setVendorStatus(paidVendor.getVendorStatus());
                    }
                } else {
                    if (paidVendor.getVendorStatus() == null) {
                        paidSheetVO.setVendorStatus(ZtbConstant.NO_PARTICIPATE);
                    } else {
                        paidSheetVO.setVendorStatus(paidVendor.getVendorStatus());
                    }
                }
                paidSheetVOList.add(paidSheetVO);
            }

//            if (StrUtil.equals(requestVO.getStatus().getName(), ZtbConstant.YIFABU)) {
//                if (paidVendor.getVendorStatus() == null) {
//                    paidSheetVO.setVendorStatus(ZtbConstant.TO_PARTICIPATE);
//                } else {
//                    paidSheetVO.setVendorStatus(paidVendor.getVendorStatus());
//                }
//            } else {
//                if (paidVendor.getVendorStatus() == null) {
//                    paidSheetVO.setVendorStatus(ZtbConstant.NO_PARTICIPATE);
//                } else {
//                    paidSheetVO.setVendorStatus(paidVendor.getVendorStatus());
//                }
//            }
//            paidSheetVOList.add(paidSheetVO);
        }

        PropertyUtils.copyProperties(pageInfo, pageVO, paidSheetVOList);

        return pageVO;
    }

    @Override
    public PageVO<PaidSheetVO> queryPaidSheetWithRequest(PaidSheetFilterDTO paidSheetFilterDTO) {
        PageVO<PaidSheetVO> pageVO = new PageVO<>();

        if (paidSheetFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(paidSheetFilterDTO.getCurrentPage(), paidSheetFilterDTO.getPageSize(),
                    paidSheetFilterDTO.getOrderType(), paidSheetFilterDTO.getOrderField());
        }
        RequestFilterDTO requestFilterDTO = new RequestFilterDTO();
        requestFilterDTO.setKeyword(paidSheetFilterDTO.getKeyword());
        requestFilterDTO.setStatusId(paidSheetFilterDTO.getStatusId());
        PageVO<RequestVO> requestVOPageVO = ieService.findRequest(requestFilterDTO);
        List<RequestVO> requestVOS = requestVOPageVO.getRecords();
        if (CollectionUtil.isNotEmpty(requestVOS)) {
            List<Long> requestIds = requestVOS.stream().map(RequestVO::getId).collect(Collectors.toList());
            paidSheetFilterDTO.setRequestIds(requestIds);
        }

        List<PaidSheet> paidSheetList = queryPaidSheetList(paidSheetFilterDTO);
        PageInfo<PaidSheet> pageInfo = new PageInfo<>(paidSheetList);

        List<PaidSheetVO> paidSheetVOList = CollectionUtil.newArrayList();

        for (PaidSheet paidSheet : paidSheetList) {
            PaidSheetVO paidSheetVO = BeanUtil.copyProperties(paidSheet, PaidSheetVO.class);
            //表单状态
            RequestVO requestVO = ieService.findRequestById(paidSheetVO.getRequestId());
            paidSheetVO.setOddNumber(requestVO.getOddNumber());
            paidSheetVO.setRequestStatusName(requestVO.getStatus().getName());
            paidSheetVO.setRequestTitle(requestVO.getTitle());
            PaidMaterialFilterDTO paidMaterialFilterDTO = new PaidMaterialFilterDTO();
            paidMaterialFilterDTO.setPaidSheetId(paidSheet.getId());
            List<PaidMaterialVO> paidMaterialVOS = paidMaterialService.queryPaidMaterialVOList(paidMaterialFilterDTO);
//            if(CollectionUtil.isNotEmpty(paidMaterialVOS)){
//                paidSheetVO.setPaidMaterialVOS(paidMaterialVOS);
//                paidSheetVO.setPaidMaterialId(paidMaterialVOS.get(0).getId());
//            }
            paidSheetVOList.add(paidSheetVO);
        }

        PropertyUtils.copyProperties(pageInfo, pageVO, paidSheetVOList);

        return pageVO;
    }

    @Override
    public void queryPaidSheetByMonitor() {
//        PaidSheetQuery paidSheetQuery = new PaidSheetQuery();
////        paidSheetQuery.setPaidStatusMarks(Arrays.asList(1, 2));
//        paidSheetQuery.setPaidStatusMarks(ZtbConstant.PAID_SHEET_STATUSES);
        List<PaidSheet> paidSheets = paidSheetMapper.queryPaidSheetCache();

        if (CollectionUtil.isNotEmpty(paidSheets)) {
            List<PaidSheetVO> paidSheetVOS= CollectionUtil.newArrayList();
            for (PaidSheet paidSheet : paidSheets) {
                PaidSheetVO paidSheetVO=BeanUtil.copyProperties(paidSheet,PaidSheetVO.class);
                PaidMaterialFilterDTO paidMaterialFilterDTO =new PaidMaterialFilterDTO();
                paidMaterialFilterDTO.setPaidSheetId(paidSheetVO.getId());
                paidSheetVO.setPaidMaterialVOS(paidMaterialService.queryPaidMaterialVOList(paidMaterialFilterDTO));
                paidSheetVOS.add(paidSheetVO);
            }
            redisUtils.set(CacheConstant.PAIDS, JSON.toJSONString(paidSheetVOS));
        }
    }

    @Override
    public void startPaidSheet() {
        long nowTime = System.currentTimeMillis();
        List<PaidSheet> paidSheets = paidSheetMapper.selectList(
                new QueryWrapper<PaidSheet>().lambda().isNull(PaidSheet::getPaidStatusMark));
        if (CollectionUtil.isEmpty(paidSheets)) {
            return;
        }
        for (PaidSheet paidSheet : paidSheets) {
            Long startTime = paidSheet.getOfferStartTime();
            if (nowTime >= startTime) {
                paidSheet.setPaidStatusMark(1);
                paidSheet.setPaidStatus("进行中");
                paidSheetMapper.updateById(paidSheet);
            }
        }
    }

    @Override
    public int selectCountByProjectId(Long id) {
        return paidSheetMapper.selectCount(
                new QueryWrapper<PaidSheet>().lambda().eq(PaidSheet::getProjectSheetId,id).eq(PaidSheet::getDeleted,0));
    }

    private void combineRequest(PaidSheetVO paidSheetVO) {
        if (paidSheetVO.getRequestId() != null) {
            RequestVO requestVO = ieService.findRequestById(paidSheetVO.getRequestId());
            paidSheetVO.setRequestVO(requestVO);
            paidSheetVO.setRequestTitle(requestVO.getTitle());
            paidSheetVO.setOddNumber(requestVO.getOddNumber());

            ProcessConfigFilterDTO processConfigFilterDTO = new ProcessConfigFilterDTO();
            processConfigFilterDTO.setCurrentPage(-1);
            processConfigFilterDTO.setProcessId(requestVO.getProcess().getId());
            PageVO<ProcessConfigVO> processConfigVOPageVO = ieService.findProcessConfigFilter(processConfigFilterDTO);
            List<ProcessConfigVO> processConfigVOS = processConfigVOPageVO.getRecords();

            List<String> statusNames = processConfigVOS.stream().map(processConfigVO -> processConfigVO.getToStatus().getName()).distinct().collect(Collectors.toList());
            paidSheetVO.setStatusNames(statusNames);
        }
        if (paidSheetVO.getCreatedUser() != null) {
            SysUserInfoVO sysUserInfoVO = permService.queryByUserName(paidSheetVO.getCreatedUser());
            paidSheetVO.setSysUserInfoVO(sysUserInfoVO);
        }

        List<RequestVO> childrequestVOS = ieService.findRequestByParentId(paidSheetVO.getRequestId());
        if (CollectionUtil.isEmpty(childrequestVOS)) {
            return;
        }
        for (RequestVO childrequestVO : childrequestVOS) {
            if (!StrUtil.equals(childrequestVO.getStatus().getName(), ZtbConstant.ARCHIVED)
                    || !StrUtil.equals(childrequestVO.getStatus().getName(), ZtbConstant.CANCELED)) {
                List<RequestFlowVO> requestFlowVOS = ieService.findRequestFlowByRequestId(childrequestVO.getId());
                RequestFlowVO newRequestFlowVO = requestFlowVOS.get(requestFlowVOS.size() - 1);
                paidSheetVO.setRequestRelationId(childrequestVO.getId());
                paidSheetVO.setRequestRelationToUserId(newRequestFlowVO.getToUserId());
                paidSheetVO.setRequestRelationToRoleId(newRequestFlowVO.getToRoleId());
            }
        }
    }

}
