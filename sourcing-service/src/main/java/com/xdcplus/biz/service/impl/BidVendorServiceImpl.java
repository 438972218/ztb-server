package com.xdcplus.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xdcplus.biz.common.constants.ZtbConstant;
import com.xdcplus.biz.common.pojo.dto.*;
import com.xdcplus.biz.common.pojo.entity.BidSpecialistScore;
import com.xdcplus.biz.common.pojo.entity.BidVendorDetail;
import com.xdcplus.biz.common.pojo.vo.*;
import com.xdcplus.biz.generator.impl.BidVendorBaseServiceImpl;
import com.xdcplus.biz.mapper.BidVendorMapper;
import com.xdcplus.biz.common.pojo.entity.BidVendor;
import com.xdcplus.biz.service.*;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 招标投标方(BidVendor)表服务实现类
 *
 * @author makejava
 * @since 2021-08-12 14:45:47
 */
@Slf4j
@Service("bidVendorService")
public class BidVendorServiceImpl extends BidVendorBaseServiceImpl<BidVendor, BidVendorVO, BidVendor, BidVendorMapper> implements BidVendorService {

    @Autowired
    private BidDetailService bidDetailService;

    @Autowired
    private BidVendorDetailService bidVendorDetailService;

    @Autowired
    private BidSpecialistScoreService bidSpecialistScoreService;

    @Autowired
    private BidSheetService bidSheetService;

    @Autowired
    private BidSpecialistService bidSpecialistService;

    @Override
    public Boolean saveBidVendorWithDetail(BidVendorDTO bidVendorDTO) {
        Boolean result = saveBidVendorWithDefault(bidVendorDTO);
        if (result) {
            log.error("saveBidVendorWithDetail() BidVendor insert faild");
            throw new ZtbWebException(ResponseEnum.BID_VENDOR_INSERT_FAIL);
        }
        long id = bidVendorDTO.getId();
        long sheetId = bidVendorDTO.getBidSheetId();
        BidDetailFilterDTO bidDetailFilterDTO = new BidDetailFilterDTO();
        bidDetailFilterDTO.setBidSheetId(sheetId);
        List<BidDetailVO> bidDetailVOS = bidDetailService.queryBidDetailVOList(bidDetailFilterDTO);
        if (CollectionUtil.isEmpty(bidDetailVOS)) {
            log.error("saveBidVendorWithDetail() BidDetailVO select faild");
            throw new ZtbWebException(ResponseEnum.BID_DETAIL_SELECT_ERROR);
        }
        List<BidVendorDetail> bidVendorDetails = CollectionUtil.newArrayList();
        bidDetailVOS.forEach(bidDetailVO -> {
            BidVendorDetail bidVendorDetail = BeanUtil.copyProperties(bidDetailVO, BidVendorDetail.class);
            bidVendorDetail.setId(null);
            bidVendorDetail.setBidVendorId(id);
            bidVendorDetail.setRound(NumberConstant.ZERO);
            bidVendorDetails.add(bidVendorDetail);
        });

        return bidVendorDetailService.saveOrUpdateBatch(bidVendorDetails);
    }

    @Override
    public Boolean saveBidVendorWithDefault(BidVendorDTO bidVendorDTO) {
        BidVendor bidVendor = BeanUtil.copyProperties(bidVendorDTO, BidVendor.class);
        bidVendor.setCreatedTime(DateUtil.current());
        bidVendor.setDeleted(0);
        bidVendor.setAgainStatus(ZtbConstant.INVITED);
        bidVendor.setVendorStatus(ZtbConstant.VENDOR_NO_REPLIED);
        bidVendor.setRound(NumberConstant.ZERO);
        bidVendor.setQualificationAttQuantity(NumberConstant.ZERO);
        bidVendor.setTechnologyAttQuantity(NumberConstant.ZERO);
        bidVendor.setCommerceScore(Double.valueOf(NumberConstant.ZERO));
        bidVendor.setQualificationScore(Double.valueOf(NumberConstant.ZERO));
        bidVendor.setTechnologyScore(Double.valueOf(NumberConstant.ZERO));
        return this.save(bidVendor);
    }

    @Override
    public void returnBidVendor(List<BidVendorDTO> bidVendorDTOS, long bidSheetId,String mark) {
        BidSheetVO bidSheetVO = bidSheetService.queryBidSheetById(bidSheetId);
        //修改bidSheet标识:澄清/最终
        bidSheetService.updateBidSheetMark(bidSheetVO,mark);
        BidSpecialistFilterDTO bidSpecialistFilterDTO = new BidSpecialistFilterDTO();
        bidSpecialistFilterDTO.setBidSheetId(bidSheetId);
        List<BidSpecialistVO> bidSpecialistVOS = bidSpecialistService.queryBidSpecialistVOList(bidSpecialistFilterDTO);
        if (CollectionUtil.isEmpty(bidSpecialistVOS)) {
            log.error("approveBeforeJudgeStatus() bidSpecialistVOS select faild");
            throw new ZtbWebException(ResponseEnum.BID_SPECIALIST_SELECT_FAIL);
        }

        for (BidVendorDTO bidVendorDTO : bidVendorDTOS) {

            long bidVendorId = bidVendorDTO.getId();
            String againStatus = bidVendorDTO.getAgainStatus();

            //更新供应商重开RFQ状态,轮次,查看RFQ时间清空
            resetBidVendor(bidVendorId, againStatus);

            if (ZtbConstant.INVITED.equals(againStatus)) {
                //受邀，更新提交状态的明细改为历史，并新增保存状态的明细,轮次+1
                //修改原评分状态为历史,并新增最新状态的专家评分
                resetBidVendorDetail(bidSheetId, bidVendorId);

                //修改原评分状态为历史,并新增最新状态的专家评分
                resetBidSpecialistScore(bidSheetVO, bidSpecialistVOS, bidVendorId);
            }
        }
    }

    /**
     * 重置投标供应商
     * 更新供应商重开RFQ状态,轮次,查看RFQ时间清空
     *
     * @param bidVendorId 投标供应商id
     * @param againStatus 再一次状态
     */
    private void resetBidVendor(long bidVendorId, String againStatus) {
        //更新供应商重开RFQ状态,轮次,查看RFQ时间清空
        BidVendor bidVendor = bidVendorMapper.selectById(bidVendorId);
        bidVendor.setAgainStatus(againStatus);

        if (ZtbConstant.INVITED.equals(againStatus)) {
            bidVendor.setVendorStatus(ZtbConstant.VENDOR_NO_REPLIED);
            bidVendor.setRound(bidVendor.getRound() + 1);
            bidVendor.setCheckTime(null);
            bidVendor.setReplyTime(null);
            bidVendor.setQualificationView(NumberConstant.ZERO);
            bidVendor.setTechnologyView(NumberConstant.ZERO);
            bidVendor.setCommerceView(NumberConstant.ZERO);
            bidVendor.setQualificationScore(Double.valueOf(NumberConstant.ZERO));
            bidVendor.setTechnologyScore(Double.valueOf(NumberConstant.ZERO));
            bidVendor.setCommerceScore(Double.valueOf(NumberConstant.ZERO));
            bidVendor.setTotalScore(Double.valueOf(NumberConstant.ZERO));
            bidVendor.setTotalPrice(Double.valueOf(NumberConstant.ZERO));
        }

        boolean result = updateById(bidVendor);
        if (!result) {
            log.error("returnBidVendor() The BidVendor update faild");
            throw new ZtbWebException(ResponseEnum.BID_VENDOR_UPDATE_FAIL);
        }
    }

    /**
     * 重置投标供应商的细节
     *
     * @param bidSheetId  报价表的id
     * @param bidVendorId 投标供应商id
     */
    private void resetBidVendorDetail(long bidSheetId, long bidVendorId) {
        //受邀，更新提交状态的明细改为历史，并新增保存状态的明细,轮次+1
        //修改原评分状态为历史,并新增最新状态的专家评分

        BidVendorDetailFilterDTO bidVendorDetailFilterDTO = new BidVendorDetailFilterDTO();
        bidVendorDetailFilterDTO.setBidVendorId(bidVendorId);
        bidVendorDetailFilterDTO.setStatus(ZtbConstant.SUBMIT);
        List<BidVendorDetailVO> bidVendorDetailVOS = bidVendorDetailService.queryBidVendorDetailVOList(bidVendorDetailFilterDTO);
        if (CollectionUtil.isEmpty(bidVendorDetailVOS)) {
            log.error("returnBidVendor() The BidVendorDetail select faild");
            throw new ZtbWebException(ResponseEnum.BID_VENDOR_DETAIL_SELECT_FAIL);
        }
        Integer round = bidVendorDetailVOS.get(NumberConstant.ZERO).getRound();

        List<BidVendorDetailDTO> bidVendorDetailDTOS = CollectionUtil.newArrayList();
        bidVendorDetailVOS.forEach(bidVendorDetailVO -> {
            BidVendorDetailDTO bidVendorDetailDTO = BeanUtil.copyProperties(bidVendorDetailVO, BidVendorDetailDTO.class);
            bidVendorDetailDTO.setStatus(ZtbConstant.HISTORY);
            bidVendorDetailDTOS.add(bidVendorDetailDTO);
        });
        bidVendorDetailService.saveOrUpdateBatchByDTOList(bidVendorDetailDTOS);

        //新增保存状态的明细,轮次+1
        //查询出招标明细
        BidDetailFilterDTO bidDetailFilterDTO = new BidDetailFilterDTO();
        bidDetailFilterDTO.setBidSheetId(bidSheetId);
        List<BidDetailVO> bidDetailVOS = bidDetailService.queryBidDetailVOList(bidDetailFilterDTO);
        if (CollectionUtil.isEmpty(bidDetailVOS)) {
            log.error("approveBeforeJudgeStatus() BidDetailVO select faild");
            throw new ZtbWebException(ResponseEnum.BID_DETAIL_SELECT_ERROR);
        }

        List<BidVendorDetail> bidVendorDetails = CollectionUtil.newArrayList();
        bidDetailVOS.forEach(bidDetailVO -> {
            BidVendorDetail bidVendorDetail = BeanUtil.copyProperties(bidDetailVO, BidVendorDetail.class);
            bidVendorDetail.setId(null);
            bidVendorDetail.setRound(round + 1);
            bidVendorDetail.setStatus(ZtbConstant.BID_SAVE_STATUS);
            bidVendorDetail.setBidVendorId(bidVendorId);
            bidVendorDetails.add(bidVendorDetail);
        });

        bidVendorDetailService.saveOrUpdateBatch(bidVendorDetails);
    }

    /**
     * 重置收购专家评分
     *
     * @param bidSheetVO       报价表签证官
     * @param bidSpecialistVOS 收购专家vos
     * @param bidVendorId      投标供应商id
     */
    private void resetBidSpecialistScore(BidSheetVO bidSheetVO, List<BidSpecialistVO> bidSpecialistVOS, long bidVendorId) {
        //修改原评分状态为历史,并新增最新状态的专家评分
        BidSpecialistScoreFilterDTO bidSpecialistScoreFilterDTO = new BidSpecialistScoreFilterDTO();
        bidSpecialistScoreFilterDTO.setBidVendorId(bidVendorId);
        bidSpecialistScoreFilterDTO.setStatus("最新");
        List<BidSpecialistScoreVO> bidSpecialistScoreVOS = bidSpecialistScoreService.queryBidSpecialistScoreVOList(bidSpecialistScoreFilterDTO);
        List<BidSpecialistScoreDTO> bidSpecialistScoreDTOS = CollectionUtil.newArrayList();
        bidSpecialistScoreVOS.forEach(bidSpecialistScoreVO -> {
            bidSpecialistScoreVO.setStatus("历史");
            BidSpecialistScoreDTO bidSpecialistScoreDTO =
                    BeanUtil.copyProperties(bidSpecialistScoreVO, BidSpecialistScoreDTO.class);
            bidSpecialistScoreDTOS.add(bidSpecialistScoreDTO);
        });
        bidSpecialistScoreService.saveOrUpdateBatchByDTOList(bidSpecialistScoreDTOS);
        //新增最新状态的专家评分
        List<BidSpecialistScore> bidSpecialistScores = CollectionUtil.newArrayList();
        BidVendorVO bidVendorVO = queryBidVendorById(bidVendorId);
        bidSpecialistScores = bidSheetService.insertBidSpecialistScore(bidSpecialistVOS, bidSheetVO,
                bidVendorId,bidVendorVO.getVendorName(), bidSpecialistScores);
        bidSpecialistScoreService.saveOrUpdateBatch(bidSpecialistScores);
    }
}
