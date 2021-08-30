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
 * 内容模板(BidDetailTemplate)表实体类
 *
 * @author Fish.Fei
 * @since 2021-08-30 09:07:52
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_bid_detail_template")
public class BidDetailTemplate implements Serializable {
    private static final long serialVersionUID = 127849343243342839L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("分类（模板1，模板2，模板3）")
    private String classify;

    @ApiModelProperty("标类型（商业标，资质标，技术标）")
    private String bidType;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("标题")
    private String name;

    @ApiModelProperty("说明")
    private String explaination;

    @ApiModelProperty("数量")
    private Double quantity;

    @ApiModelProperty("单位")
    private String unit;

    @ApiModelProperty("var1")
    private String var1;

    @ApiModelProperty("var2")
    private String var2;

    @ApiModelProperty("var3")
    private String var3;

    @ApiModelProperty("var4")
    private String var4;

    @ApiModelProperty("var5")
    private String var5;

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
