package com.xdcplus.vendor.common.pojo.entity;

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
 * 招标附件(BidAttachment)表实体类
 *
 * @author Fish.Fei
 * @since 2021-08-26 10:00:01
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_bid_attachment")
public class BidAttachment implements Serializable {
    private static final long serialVersionUID = -29996767926454239L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("招标单id")
    private Long bidSheetId;

    @ApiModelProperty("招标供应商id")
    private Long bidVendorId;

    @ApiModelProperty("类型(用户、供应商)")
    private String type;

    @ApiModelProperty("标类型")
    private String bidType;

    @ApiModelProperty("供应商是否可见")
    private Integer vendorVisible;

    @ApiModelProperty("附件类型")
    private String attachmentType;

    @ApiModelProperty("附件名称")
    private String attachmentName;

    @ApiModelProperty("附件地址")
    private String attachmentUrl;

    @ApiModelProperty("上传人")
    private String uploadingUser;

    @ApiModelProperty("上传时间")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Long uploadingDate;

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
