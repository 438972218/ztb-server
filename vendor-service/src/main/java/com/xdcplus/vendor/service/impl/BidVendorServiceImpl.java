package com.xdcplus.vendor.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xdcplus.vendor.common.pojo.dto.BidVendorDTO;
import com.xdcplus.vendor.generator.impl.BidVendorBaseServiceImpl;
import com.xdcplus.vendor.mapper.BidVendorMapper;
import com.xdcplus.vendor.common.pojo.entity.BidVendor;
import com.xdcplus.vendor.common.pojo.vo.BidVendorVO;
import com.xdcplus.vendor.service.BidVendorDetailService;
import com.xdcplus.vendor.service.BidVendorService;
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
 * @author Fish.Fei
 * @since 2021-08-19 16:42:09
 */
@Slf4j
@Service("bidVendorService")
public class BidVendorServiceImpl extends BidVendorBaseServiceImpl<BidVendor, BidVendorVO, BidVendor, BidVendorMapper> implements BidVendorService {

    @Autowired
    BidVendorDetailService bidVendorDetailService;

    @Override
    public void updateBidVendorStatusByVendorUserId(BidVendorDTO bidVendorDTO) {

        List<BidVendor> bidVendors = bidVendorMapper.selectList(new QueryWrapper<BidVendor>().lambda().eq(BidVendor::getBidSheetId,bidVendorDTO.getBidSheetId())
                .eq(BidVendor::getVendorUserId,bidVendorDTO.getVendorUserId()));
        if(CollectionUtil.isEmpty(bidVendors)){
            log.error("updateBidVendorStatusByVendorUserId() bidVendors select faild");
            throw new ZtbWebException(ResponseEnum.BID_VENDOR_SELECT_FAIL);
        }
        BidVendor bidVendor =bidVendors.get(NumberConstant.ZERO);
        bidVendor.setVendorStatus(bidVendorDTO.getVendorStatus());
        int result = bidVendorMapper.updateById(bidVendor);
        if (result == 0) {
            log.error("updateBidVendorStatusByVendorUserId() bidVendors update faild");
            throw new ZtbWebException(ResponseEnum.BID_VENDOR_UPDATE_FAIL);
        }
        bidVendorDetailService.updateBidVendorDetailByOperation(bidVendor.getId(),bidVendorDTO.getVendorStatus());
    }

    @Override
    public void updateBidVendorCheckTimeByVendorUserId(BidVendorDTO bidVendorDTO) {
        List<BidVendor> bidVendors = bidVendorMapper.selectList(new QueryWrapper<BidVendor>().lambda()
                .eq(BidVendor::getBidSheetId,bidVendorDTO.getBidSheetId())
                .eq(BidVendor::getVendorUserId,bidVendorDTO.getVendorUserId()));
        if(CollectionUtil.isEmpty(bidVendors)){
            log.error("updateBidVendorCheckTimeByVendorUserId() The BidVendor select faild");
            throw new ZtbWebException(ResponseEnum.BID_VENDOR_SELECT_FAIL);
        }
        BidVendor bidVendor=bidVendors.get(NumberConstant.ZERO);
        if(bidVendor.getCheckTime()==null){
            bidVendor.setCheckTime(DateUtil.current());
            bidVendorMapper.updateById(bidVendor);
        }
    }
}
