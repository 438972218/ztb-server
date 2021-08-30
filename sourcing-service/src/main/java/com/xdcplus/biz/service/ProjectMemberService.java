package com.xdcplus.biz.service;

import com.xdcplus.biz.common.pojo.dto.ProjectMemberDTO;
import com.xdcplus.biz.generator.ProjectMemberBaseService;
import com.xdcplus.biz.common.pojo.entity.ProjectMember;
import com.xdcplus.biz.common.pojo.vo.ProjectMemberVO;

import java.util.List;


/**
 * 项目成员信息(ProjectMember)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-24 09:40:42
 */
public interface ProjectMemberService extends ProjectMemberBaseService<ProjectMember, ProjectMemberVO, ProjectMember> {

    List<ProjectMemberDTO> combineProjectMember(String userName);

}
