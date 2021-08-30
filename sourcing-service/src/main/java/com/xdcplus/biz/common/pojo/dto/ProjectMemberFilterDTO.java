package com.xdcplus.biz.common.pojo.dto;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 项目成员信息(ProjectMember)表查询入参DTO类
 *
 * @author Fish.Fei
 * @since 2021-08-24 09:40:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class ProjectMemberFilterDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = -15780618297937313L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("招标单id")
    private Long projectId;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("邮件")
    private String email;

    @ApiModelProperty("部门")
    private String department;

    @ApiModelProperty("岗位")
    private String post;

    @ApiModelProperty("创建人")
    private String createdUser;

    @ApiModelProperty("创建时间")
    private Long createdTime;

    @ApiModelProperty("修改人")
    private String updatedUser;

    @ApiModelProperty("修改时间")
    private Long updatedTime;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("版本号")
    private Integer version;

    @ApiModelProperty("是否已经逻辑删除（0：未删除 1：已删除）")
    private Integer deleted;

}
