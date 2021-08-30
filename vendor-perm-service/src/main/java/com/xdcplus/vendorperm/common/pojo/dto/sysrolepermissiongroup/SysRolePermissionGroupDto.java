package com.xdcplus.vendorperm.common.pojo.dto.sysrolepermissiongroup;

import com.xdcplus.vendorperm.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.ztb.common.tool.validator.groupvlidator.IdGroupValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 角色权限组信息表(SysRolePermissionGroup)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("角色权限组信息 参数对照对象")
public class SysRolePermissionGroupDto implements Serializable {
    private static final long serialVersionUID = -61315711198327228L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @NotNull(groups = {IdGroupValidator.class, UpdateGroupValidator.class}, message = "id不能为空")
    private Long id;
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
