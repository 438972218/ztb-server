package com.xdcplus.biz.generator;

import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.entity.ProjectSheet;
import com.xdcplus.biz.common.pojo.dto.ProjectSheetDTO;
import com.xdcplus.biz.common.pojo.dto.ProjectSheetFilterDTO;
import com.xdcplus.biz.common.pojo.vo.ProjectSheetVO;

import java.util.List;

/**
 * 项目(ProjectSheet)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-20 16:31:00
 */
public interface ProjectSheetBaseService<S, T, E> extends BaseService<ProjectSheet, ProjectSheetVO, ProjectSheet> {

    /**
     * 添加项目(ProjectSheet)
     *
     * @param projectSheetDTO 项目(ProjectSheetDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveProjectSheet(ProjectSheetDTO projectSheetDTO);

    /**
     * 修改项目(ProjectSheet)
     *
     * @param projectSheetDTO 项目(ProjectSheetDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateProjectSheet(ProjectSheetDTO projectSheetDTO);

    /**
     * 批量保存或更新项目(ProjectSheet)
     *
     * @param projectSheetList 项目(ProjectSheetList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<ProjectSheet> projectSheetList);

    /**
     * 批量保存或更新项目(ProjectSheetDTOList)
     *
     * @param projectSheetDTOList 项目(ProjectSheetDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<ProjectSheetDTO> projectSheetDTOList);

    /**
     * 删除项目(ProjectSheet)
     *
     * @param id 项目(ProjectSheet)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteProjectSheetLogical(Long id);

    /**
     * 查询项目(ProjectSheet)
     *
     * @param projectSheetFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<ProjectSheetVO>} 状态信息
     */
    List<ProjectSheetVO> queryProjectSheetVOList(ProjectSheetFilterDTO projectSheetFilterDTO);

    /**
     * 查询项目(ProjectSheet)
     *
     * @param projectSheetFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<ProjectSheetVO>} 状态信息
     */
    PageVO<ProjectSheetVO> queryProjectSheet(ProjectSheetFilterDTO projectSheetFilterDTO);

    /**
     * 查询一个
     *
     * @param id 项目(ProjectSheet)主键
     * @return {@link ProjectSheetVO} 项目(ProjectSheet)信息
     */
    ProjectSheetVO queryProjectSheetById(Long id);


}
