package com.xdcplus.vendorperm.common.pojo.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限组表(SysPermissionGroup)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysPermissionGroup implements Serializable {
    private static final long serialVersionUID = 117479615058440287L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 权限组标识
     */
    private String code;
    /**
     * 权限组说明
     */
    private String description;
    /**
     * 备注
     */
    private String remark;
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
