package com.xdcplus.vendor.service;

import com.xdcplus.vendor.common.pojo.dto.BidAttachmentDTO;
import com.xdcplus.vendor.generator.BidAttachmentBaseService;
import com.xdcplus.vendor.common.pojo.entity.BidAttachment;
import com.xdcplus.vendor.common.pojo.vo.BidAttachmentVO;


/**
 * 招标附件(BidAttachment)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-26 10:00:04
 */
public interface BidAttachmentService extends BidAttachmentBaseService<BidAttachment, BidAttachmentVO, BidAttachment> {

    Boolean saveBidAttachmentWithVendor(BidAttachmentDTO bidAttachmentDTO);
}
