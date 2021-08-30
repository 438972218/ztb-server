package com.xdcplus.biz.generator;

import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.entity.ProjectAttachment;
import com.xdcplus.biz.common.pojo.dto.ProjectAttachmentDTO;
import com.xdcplus.biz.common.pojo.dto.ProjectAttachmentFilterDTO;
import com.xdcplus.biz.common.pojo.vo.ProjectAttachmentVO;

import java.util.List;

/**
 * 项目附件(ProjectAttachment)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-20 16:27:40
 */
public interface ProjectAttachmentBaseService<S, T, E> extends BaseService<ProjectAttachment, ProjectAttachmentVO, ProjectAttachment> {

    /**
     * 添加项目附件(ProjectAttachment)
     *
     * @param projectAttachmentDTO 项目附件(ProjectAttachmentDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveProjectAttachment(ProjectAttachmentDTO projectAttachmentDTO);

    /**
     * 修改项目附件(ProjectAttachment)
     *
     * @param projectAttachmentDTO 项目附件(ProjectAttachmentDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateProjectAttachment(ProjectAttachmentDTO projectAttachmentDTO);

    /**
     * 批量保存或更新项目附件(ProjectAttachment)
     *
     * @param projectAttachmentList 项目附件(ProjectAttachmentList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<ProjectAttachment> projectAttachmentList);

    /**
     * 批量保存或更新项目附件(ProjectAttachmentDTOList)
     *
     * @param projectAttachmentDTOList 项目附件(ProjectAttachmentDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<ProjectAttachmentDTO> projectAttachmentDTOList);

    /**
     * 删除项目附件(ProjectAttachment)
     *
     * @param id 项目附件(ProjectAttachment)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteProjectAttachmentLogical(Long id);

    /**
     * 查询项目附件(ProjectAttachment)
     *
     * @param projectAttachmentFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<ProjectAttachmentVO>} 状态信息
     */
    List<ProjectAttachmentVO> queryProjectAttachmentVOList(ProjectAttachmentFilterDTO projectAttachmentFilterDTO);

    /**
     * 查询项目附件(ProjectAttachment)
     *
     * @param projectAttachmentFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<ProjectAttachmentVO>} 状态信息
     */
    PageVO<ProjectAttachmentVO> queryProjectAttachment(ProjectAttachmentFilterDTO projectAttachmentFilterDTO);

    /**
     * 查询一个
     *
     * @param id 项目附件(ProjectAttachment)主键
     * @return {@link ProjectAttachmentVO} 项目附件(ProjectAttachment)信息
     */
    ProjectAttachmentVO queryProjectAttachmentById(Long id);


}
