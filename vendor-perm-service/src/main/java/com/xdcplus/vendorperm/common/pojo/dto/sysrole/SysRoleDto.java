package com.xdcplus.vendorperm.common.pojo.dto.sysrole;

import com.xdcplus.vendorperm.common.validator.groupvlidator.InsertGroupValidator;
import com.xdcplus.vendorperm.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.ztb.common.tool.validator.groupvlidator.IdGroupValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色表(SysRole)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:08:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(" 角色参数对照对象")
public class SysRoleDto implements Serializable {
    private static final long serialVersionUID = -16306813213510891L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @NotNull(groups = {IdGroupValidator.class,UpdateGroupValidator.class}, message = "id不能为空")
    private Long id;
    /**
     * 角色标识
     */
    @ApiModelProperty("角色标识")
    private String mark;
    /**
     * 角色名称
     */

    @ApiModelProperty("角色名称")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "角色名称不能为空")
    private String name;
    /**
     * 角色描述
     */
    @ApiModelProperty("角色描述")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "角色描述不能为空")
    private String description;
    /**
     * 所属分部（公司）
     */
    @ApiModelProperty("所属分部（公司）")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "所属分部（公司）不能为空")
    private Long companyId;

    /**
     * 系统权限组列表
     */
    @ApiModelProperty("系统权限组列表")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "系统权限组列表不能为空")
    private List<Long> sysPermissionGroupList=new ArrayList<>();

}
