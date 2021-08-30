package com.xdcplus.vendorperm.common.pojo.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 行政区域表(SysRegion)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRegion implements Serializable {
    private static final long serialVersionUID = -51318149706399898L;
    /**
     * 主键
     */
    private Long id;

    private String name;
    /**
     * 父id
     */
    private Long parentId;
    /**
     * 祖级列表
     */
    private String ancestors;
    /**
     * 类型（1：省，2：市，3：区）
     */
    private String type;
    /**
     * 创建人
     */
    private String createdUser;
    /**
     * 创建时间
     */
    private Long createdTime;
    /**
     * 更新人
     */
    private String updatedUser;
    /**
     * 更新时间
     */
    private Long updatedTime;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 是否已经逻辑删除（0：未删除 1：已删除）
     */
    private Integer deleted;


}
