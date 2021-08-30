package com.xdcplus.biz.common.pojo.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 竞价物品(PaidMaterial)表VO类
 *
 * @author Fish.Fei
 * @since 2021-08-30 15:32:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class PaidMaterialVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -77011287537460295L;

    @ApiModelProperty("竞价单id")
    private Long paidSheetId;

    @ApiModelProperty("物品标识")
    private Integer materialMark;

    @ApiModelProperty("物品名称")
    private String materialName;

    @ApiModelProperty("竞价开始时间")
    private Long beginTime;

    @ApiModelProperty("竞价截止时间")
    private Long endTime;

    @ApiModelProperty("单位")
    private String unit;

    @ApiModelProperty("数量")
    private Double quantity;

}
