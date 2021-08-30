package com.xdcplus.permission.common.pojo.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 职务类别表(SysJobType)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysJobType implements Serializable {
    private static final long serialVersionUID = -22142780018667395L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 职务类别简称
     */
    private String shortName;
    /**
     * 职务类别全称
     */
    private String fullName;
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
