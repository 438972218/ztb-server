package com.xdcplus.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.xdcplus.biz.common.constants.ZtbConstant;
import com.xdcplus.biz.common.pojo.dto.BidSpecialistScoreDTO;
import com.xdcplus.biz.common.pojo.dto.BidSpecialistScoreFilterDTO;
import com.xdcplus.biz.common.pojo.dto.BidVendorDTO;
import com.xdcplus.biz.common.pojo.dto.BidVendorFilterDTO;
import com.xdcplus.biz.common.pojo.vo.BidVendorVO;
import com.xdcplus.biz.generator.impl.BidSpecialistScoreBaseServiceImpl;
import com.xdcplus.biz.mapper.BidSpecialistScoreMapper;
import com.xdcplus.biz.common.pojo.entity.BidSpecialistScore;
import com.xdcplus.biz.common.pojo.vo.BidSpecialistScoreVO;
import com.xdcplus.biz.remote.service.PermService;
import com.xdcplus.biz.service.BidSpecialistScoreService;
import com.xdcplus.biz.service.BidVendorService;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.remote.domain.perm.vo.SysUserInfoVO;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 招标专家评分(BidSpecialistScore)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-08-24 16:30:57
 */
@Slf4j
@Service("bidSpecialistScoreService")
public class BidSpecialistScoreServiceImpl extends BidSpecialistScoreBaseServiceImpl<BidSpecialistScore, BidSpecialistScoreVO, BidSpecialistScore, BidSpecialistScoreMapper> implements BidSpecialistScoreService {

    @Autowired
    BidVendorService bidVendorService;

    @Autowired
    PermService permService;

    @Override
    public List<BidSpecialistScoreVO> queryBidSpecialistScoreVOBySpecialist(BidSpecialistScoreFilterDTO bidSpecialistScoreFilterDTO) {
        BidVendorFilterDTO bidVendorFilterDTO = new BidVendorFilterDTO();
        bidVendorFilterDTO.setBidSheetId(bidSpecialistScoreFilterDTO.getBidSheetId());
        bidVendorFilterDTO.setVendorStatus(ZtbConstant.VENDOR_REPLIED);
        List<BidVendorVO> bidVendorVOS = bidVendorService.queryBidVendorVOList(bidVendorFilterDTO);
        if (CollectionUtil.isEmpty(bidVendorVOS)) {
            log.error("queryBidSpecialistScoreVOBySpecialist() BidVendor select faild");
            throw new ZtbWebException(ResponseEnum.BID_VENDOR_SELECT_FAIL);
        }
        List<Long> bidVendorIds = bidVendorVOS.stream().map(BidVendorVO::getId).collect(Collectors.toList());
        bidSpecialistScoreFilterDTO.setBidVendorIds(bidVendorIds);
        bidSpecialistScoreFilterDTO.setStatus(ZtbConstant.SPECIALIST_SCORE_NEW);
        List<BidSpecialistScoreVO> bidSpecialistScoreVOS = queryBidSpecialistScoreVOList(bidSpecialistScoreFilterDTO);

        return bidSpecialistScoreVOS;
    }

    @Override
    public List<BidSpecialistScoreVO> queryBidSpecialistScoreVOByBidVendor(BidSpecialistScoreFilterDTO bidSpecialistScoreFilterDTO) {
        List<BidSpecialistScoreVO> bidSpecialistScoreVOS = queryBidSpecialistScoreVOList(bidSpecialistScoreFilterDTO);
        if (CollectionUtil.isEmpty(bidSpecialistScoreVOS)) {
            return null;
        }
        bidSpecialistScoreVOS.forEach(bidSpecialistScoreVO -> {
            SysUserInfoVO sysUserInfoVO = permService.sysUserQueryById(bidSpecialistScoreVO.getUserId());
            bidSpecialistScoreVO.setSysUserInfoVO(sysUserInfoVO);
        });

        return bidSpecialistScoreVOS;
    }

    @Override
    public void updateWhetherViewBySpecialist(BidSpecialistScoreDTO bidSpecialistScoreDTO) {
        updateBidSpecialistScore(bidSpecialistScoreDTO);
        String bidType = bidSpecialistScoreDTO.getBidType();
        long bidVendorId = bidSpecialistScoreDTO.getBidVendorId();
        BidVendorVO bidVendorVO = bidVendorService.queryBidVendorById(bidVendorId);

        if (bidType.equals(ZtbConstant.COMMERCE)) {
            bidVendorVO.setCommerceView(NumberConstant.ONE);
        } else if (bidType.equals(ZtbConstant.TECHNOLOGY)) {
            bidVendorVO.setTechnologyView(NumberConstant.ONE);
        } else if (bidType.equals(ZtbConstant.QUALIFICATION)) {
            bidVendorVO.setQualificationView(NumberConstant.ONE);
        }
        bidVendorService.updateBidVendor(BeanUtil.copyProperties(bidVendorVO, BidVendorDTO.class));
    }

    @Override
    public void updateScoreWithBidVendor(BidSpecialistScoreDTO bidSpecialistScoreDTO) {
        Boolean result = updateBidSpecialistScore(bidSpecialistScoreDTO);
        BidVendorVO bidVendorVO = bidVendorService.queryBidVendorById(bidSpecialistScoreDTO.getBidVendorId());
        String bidType = bidSpecialistScoreDTO.getBidType();
        Double score = bidSpecialistScoreDTO.getScore();

        if (ZtbConstant.QUALIFICATION.equals(bidType)) {
            bidVendorVO.setQualificationScore(bidVendorVO.getQualificationScore()+score);
        } else if (ZtbConstant.TECHNOLOGY.equals(bidType)) {
            bidVendorVO.setTechnologyScore(bidVendorVO.getTechnologyScore()+score);
        } else if (ZtbConstant.COMMERCE.equals(bidType)) {
            bidVendorVO.setCommerceScore(bidVendorVO.getCommerceScore()+score);
        }

        bidVendorService.updateBidVendor(BeanUtil.copyProperties(bidVendorVO,BidVendorDTO.class));
    }
}
