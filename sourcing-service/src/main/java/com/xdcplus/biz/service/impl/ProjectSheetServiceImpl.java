package com.xdcplus.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.xdcplus.biz.common.pojo.dto.*;
import com.xdcplus.biz.common.pojo.entity.ProjectAttachment;
import com.xdcplus.biz.common.pojo.query.ProjectSheetQuery;
import com.xdcplus.biz.common.pojo.vo.ProjectAttachmentVO;
import com.xdcplus.biz.common.pojo.vo.ProjectMemberVO;
import com.xdcplus.biz.generator.impl.ProjectSheetBaseServiceImpl;
import com.xdcplus.biz.mapper.ProjectSheetMapper;
import com.xdcplus.biz.common.pojo.entity.ProjectSheet;
import com.xdcplus.biz.common.pojo.vo.ProjectSheetVO;
import com.xdcplus.biz.remote.service.IeService;
import com.xdcplus.biz.service.*;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.utils.PageableUtils;
import com.xdcplus.ztb.common.tool.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 项目(ProjectSheet)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-08-20 16:31:01
 */
@Slf4j
@Service("projectSheetService")
public class ProjectSheetServiceImpl extends ProjectSheetBaseServiceImpl<ProjectSheet, ProjectSheetVO, ProjectSheet, ProjectSheetMapper> implements ProjectSheetService {

    @Autowired
    ProjectAttachmentService projectAttachmentService;

    @Autowired
    ProjectMemberService projectMemberService;

    @Autowired
    BidSheetService bidSheetService;

    @Autowired
    PaidSheetService paidSheetService;

    @Override
    public ProjectSheetVO saveProjectSheetReturnVO(ProjectSheetDTO projectSheetDTO) {

        List<ProjectMemberDTO> projectMemberDTOS = projectMemberService.combineProjectMember(projectSheetDTO.getCreatedUser());

        ProjectSheet projectSheet = projectSheetMapper.selectById(projectSheetDTO.getId());
        if (ObjectUtil.isNotNull(projectSheet)) {
            log.error("saveProjectSheetReturnVO() The ProjectSheet already exists");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        projectSheet = new ProjectSheet();
        BeanUtil.copyProperties(projectSheetDTO, projectSheet);
        projectSheet.setCreatedTime(DateUtil.current());
        boolean result = this.save(projectSheet);
        ProjectSheetVO projectSheetVO = BeanUtil.copyProperties(projectSheet, ProjectSheetVO.class);

        projectMemberDTOS.forEach(projectMemberDTO -> projectMemberDTO.setProjectId(projectSheetVO.getId()));
        projectMemberService.saveOrUpdateBatchByDTOList(projectMemberDTOS);

        if (result) {
            return projectSheetVO;
        } else {
            return null;
        }
    }

    @Override
    public ProjectSheetVO showProjectSheet(Long id) {
        ProjectSheetVO projectSheetVO=queryProjectSheetById(id);
        if(ObjectUtil.isEmpty(projectSheetVO)){
            return null;
        }

        ProjectAttachmentFilterDTO projectAttachmentFilterDTO=new ProjectAttachmentFilterDTO();
        projectAttachmentFilterDTO.setProjectId(projectSheetVO.getId());
        List<ProjectAttachmentVO> projectAttachmentVOS = projectAttachmentService.queryProjectAttachmentVOList(projectAttachmentFilterDTO);
        projectSheetVO.setProjectAttachmentVOS(projectAttachmentVOS);

        ProjectMemberFilterDTO projectMemberFilterDTO=new ProjectMemberFilterDTO();
        projectMemberFilterDTO.setProjectId(projectSheetVO.getId());
        List<ProjectMemberVO> projectMemberVOS = projectMemberService.queryProjectMemberVOList(projectMemberFilterDTO);
        projectSheetVO.setProjectMemberVOS(projectMemberVOS);

        return projectSheetVO;
    }

    @Override
    public PageVO<ProjectSheetVO> queryProjectSheetWithUser(ProjectSheetFilterDTO projectSheetFilterDTO) {
        projectSheetFilterDTO.setDeleted(0);
        PageVO<ProjectSheetVO> pageVO = new PageVO<>();

        if (projectSheetFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(projectSheetFilterDTO.getCurrentPage(), projectSheetFilterDTO.getPageSize(),
                    projectSheetFilterDTO.getOrderType(), projectSheetFilterDTO.getOrderField());
        }

        ProjectSheetQuery projectSheetQuery = BeanUtil.copyProperties(projectSheetFilterDTO, ProjectSheetQuery.class);
        List<ProjectSheet> projectSheetList = projectSheetMapper.queryProjectSheetWithUser(projectSheetQuery);

        PageInfo<ProjectSheet> pageInfo = new PageInfo<>(projectSheetList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(projectSheetList));

        return pageVO;
    }

    @Override
    public Boolean deleteProjectSheet(Long id) {
        int bidSheetCount = bidSheetService.selectCountByProjectId(id);
        int paidSheetCount = paidSheetService.selectCountByProjectId(id);
        if(!NumberConstant.ZERO.equals(bidSheetCount) || !NumberConstant.ZERO.equals(paidSheetCount)){
            log.error("deleteProjectSheet() this ProjectSheet exist other Sheet");
            throw new ZtbWebException(ResponseEnum.PROJECT_DELETE_FAILD);
        }

        return deleteProjectSheetLogical(id);
    }

}
