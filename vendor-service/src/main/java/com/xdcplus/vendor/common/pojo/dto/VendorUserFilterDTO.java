package com.xdcplus.vendor.common.pojo.dto;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 供应商用户(VendorUser)表查询入参DTO类
 *
 * @author Fish.Fei
 * @since 2021-08-23 17:48:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class VendorUserFilterDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = -47263570190779667L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("供应商id")
    private Long vendorId;

    @ApiModelProperty("用户id")
    private Long userId;

}
