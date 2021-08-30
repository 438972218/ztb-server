package com.xdcplus.vendorperm.common.pojo.vo.sysrole;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@ApiModel("角色Vo")
public class SysRoleVo implements Serializable {
    private static final long serialVersionUID = -16306813213510891L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;
    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    private String name;
    /**
     * 角色描述
     */
    @ApiModelProperty("角色描述")
    private String description;
    /**
     * 所属分部（公司）
     */
    @ApiModelProperty("所属分部（公司)")
    private Long companyId;

    @ApiModelProperty("所属分部（公司)名称")
    private String companyName;
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
     * 角色标识
     */
    @ApiModelProperty("角色标识")
    private String mark;
    /**
     * 系统权限组列表
     */
    @ApiModelProperty("系统权限组列表")
    private List<Long> sysPermissionGroupList=new ArrayList<>();

}
