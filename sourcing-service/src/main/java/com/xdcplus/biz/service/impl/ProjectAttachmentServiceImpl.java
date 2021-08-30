package com.xdcplus.biz.service.impl;

import com.xdcplus.biz.generator.impl.ProjectAttachmentBaseServiceImpl;
import com.xdcplus.biz.mapper.ProjectAttachmentMapper;
import com.xdcplus.biz.common.pojo.entity.ProjectAttachment;
import com.xdcplus.biz.common.pojo.vo.ProjectAttachmentVO;
import com.xdcplus.biz.service.ProjectAttachmentService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 项目附件(ProjectAttachment)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-08-20 16:27:41
 */
@Slf4j
@Service("projectAttachmentService")
public class ProjectAttachmentServiceImpl extends ProjectAttachmentBaseServiceImpl<ProjectAttachment, ProjectAttachmentVO, ProjectAttachment, ProjectAttachmentMapper> implements ProjectAttachmentService {


}
