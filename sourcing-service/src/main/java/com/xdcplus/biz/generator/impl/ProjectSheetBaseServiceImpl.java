package com.xdcplus.biz.generator.impl;

import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.biz.mapper.ProjectSheetMapper;
import com.xdcplus.biz.common.pojo.entity.ProjectSheet;
import com.xdcplus.biz.common.pojo.dto.ProjectSheetDTO;
import com.xdcplus.biz.common.pojo.dto.ProjectSheetFilterDTO;
import com.xdcplus.biz.common.pojo.vo.ProjectSheetVO;
import com.xdcplus.biz.common.pojo.query.ProjectSheetQuery;
import com.xdcplus.biz.generator.ProjectSheetBaseService;
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
 * 项目(ProjectSheet)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-08-20 16:31:01
 */
public class ProjectSheetBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<ProjectSheet, ProjectSheetVO, ProjectSheet, ProjectSheetMapper> implements ProjectSheetBaseService<S, T, E> {

    @Autowired
    protected ProjectSheetMapper projectSheetMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveProjectSheet(ProjectSheetDTO projectSheetDTO) {

        ProjectSheet projectSheet = projectSheetMapper.selectById(projectSheetDTO.getId());
        if (ObjectUtil.isNotNull(projectSheet)) {
            log.error("saveProjectSheet() The ProjectSheet already exists");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        projectSheet = new ProjectSheet();
        BeanUtil.copyProperties(projectSheetDTO, projectSheet);
        projectSheet.setCreatedTime(DateUtil.current());
        projectSheet.setDeleted(0);

        return this.save(projectSheet);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateProjectSheet(ProjectSheetDTO projectSheetDTO) {

        ProjectSheet projectSheet = this.getById(projectSheetDTO.getId());
        if (ObjectUtil.isNull(projectSheet)) {
            log.error("updateProjectSheet() The ProjectSheet does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(projectSheetDTO, projectSheet);
        projectSheet.setUpdatedUser(projectSheetDTO.getUpdatedUser());
        projectSheet.setUpdatedTime(DateUtil.current());

        return this.updateById(projectSheet);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<ProjectSheet> projectSheetList) {

        projectSheetList.forEach(projectSheet -> {
            ProjectSheet projectSheetParam = new ProjectSheet();
            projectSheetParam.setId(projectSheet.getId());
            if (ObjectUtil.isNotNull(projectSheet.getId())) {
                projectSheet.setId(projectSheet.getId());
                projectSheet.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<ProjectSheet> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(ProjectSheet::getId, projectSheet.getId());
                update(projectSheet, lambdaUpdate);
            } else {
                projectSheet.setCreatedTime(DateUtil.current());
                projectSheet.setDeleted(0);
                save(projectSheet);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<ProjectSheetDTO> projectSheetDTOList) {

        List<ProjectSheet> projectSheetList = BeanUtils.copyProperties(projectSheetDTOList, ProjectSheet.class);
        return saveOrUpdateBatch(projectSheetList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteProjectSheetLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        ProjectSheet projectSheet = this.getById(id);

        if (ObjectUtil.isNull(projectSheet)) {
            log.error("deleteProjectSheet() The ProjectSheet does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }
        projectSheet.setDeleted(1);

        return this.updateById(projectSheet);
    }

    private List<ProjectSheet> queryProjectSheetList(ProjectSheetFilterDTO projectSheetFilterDTO) {
        projectSheetFilterDTO.setDeleted(0);
        ProjectSheetQuery projectSheetQuery = BeanUtil.copyProperties(projectSheetFilterDTO, ProjectSheetQuery.class);

        return projectSheetMapper.queryProjectSheet(projectSheetQuery);
    }

    @Override
    public List<ProjectSheetVO> queryProjectSheetVOList(ProjectSheetFilterDTO projectSheetFilterDTO) {
        projectSheetFilterDTO.setDeleted(0);
        return this.objectConversion(queryProjectSheetList(projectSheetFilterDTO));
    }

    @Override
    public PageVO<ProjectSheetVO> queryProjectSheet(ProjectSheetFilterDTO projectSheetFilterDTO) {
        projectSheetFilterDTO.setDeleted(0);
        PageVO<ProjectSheetVO> pageVO = new PageVO<>();

        if (projectSheetFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(projectSheetFilterDTO.getCurrentPage(), projectSheetFilterDTO.getPageSize(),
                    projectSheetFilterDTO.getOrderType(), projectSheetFilterDTO.getOrderField());
        }

        List<ProjectSheet> projectSheetList = queryProjectSheetList(projectSheetFilterDTO);

        PageInfo<ProjectSheet> pageInfo = new PageInfo<>(projectSheetList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(projectSheetList));

        return pageVO;
    }

    @Override
    public ProjectSheetVO queryProjectSheetById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
