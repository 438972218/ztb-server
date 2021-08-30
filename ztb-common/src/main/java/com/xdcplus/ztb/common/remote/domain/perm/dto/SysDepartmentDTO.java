package com.xdcplus.ztb.common.remote.domain.perm.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 部门DTO
 *
 * @author Rong.Jia
 * @date 2021/08/04 13:29:33
 */
@Data
public class SysDepartmentDTO implements Serializable {

    private static final long serialVersionUID = 8037896654902157601L;

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

    /**
     *部门简称
     */
    private String shortName;

    /**
     * 部门全称
     */
    private String fullName;

    /**
     * 所属公司id
     */
    private Long companyId;

    /**
     * 所属公司Code,忽略，不需要传
     */
    private String companyCode;
    /**
     *上级部门Id
     */
    private Long parentId;

    /**
     * 上级部门Code
     */
    private String parentCode;

    /**
     * 部门编号
     */
    private String code;
    /**
     * 部门负责人
     */
    private Long manager;

    /**
     * 上级部门Code不能为空！，忽略，不需要传
     */
    private String parent;

    /**
     * 部门Code不能为空！，忽略，不需要传
     */
    private String level;
























}
