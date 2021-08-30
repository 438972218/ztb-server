package com.xdcplus.vendorperm.common.pojo.vo.sysregion;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 行政区域表(SysRegion)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:06
 */
@Data
@ApiModel("行政区域Vo")
public class SysRegionVo implements Serializable {
    private static final long serialVersionUID = -51318149706399898L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
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

    @ApiModelProperty("children")
    @JsonInclude(value=JsonInclude.Include.NON_NULL)
    protected List<SysRegionVo> childrens ;

    private String title;

    private Long key;

    public void setName(String name) {
        this.title=name;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
        this.key=id;
    }
}
