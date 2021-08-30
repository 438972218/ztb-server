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
 * 招标专家评分(BidSpecialistScore)表实体类
 *
 * @author Fish.Fei
 * @since 2021-08-26 17:17:59
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_bid_specialist_score")
public class BidSpecialistScore implements Serializable {
    private static final long serialVersionUID = -50249155268847217L;

    @ApiModelProperty("$column.comment")
    private Long id;

    @ApiModelProperty("供应商id")
    private Long bidVendorId;

    @ApiModelProperty("供应商名称")
    private String bidVendorName;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("标类型")
    private String bidType;

    @ApiModelProperty("是否查看")
    private Integer whetherView;

    @ApiModelProperty("评分")
    private Double score;

    @ApiModelProperty("评论")
    private String comment;

    @ApiModelProperty("状态（最新/历史）")
    private String status;

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
