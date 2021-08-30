package com.xdcplus.permission.common.pojo.vo.syspermissionGroup;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统权限组
 * 权限组表(SysPermissionGroup)实体类
 *
 * @author Bullion.Yan
 * @date 2021/06/28
 * @since 2021-06-28 13:10:12
 */
@Data
@ApiModel("权限组Vo")
public class SysPermissionGroupVo implements Serializable {
    private static final long serialVersionUID = 117479615058440287L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;
    /**
     * 权限组标识
     */
    @ApiModelProperty("权限组标识")
    private String code;
    /**
     * 权限组说明
     */
    @ApiModelProperty("权限组说明")
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
    List<Long> permissionList=new ArrayList<>();
}
