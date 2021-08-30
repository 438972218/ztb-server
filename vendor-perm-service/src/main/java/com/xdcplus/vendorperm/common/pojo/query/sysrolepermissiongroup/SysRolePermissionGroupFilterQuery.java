package com.xdcplus.vendorperm.common.pojo.query.sysrolepermissiongroup;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 角色权限组信息表(SysRolePermissionGroup)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("角色权限组信息过滤查询 参数对照对象")
public class SysRolePermissionGroupFilterQuery extends PageDTO implements Serializable {
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
