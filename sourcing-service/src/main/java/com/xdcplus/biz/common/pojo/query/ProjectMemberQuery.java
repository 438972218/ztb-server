package com.xdcplus.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 项目成员信息(ProjectMember)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-08-24 09:40:40
 */
@Data
@SuppressWarnings("serial")
public class ProjectMemberQuery implements Serializable {
    private static final long serialVersionUID = -45722519187223002L;

    private Long id;

    private Long projectId;

    private Long userId;

    private String name;

    private String email;

    private String department;

    private String post;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}
