package com.xdcplus.biz.generator.impl;

import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.biz.mapper.ProjectMemberMapper;
import com.xdcplus.biz.common.pojo.entity.ProjectMember;
import com.xdcplus.biz.common.pojo.dto.ProjectMemberDTO;
import com.xdcplus.biz.common.pojo.dto.ProjectMemberFilterDTO;
import com.xdcplus.biz.common.pojo.vo.ProjectMemberVO;
import com.xdcplus.biz.common.pojo.query.ProjectMemberQuery;
import com.xdcplus.biz.generator.ProjectMemberBaseService;
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
 * 项目成员信息(ProjectMember)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-08-24 09:40:41
 */
public class ProjectMemberBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<ProjectMember, ProjectMemberVO, ProjectMember, ProjectMemberMapper> implements ProjectMemberBaseService<S, T, E> {

    @Autowired
    protected ProjectMemberMapper projectMemberMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveProjectMember(ProjectMemberDTO projectMemberDTO) {

        ProjectMember projectMember = projectMemberMapper.selectById(projectMemberDTO.getId());
        if (ObjectUtil.isNotNull(projectMember)) {
            log.error("saveProjectMember() The ProjectMember already exists");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        projectMember = new ProjectMember();
        BeanUtil.copyProperties(projectMemberDTO, projectMember);
        projectMember.setCreatedTime(DateUtil.current());
        projectMember.setDeleted(0);

        return this.save(projectMember);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateProjectMember(ProjectMemberDTO projectMemberDTO) {

        ProjectMember projectMember = this.getById(projectMemberDTO.getId());
        if (ObjectUtil.isNull(projectMember)) {
            log.error("updateProjectMember() The ProjectMember does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(projectMemberDTO, projectMember);
        projectMember.setUpdatedUser(projectMemberDTO.getUpdatedUser());
        projectMember.setUpdatedTime(DateUtil.current());

        return this.updateById(projectMember);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<ProjectMember> projectMemberList) {

        projectMemberList.forEach(projectMember -> {
            ProjectMember projectMemberParam = new ProjectMember();
            projectMemberParam.setId(projectMember.getId());
            if (ObjectUtil.isNotNull(projectMember.getId())) {
                projectMember.setId(projectMember.getId());
                projectMember.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<ProjectMember> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(ProjectMember::getId, projectMember.getId());
                update(projectMember, lambdaUpdate);
            } else {
                projectMember.setCreatedTime(DateUtil.current());
                projectMember.setDeleted(0);
                save(projectMember);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<ProjectMemberDTO> projectMemberDTOList) {

        List<ProjectMember> projectMemberList = BeanUtils.copyProperties(projectMemberDTOList, ProjectMember.class);
        return saveOrUpdateBatch(projectMemberList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteProjectMemberLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        ProjectMember projectMember = this.getById(id);

        if (ObjectUtil.isNull(projectMember)) {
            log.error("deleteProjectMember() The ProjectMember does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }
        projectMember.setDeleted(1);

        return this.updateById(projectMember);
    }

    private List<ProjectMember> queryProjectMemberList(ProjectMemberFilterDTO projectMemberFilterDTO) {
        projectMemberFilterDTO.setDeleted(0);
        ProjectMemberQuery projectMemberQuery = BeanUtil.copyProperties(projectMemberFilterDTO, ProjectMemberQuery.class);

        return projectMemberMapper.queryProjectMember(projectMemberQuery);
    }

    @Override
    public List<ProjectMemberVO> queryProjectMemberVOList(ProjectMemberFilterDTO projectMemberFilterDTO) {
        projectMemberFilterDTO.setDeleted(0);
        return this.objectConversion(queryProjectMemberList(projectMemberFilterDTO));
    }

    @Override
    public PageVO<ProjectMemberVO> queryProjectMember(ProjectMemberFilterDTO projectMemberFilterDTO) {
        projectMemberFilterDTO.setDeleted(0);
        PageVO<ProjectMemberVO> pageVO = new PageVO<>();

        if (projectMemberFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(projectMemberFilterDTO.getCurrentPage(), projectMemberFilterDTO.getPageSize(),
                    projectMemberFilterDTO.getOrderType(), projectMemberFilterDTO.getOrderField());
        }

        List<ProjectMember> projectMemberList = queryProjectMemberList(projectMemberFilterDTO);

        PageInfo<ProjectMember> pageInfo = new PageInfo<>(projectMemberList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(projectMemberList));

        return pageVO;
    }

    @Override
    public ProjectMemberVO queryProjectMemberById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
