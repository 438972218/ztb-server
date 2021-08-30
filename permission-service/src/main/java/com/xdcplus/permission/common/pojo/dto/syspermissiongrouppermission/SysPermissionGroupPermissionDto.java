package com.xdcplus.permission.common.pojo.dto.syspermissiongrouppermission;

import com.xdcplus.permission.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.ztb.common.tool.validator.groupvlidator.IdGroupValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 权限组与权限关联表(SysPermissionGroupPermission)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("权限组与权限关联Dto 参数对照对象")
public class SysPermissionGroupPermissionDto implements Serializable {
    private static final long serialVersionUID = 555170689964178958L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @NotNull(groups = {IdGroupValidator.class, UpdateGroupValidator.class}, message = "id不能为空")
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



}
