package com.xdcplus.vendorperm.service;


import com.xdcplus.vendorperm.common.pojo.dto.sysrolepermissiongroup.SysRolePermissionGroupDto;
import com.xdcplus.vendorperm.common.pojo.entity.SysRolePermissionGroup;
import com.xdcplus.vendorperm.common.pojo.vo.sysrolepermissiongroup.SysRolePermissionGroupVo;

import java.util.List;

/**
 * 角色权限组信息表(SysRolePermissionGroup)表服务接口
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:08
 */
public interface SysRolePermissionGroupService {

    /**
     * 获取角色权限组信息
     *
     * @param sysRolePermissionGroupDto 系统角色权限组dto
     * @return {@link List<SysRolePermissionGroup>}
     */
    List<SysRolePermissionGroupVo> getSysRolePermissionGroupByCondition(SysRolePermissionGroupDto sysRolePermissionGroupDto);

    /**
     * 保存
     *
     * @param sysRoleDto 系统角色dto
     * @param loginUser  登录用户
     */
    void save(SysRolePermissionGroupDto sysRoleDto, String loginUser);


    /**
     * 按角色删除id
     *
     * @param roleId 角色id
     */
    void deleteByRoleId(Long roleId);


}
