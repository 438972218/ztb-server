package com.xdcplus.vendorperm.common.pojo.dto.syspermissionGroup;

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
 * 权限组表(SysPermissionGroup)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("权限组参数对照对象")
public class SysPermissionGroupDto implements Serializable {
    private static final long serialVersionUID = 117479615058440287L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @NotNull(groups = {IdGroupValidator.class, UpdateGroupValidator.class}, message = "id不能为空")
    private Long id;
    /**
     * 权限组标识
     */
    @ApiModelProperty("权限组标识")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "权限组标识不能为空")
    private String code;
    /**
     * 权限组说明
     */
    @ApiModelProperty("权限组说明")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "权限组说明不能为空")
    private String description;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;
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
     * 更新人
     */
    @ApiModelProperty("更新人")
    private String updatedUser;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Long updatedTime;
    @ApiModelProperty("权限信息")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "权限信息不能为空")
    List<Long> permissionList=new ArrayList<>();



}
