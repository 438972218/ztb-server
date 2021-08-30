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
 * 字典管理(Dictionary)表实体类
 *
 * @author Fish.Fei
 * @since 2021-08-16 11:23:17
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_dictionary")
public class Dictionary implements Serializable {
    private static final long serialVersionUID = 684043509058962388L;

    @ApiModelProperty("$column.comment")
    private Long id;

    @ApiModelProperty("字典名称")
    private String dictionaryChinese;

    @ApiModelProperty("字典类名")
    private String dictionaryClass;

    @ApiModelProperty("下拉中文名")
    private String meaning;

    @ApiModelProperty("下拉ID")
    private Integer numerical;

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
