package com.xdcplus.vendor.common.pojo.dto;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商用户(VendorUser)表更新入参DTO类
 *
 * @author Fish.Fei
 * @since 2021-08-23 17:48:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorUserDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = 708829808326176923L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("供应商id")
    private Long vendorId;

    @ApiModelProperty("用户id")
    private Long userId;

}
