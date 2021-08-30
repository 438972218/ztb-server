package com.xdcplus.biz.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldStrategy;

import java.io.Serializable;

/**
 * 项目成员信息(ProjectMember)表实体类
 *
 * @author Fish.Fei
 * @since 2021-08-24 09:40:40
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_project_member")
public class ProjectMember implements Serializable {
    private static final long serialVersionUID = 481265592002079793L;

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
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Long createdTime;

    @ApiModelProperty("修改人")
    private String updatedUser;

    @ApiModelProperty("修改时间")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Long updatedTime;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("版本号")
    private Integer version;

    @ApiModelProperty("是否已经逻辑删除（0：未删除 1：已删除）")
    private Integer deleted;
}
