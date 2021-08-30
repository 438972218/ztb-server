package com.xdcplus.vendorperm.common.pojo.vo.syspermissiongrouppermission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 权限组与权限关联表(SysPermissionGroupPermission)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:05
 */
@Data
@ApiModel("权限组与权限关联Vo")
public class SysPermissionGroupPermissionVo implements Serializable {
    private static final long serialVersionUID = 555170689964178958L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;
    /**
     * 权限组ID
     */
    @ApiModelProperty("权限组ID")
    private Long permissionGroupId;
    /**
     * 权限id
     */
    @ApiModelProperty("权限id")
    private Long permissionId;
    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createdUser;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Long createdTime;


}
