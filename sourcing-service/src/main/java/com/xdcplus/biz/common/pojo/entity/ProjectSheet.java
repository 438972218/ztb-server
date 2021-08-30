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
 * 项目(ProjectSheet)表实体类
 *
 * @author Fish.Fei
 * @since 2021-08-20 16:30:59
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_project_sheet")
public class ProjectSheet implements Serializable {
    private static final long serialVersionUID = -57988715807849132L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("代码")
    private String code;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("部门")
    private String department;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("项目激活时间")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Long activationTime;

    @ApiModelProperty("项目启动时间")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Long startTime;

    @ApiModelProperty("项目完成时间")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Long finishTime;

    @ApiModelProperty("品类")
    private String items;

    @ApiModelProperty("说明")
    private String explanation;

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
