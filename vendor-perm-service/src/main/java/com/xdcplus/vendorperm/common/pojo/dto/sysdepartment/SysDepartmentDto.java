package com.xdcplus.vendorperm.common.pojo.dto.sysdepartment;

import com.xdcplus.vendorperm.common.validator.groupvlidator.InsertGroupValidator;
import com.xdcplus.vendorperm.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.ztb.common.tool.validator.groupvlidator.IdGroupValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 部门表(SysDepartment)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("部门Dto")
public class SysDepartmentDto implements Serializable {
    private static final long serialVersionUID = 240653071251559074L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @NotNull(groups = {IdGroupValidator.class, UpdateGroupValidator.class}, message = "id不能为空")
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
     * 更新人
     */
    @ApiModelProperty("更新人")
    private String updatedUser;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Long updatedTime;
    /**
     * 版本号
     */
    @ApiModelProperty("版本号")
    private Integer version;
    /**
     * 是否已经逻辑删除（0：未删除 1：已删除）
     */
    @ApiModelProperty("主键")
    private Integer deleted;
    /**
     *部门简称
     */
    @ApiModelProperty("部门简称")
    @NotBlank(message = "部门简称不能为空！")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "部门简称不能为空")
    private String shortName;
    /**
     * 部门全称
     */
    @ApiModelProperty("部门全称")
    @NotBlank(message = "部门全称不能为空！")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "部门全称不能为空")
    private String fullName;
    /**
     * 所属公司id
     */
    @ApiModelProperty("所属公司id")
    @NotBlank(message = "所属公司不能为空！")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "所属公司不能为空")
    private Long companyId;
    @ApiModelProperty("所属公司Code,忽略，不需要传")
    private String companyCode;
    /**
     *上级部门Id
     */
    @ApiModelProperty("上级部门Id")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "上级部门不能为空")
    private Long parentId;
    /**
     * 上级部门Code
     */
    @ApiModelProperty("上级部门Code，忽略不需要传")
    private String parentCode;
    /**
     * 部门编号
     */
    @ApiModelProperty("部门编号")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "部门编号不能为空")
    private String code;
    /**
     * 部门负责人
     */
    @ApiModelProperty("部门负责人")
//    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "部门负责人不能为空")
    private Long manager;
    @NotBlank(message = "上级部门Code不能为空！，忽略，不需要传")
    private String parent;
    @NotBlank(message = "部门Code不能为空！，忽略，不需要传")
    private String level;


}
