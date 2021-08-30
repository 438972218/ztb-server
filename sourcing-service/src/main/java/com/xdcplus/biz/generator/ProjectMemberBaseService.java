package com.xdcplus.biz.generator;

import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.entity.ProjectMember;
import com.xdcplus.biz.common.pojo.dto.ProjectMemberDTO;
import com.xdcplus.biz.common.pojo.dto.ProjectMemberFilterDTO;
import com.xdcplus.biz.common.pojo.vo.ProjectMemberVO;

import java.util.List;

/**
 * 项目成员信息(ProjectMember)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-24 09:40:41
 */
public interface ProjectMemberBaseService<S, T, E> extends BaseService<ProjectMember, ProjectMemberVO, ProjectMember> {

    /**
     * 添加项目成员信息(ProjectMember)
     *
     * @param projectMemberDTO 项目成员信息(ProjectMemberDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveProjectMember(ProjectMemberDTO projectMemberDTO);

    /**
     * 修改项目成员信息(ProjectMember)
     *
     * @param projectMemberDTO 项目成员信息(ProjectMemberDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateProjectMember(ProjectMemberDTO projectMemberDTO);

    /**
     * 批量保存或更新项目成员信息(ProjectMember)
     *
     * @param projectMemberList 项目成员信息(ProjectMemberList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<ProjectMember> projectMemberList);

    /**
     * 批量保存或更新项目成员信息(ProjectMemberDTOList)
     *
     * @param projectMemberDTOList 项目成员信息(ProjectMemberDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<ProjectMemberDTO> projectMemberDTOList);

    /**
     * 删除项目成员信息(ProjectMember)
     *
     * @param id 项目成员信息(ProjectMember)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteProjectMemberLogical(Long id);

    /**
     * 查询项目成员信息(ProjectMember)
     *
     * @param projectMemberFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<ProjectMemberVO>} 状态信息
     */
    List<ProjectMemberVO> queryProjectMemberVOList(ProjectMemberFilterDTO projectMemberFilterDTO);

    /**
     * 查询项目成员信息(ProjectMember)
     *
     * @param projectMemberFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<ProjectMemberVO>} 状态信息
     */
    PageVO<ProjectMemberVO> queryProjectMember(ProjectMemberFilterDTO projectMemberFilterDTO);

    /**
     * 查询一个
     *
     * @param id 项目成员信息(ProjectMember)主键
     * @return {@link ProjectMemberVO} 项目成员信息(ProjectMember)信息
     */
    ProjectMemberVO queryProjectMemberById(Long id);


}
