package com.xdcplus.vendor.common.pojo.dto;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 出价记录过滤器DTO
 *
 * @author Rong.Jia
 * @date 2021/08/18 08:58:18
 */
@ApiModel("出价记录查询参数对照对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class OfferFilterDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 8241130392997945923L;

    /**
     *  表单ID
     */
    @ApiModelProperty("表单ID")
    private Long requestId;

    /**
     *  出价物标识
     */
    @ApiModelProperty("出价物标识")
    private String offerGoods;

    /**
     *  供应商ID
     */
    @ApiModelProperty("供应商ID")
    private Long vendorId;

    /**
     *  出价人
     */
    @ApiModelProperty("出价人")
    private String offerUser;




}
