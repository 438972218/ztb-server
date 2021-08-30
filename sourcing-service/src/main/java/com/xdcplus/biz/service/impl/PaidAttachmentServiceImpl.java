package com.xdcplus.biz.service.impl;

import com.xdcplus.biz.generator.impl.PaidAttachmentBaseServiceImpl;
import com.xdcplus.biz.mapper.PaidAttachmentMapper;
import com.xdcplus.biz.common.pojo.entity.PaidAttachment;
import com.xdcplus.biz.common.pojo.vo.PaidAttachmentVO;
import com.xdcplus.biz.service.PaidAttachmentService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 竞价单附件(PaidAttachment)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-08-16 14:01:57
 */
@Slf4j
@Service("paidAttachmentService")
public class PaidAttachmentServiceImpl extends PaidAttachmentBaseServiceImpl<PaidAttachment, PaidAttachmentVO, PaidAttachment, PaidAttachmentMapper> implements PaidAttachmentService {


}
