package com.xdcplus.vendorperm.common.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 部门表(SysDepartment)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-30 10:20:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDepartment implements Serializable {
    private static final long serialVersionUID = -49903930624789273L;
    /**
     * 主键
     */
    private Long id;
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

    private String shortName;

    private String fullName;

    private Long companyId;

    private String companyCode;

    private Long parentId;

    private String parentCode;
    /**
     *
     */
    private String code;
    /**
     * 部门负责人
     */
    private Long manager;


}
