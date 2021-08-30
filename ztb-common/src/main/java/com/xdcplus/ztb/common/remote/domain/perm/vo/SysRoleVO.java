package com.xdcplus.ztb.common.remote.domain.perm.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色VO
 *
 * @author Rong.Jia
 * @date 2021/08/04 14:32:03
 */
@Data
public class SysRoleVO implements Serializable {

    private static final long serialVersionUID = 2871856592679980257L;

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
     * 所属分部（公司)名称
     */
    private String companyName;
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
     * 系统权限组列表
     */
    private List<Long> sysPermissionGroupList=new ArrayList<>();




















}
