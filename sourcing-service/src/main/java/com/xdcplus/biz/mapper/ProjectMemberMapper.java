package com.xdcplus.biz.mapper;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.biz.common.pojo.entity.ProjectMember;
import com.xdcplus.biz.common.pojo.query.ProjectMemberQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目成员信息(ProjectMember)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-08-24 09:40:41
 */
public interface ProjectMemberMapper extends IBaseMapper<ProjectMember> {

    /**
     * 查询项目成员信息(ProjectMember)
     *
     * @param projectMemberQuery 项目成员信息(ProjectMember)查询
     * @return {@link List<ProjectMember>}
     */
    List<ProjectMember> queryProjectMember(ProjectMemberQuery projectMemberQuery);

}
