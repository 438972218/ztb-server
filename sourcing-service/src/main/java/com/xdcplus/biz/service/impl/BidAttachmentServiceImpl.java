package com.xdcplus.biz.service.impl;

import com.xdcplus.biz.generator.impl.BidAttachmentBaseServiceImpl;
import com.xdcplus.biz.mapper.BidAttachmentMapper;
import com.xdcplus.biz.common.pojo.entity.BidAttachment;
import com.xdcplus.biz.common.pojo.vo.BidAttachmentVO;
import com.xdcplus.biz.service.BidAttachmentService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 招标附件(BidAttachment)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:23:13
 */
@Slf4j
@Service("bidAttachmentService")
public class BidAttachmentServiceImpl extends BidAttachmentBaseServiceImpl<BidAttachment, BidAttachmentVO, BidAttachment, BidAttachmentMapper> implements BidAttachmentService {


}
