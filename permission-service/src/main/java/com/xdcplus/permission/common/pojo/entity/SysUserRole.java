package com.xdcplus.permission.common.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 角色权限组信息表(SysUserRole)实体类
 *
 * @author Bullion.Yan
 * @since 2021-07-08 09:18:20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 862320771515415275L;
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
     * 角色编号
     */
    private Long roleId;
    /**
     * 用户id
     */
    private Long userId;


}
