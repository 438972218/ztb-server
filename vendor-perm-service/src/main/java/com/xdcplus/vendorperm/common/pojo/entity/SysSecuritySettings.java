package com.xdcplus.vendorperm.common.pojo.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 安全设置表(SysSecuritySettings)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysSecuritySettings implements Serializable {
    private static final long serialVersionUID = 382133632247734092L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 密码最小长度
     */
    private Integer passwordMinLength;
    /**
     * 密码失败次数限制
     */
    private Integer passwordFailNumberLimit;
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
