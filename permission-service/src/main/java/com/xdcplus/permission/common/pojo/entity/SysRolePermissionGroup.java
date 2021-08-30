package com.xdcplus.permission.common.pojo.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色权限组信息表(SysRolePermissionGroup)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRolePermissionGroup implements Serializable {
    private static final long serialVersionUID = -61315711198327228L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 创建人
     */
    private String createdUser;
    /**
     * 创建时间
     */
    private Long createdTime;
    /**
     * 角色编号
     */
    private Long roleId;
    /**
     * 权限组编号
     */
    private Long permissionGroupId;


}
