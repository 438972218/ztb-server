package com.xdcplus.vendor.common.pojo.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 出价记录VO
 *
 * @author Rong.Jia
 * @date 2021/08/17 09:46:09
 */
@ApiModel("出价记录信息 对照对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class OfferVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 4562076174607707963L;

    /**
     * 金额
     */
    @ApiModelProperty("金额")
    private String money;

    /**
     * 出价时间
     */
    @ApiModelProperty("出价时间")
    private Long offerTime;

    /**
     * 出价人
     */
    @ApiModelProperty("出价人")
    private String offerUser;

    /**
     * 表单ID
     */
    @ApiModelProperty("表单ID")
    private Long requestId;

    /**
     * 排名
     */
    @ApiModelProperty("排名")
    private Integer ranking;

    /**
     * 出价物标识
     */
    @ApiModelProperty("出价物标识")
    private String offerGoods;

    /**
     * 供应商ID
     */
    @ApiModelProperty("供应商ID")
    private Long vendorId;










}
