package com.xdcplus.vendorperm.common.pojo.vo.sysrolepermissiongroup;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色权限组信息表(SysRolePermissionGroup)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:08
 */
@Data
public class SysRolePermissionGroupVo implements Serializable {
    private static final long serialVersionUID = -61315711198327228L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;
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
    /**
     * 角色编号
     */
    @ApiModelProperty("角色编号")
    private Long roleId;
    /**
     * 权限组编号
     */
    @ApiModelProperty("权限组编号")
    private Long permissionGroupId;


}
