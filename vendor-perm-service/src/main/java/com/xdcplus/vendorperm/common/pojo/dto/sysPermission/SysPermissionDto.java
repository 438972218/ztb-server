package com.xdcplus.vendorperm.common.pojo.dto.sysPermission;

import com.xdcplus.vendorperm.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.ztb.common.tool.validator.groupvlidator.IdGroupValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 权限信息表(SysPermission)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("权限信息DtO对象 参数对照对象")
public class SysPermissionDto implements Serializable {
    private static final long serialVersionUID = 445476638063847848L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @NotNull(groups = {IdGroupValidator.class, UpdateGroupValidator.class}, message = "id不能为空")
    private Long id;
    /**
     * 权限名称
     */
    @ApiModelProperty("权限名称")
    private String name;
    /**
     * 父权限id
     */
    @ApiModelProperty("父权限id")
    private Long parentId;
    /**
     * 功能的级别('menu','button')
     */
    @ApiModelProperty("功能的级别('menu','button')")
    private String level;
    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;
    /**
     * url地址
     */
    @ApiModelProperty("url地址")
    private String routeUrl;
    /**
     * 是否隐藏  1:是 0：否
     */
    @ApiModelProperty("是否隐藏  1:是 0：否")
    private Integer isHide;
    /**
     * 说明
     */
    @ApiModelProperty("说明")
    private String description;
    /**
     * 图标
     */
    @ApiModelProperty("图标")
    private String icon;
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

    @ApiModelProperty("权限字符串")
    private String permission;

}
