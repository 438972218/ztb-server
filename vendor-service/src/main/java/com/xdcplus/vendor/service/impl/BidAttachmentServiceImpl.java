package com.xdcplus.vendor.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.xdcplus.vendor.common.constants.ZtbConstant;
import com.xdcplus.vendor.common.pojo.dto.BidAttachmentDTO;
import com.xdcplus.vendor.common.pojo.vo.BidVendorVO;
import com.xdcplus.vendor.generator.impl.BidAttachmentBaseServiceImpl;
import com.xdcplus.vendor.mapper.BidAttachmentMapper;
import com.xdcplus.vendor.common.pojo.entity.BidAttachment;
import com.xdcplus.vendor.common.pojo.vo.BidAttachmentVO;
import com.xdcplus.vendor.service.BidAttachmentService;
import com.xdcplus.vendor.service.BidVendorService;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 招标附件(BidAttachment)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-08-26 10:00:04
 */
@Slf4j
@Service("bidAttachmentService")
public class BidAttachmentServiceImpl extends BidAttachmentBaseServiceImpl<BidAttachment, BidAttachmentVO, BidAttachment, BidAttachmentMapper> implements BidAttachmentService {

    @Autowired
    BidVendorService bidVendorService;

    @Override
    public Boolean saveBidAttachmentWithVendor(BidAttachmentDTO bidAttachmentDTO) {
        Boolean result = saveBidAttachment(bidAttachmentDTO);
        if (result) {
            BidVendorVO bidVendorVO = bidVendorService.queryBidVendorById(bidAttachmentDTO.getBidVendorId());
            Assert.notNull(bidVendorVO, ResponseEnum.BID_VENDOR_SELECT_FAIL.getMessage());
            if (ZtbConstant.QUALIFICATION.equals(bidAttachmentDTO.getBidType())) {
                //资质
                bidVendorVO.setQualificationAttQuantity(bidVendorVO.getQualificationAttQuantity()+1);
            }else if(ZtbConstant.TECHNOLOGY.equals(bidAttachmentDTO.getBidType())){
                //技术
                bidVendorVO.setTechnologyAttQuantity(bidVendorVO.getTechnologyAttQuantity()+1);
            }

        }
        return null;
    }
}
