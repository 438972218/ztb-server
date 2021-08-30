package com.xdcplus.permission.common.pojo.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色表(SysRole)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:08:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRole implements Serializable {
    private static final long serialVersionUID = -16306813213510891L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色描述
     */
    private String description;
    /**
     * 所属分部（公司）
     */
    private Long companyId;
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

    private String mark;


}
