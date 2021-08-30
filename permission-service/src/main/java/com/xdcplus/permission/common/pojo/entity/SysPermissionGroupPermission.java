package com.xdcplus.permission.common.pojo.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限组与权限关联表(SysPermissionGroupPermission)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysPermissionGroupPermission implements Serializable {
    private static final long serialVersionUID = 555170689964178958L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 权限组ID
     */
    private Long permissionGroupId;
    /**
     * 权限id
     */
    private Long permissionId;
    /**
     * 创建人
     */
    private String createdUser;
    /**
     * 创建时间
     */
    private Long createdTime;


}
