package com.xdcplus.permission.common.pojo.dto.sysregion;

import com.xdcplus.permission.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.ztb.common.tool.validator.groupvlidator.IdGroupValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 行政区域表(SysRegion)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("行政区域Dto对象参数对照对象")
public class SysRegionDto implements Serializable {
    private static final long serialVersionUID = -51318149706399898L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @NotNull(groups = {IdGroupValidator.class, UpdateGroupValidator.class}, message = "id不能为空")
    private Long id;

    @ApiModelProperty("行政区域名称")
    private String name;
    /**
     * 父id
     */
    @ApiModelProperty("父id")
    private Long parentId;
    /**
     * 祖级列表
     */
    @ApiModelProperty("祖级列表")
    private String ancestors;
    /**
     * 类型（1：省，2：市，3：区）
     */
    @ApiModelProperty("类型（1：省，2：市，3：区）")
    private String type;
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


}
