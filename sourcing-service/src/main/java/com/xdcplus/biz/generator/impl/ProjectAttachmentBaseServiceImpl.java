package com.xdcplus.biz.generator.impl;

import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.biz.mapper.ProjectAttachmentMapper;
import com.xdcplus.biz.common.pojo.entity.ProjectAttachment;
import com.xdcplus.biz.common.pojo.dto.ProjectAttachmentDTO;
import com.xdcplus.biz.common.pojo.dto.ProjectAttachmentFilterDTO;
import com.xdcplus.biz.common.pojo.vo.ProjectAttachmentVO;
import com.xdcplus.biz.common.pojo.query.ProjectAttachmentQuery;
import com.xdcplus.biz.generator.ProjectAttachmentBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.ztb.common.tool.utils.BeanUtils;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.utils.PageableUtils;
import com.xdcplus.ztb.common.tool.utils.PropertyUtils;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目附件(ProjectAttachment)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-08-20 16:27:40
 */
public class ProjectAttachmentBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<ProjectAttachment, ProjectAttachmentVO, ProjectAttachment, ProjectAttachmentMapper> implements ProjectAttachmentBaseService<S, T, E> {

    @Autowired
    protected ProjectAttachmentMapper projectAttachmentMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveProjectAttachment(ProjectAttachmentDTO projectAttachmentDTO) {

        ProjectAttachment projectAttachment = projectAttachmentMapper.selectById(projectAttachmentDTO.getId());
        if (ObjectUtil.isNotNull(projectAttachment)) {
            log.error("saveProjectAttachment() The ProjectAttachment already exists");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        projectAttachment = new ProjectAttachment();
        BeanUtil.copyProperties(projectAttachmentDTO, projectAttachment);
        projectAttachment.setCreatedTime(DateUtil.current());
        projectAttachment.setDeleted(0);

        return this.save(projectAttachment);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateProjectAttachment(ProjectAttachmentDTO projectAttachmentDTO) {

        ProjectAttachment projectAttachment = this.getById(projectAttachmentDTO.getId());
        if (ObjectUtil.isNull(projectAttachment)) {
            log.error("updateProjectAttachment() The ProjectAttachment does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(projectAttachmentDTO, projectAttachment);
        projectAttachment.setUpdatedUser(projectAttachmentDTO.getUpdatedUser());
        projectAttachment.setUpdatedTime(DateUtil.current());

        return this.updateById(projectAttachment);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<ProjectAttachment> projectAttachmentList) {

        projectAttachmentList.forEach(projectAttachment -> {
            ProjectAttachment projectAttachmentParam = new ProjectAttachment();
            projectAttachmentParam.setId(projectAttachment.getId());
            if (ObjectUtil.isNotNull(projectAttachment.getId())) {
                projectAttachment.setId(projectAttachment.getId());
                projectAttachment.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<ProjectAttachment> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(ProjectAttachment::getId, projectAttachment.getId());
                update(projectAttachment, lambdaUpdate);
            } else {
                projectAttachment.setCreatedTime(DateUtil.current());
                projectAttachment.setDeleted(0);
                save(projectAttachment);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<ProjectAttachmentDTO> projectAttachmentDTOList) {

        List<ProjectAttachment> projectAttachmentList = BeanUtils.copyProperties(projectAttachmentDTOList, ProjectAttachment.class);
        return saveOrUpdateBatch(projectAttachmentList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteProjectAttachmentLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        ProjectAttachment projectAttachment = this.getById(id);

        if (ObjectUtil.isNull(projectAttachment)) {
            log.error("deleteProjectAttachment() The ProjectAttachment does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }
        projectAttachment.setDeleted(1);

        return this.updateById(projectAttachment);
    }

    private List<ProjectAttachment> queryProjectAttachmentList(ProjectAttachmentFilterDTO projectAttachmentFilterDTO) {
        projectAttachmentFilterDTO.setDeleted(0);
        ProjectAttachmentQuery projectAttachmentQuery = BeanUtil.copyProperties(projectAttachmentFilterDTO, ProjectAttachmentQuery.class);

        return projectAttachmentMapper.queryProjectAttachment(projectAttachmentQuery);
    }

    @Override
    public List<ProjectAttachmentVO> queryProjectAttachmentVOList(ProjectAttachmentFilterDTO projectAttachmentFilterDTO) {
        projectAttachmentFilterDTO.setDeleted(0);
        return this.objectConversion(queryProjectAttachmentList(projectAttachmentFilterDTO));
    }

    @Override
    public PageVO<ProjectAttachmentVO> queryProjectAttachment(ProjectAttachmentFilterDTO projectAttachmentFilterDTO) {
        projectAttachmentFilterDTO.setDeleted(0);
        PageVO<ProjectAttachmentVO> pageVO = new PageVO<>();

        if (projectAttachmentFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(projectAttachmentFilterDTO.getCurrentPage(), projectAttachmentFilterDTO.getPageSize(),
                    projectAttachmentFilterDTO.getOrderType(), projectAttachmentFilterDTO.getOrderField());
        }

        List<ProjectAttachment> projectAttachmentList = queryProjectAttachmentList(projectAttachmentFilterDTO);

        PageInfo<ProjectAttachment> pageInfo = new PageInfo<>(projectAttachmentList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(projectAttachmentList));

        return pageVO;
    }

    @Override
    public ProjectAttachmentVO queryProjectAttachmentById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
