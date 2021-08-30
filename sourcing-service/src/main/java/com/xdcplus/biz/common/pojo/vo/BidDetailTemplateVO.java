package com.xdcplus.biz.common.pojo.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 内容模板(BidDetailTemplate)表VO类
 *
 * @author Fish.Fei
 * @since 2021-08-30 09:07:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class BidDetailTemplateVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -88118904119021898L;

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

}
