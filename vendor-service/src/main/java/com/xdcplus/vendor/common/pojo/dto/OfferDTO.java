package com.xdcplus.vendor.common.pojo.dto;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 出价信息DTO
 *
 * @author Rong.Jia
 * @date 2021/08/17 10:22:06
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("出价参数对照对象")
public class OfferDTO extends BaseBO implements Serializable {

    private static final long serialVersionUID = -5237204252934995624L;

    /**
     * 金额
     */
    @NotBlank(message = "金额 不能为空")
    @ApiModelProperty(value = "金额", required = true)
    private String money;

    /**
     * 表单ID
     */
    @NotNull(message = "表单ID 不能为空")
    @ApiModelProperty(value = "表单ID", required = true)
    private Long requestId;

    /**
     * 出价物标识
     */
    @NotBlank(message = "出价物标识 不能为空")
    @ApiModelProperty(value = "出价物标识", required = true)
    private String offerGoods;

    /**
     * 出价人
     */
    @NotBlank(message = "出价人 不能为空")
    @ApiModelProperty(value = "出价人", required = true)
    private String offerUser;


















}
