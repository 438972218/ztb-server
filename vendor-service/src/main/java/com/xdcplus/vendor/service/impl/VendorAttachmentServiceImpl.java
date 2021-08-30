package com.xdcplus.vendor.service.impl;

import com.xdcplus.vendor.generator.impl.VendorAttachmentBaseServiceImpl;
import com.xdcplus.vendor.mapper.VendorAttachmentMapper;
import com.xdcplus.vendor.common.pojo.entity.VendorAttachment;
import com.xdcplus.vendor.common.pojo.vo.VendorAttachmentVO;
import com.xdcplus.vendor.service.VendorAttachmentService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 招标附件(VendorAttachment)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-08-23 10:14:55
 */
@Slf4j
@Service("vendorAttachmentService")
public class VendorAttachmentServiceImpl extends VendorAttachmentBaseServiceImpl<VendorAttachment, VendorAttachmentVO, VendorAttachment, VendorAttachmentMapper> implements VendorAttachmentService {


}
