package com.xdcplus.vendor.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 供应商用户(VendorUser)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-08-23 17:48:28
 */
@Data
@SuppressWarnings("serial")
public class VendorUserQuery implements Serializable {
    private static final long serialVersionUID = -11570386990790251L;

    private Long id;

    private Long vendorId;

    private Long userId;

}
