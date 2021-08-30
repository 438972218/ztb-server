package com.xdcplus.biz.service;

import com.xdcplus.biz.common.pojo.dto.BidSheetDTO;
import com.xdcplus.biz.common.pojo.dto.ProjectSheetDTO;
import com.xdcplus.biz.common.pojo.dto.ProjectSheetFilterDTO;
import com.xdcplus.biz.common.pojo.vo.BidSheetVO;
import com.xdcplus.biz.generator.ProjectSheetBaseService;
import com.xdcplus.biz.common.pojo.entity.ProjectSheet;
import com.xdcplus.biz.common.pojo.vo.ProjectSheetVO;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;


/**
 * 项目(ProjectSheet)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-20 16:31:01
 */
public interface ProjectSheetService extends ProjectSheetBaseService<ProjectSheet, ProjectSheetVO, ProjectSheet> {


    /**
     * 添加项目(ProjectSheet)返回VO
     *
     * @param projectSheetDTO 项目(ProjectSheetDTO)
     * @return {@link Boolean} 是否成功
     */
    ProjectSheetVO saveProjectSheetReturnVO(ProjectSheetDTO projectSheetDTO);

    /**
     * show项目
     *
     * @param id 项目(ProjectSheet)主键
     * @return {@link ProjectSheetVO} 项目(ProjectSheet)信息
     */
    ProjectSheetVO showProjectSheet(Long id);

    PageVO<ProjectSheetVO> queryProjectSheetWithUser(ProjectSheetFilterDTO projectSheetFilterDTO);

    Boolean deleteProjectSheet(Long id);
    
}
