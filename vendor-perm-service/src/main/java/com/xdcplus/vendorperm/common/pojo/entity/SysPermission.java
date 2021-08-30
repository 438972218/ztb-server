package com.xdcplus.vendorperm.common.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 权限信息表(SysPermission)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 445476638063847848L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 父权限id
     */
    private Long parentId;
    /**
     * 功能的级别('menu','button')
     */
    private String level;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * url地址
     */
    private String routeUrl;
    /**
     * 是否隐藏  1:是 0：否
     */
    private Integer isHide;
    /**
     * 说明
     */
    private String description;
    /**
     * 图标
     */
    private String icon;
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

    private String permission;


}
