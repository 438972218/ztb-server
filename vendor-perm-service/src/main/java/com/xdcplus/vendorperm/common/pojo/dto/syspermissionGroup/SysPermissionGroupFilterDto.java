package com.xdcplus.vendorperm.common.pojo.dto.syspermissionGroup;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 权限组表(SysPermissionGroup)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("权限组过滤查询 参数对照对象")
public class SysPermissionGroupFilterDto extends PageDTO implements Serializable {
    private static final long serialVersionUID = 117479615058440287L;

    /**
     * 权限组标识
     */
    @ApiModelProperty("权限组标识")
    private String code;

}
