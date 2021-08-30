package com.xdcplus.vendorperm.dao;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.vendorperm.common.pojo.entity.SysPermission;
import com.xdcplus.vendorperm.common.pojo.query.sysPermission.SysPermissionFilterQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限信息表(SysPermission)表数据库访问层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:13
 */
public interface SysPermissionDao extends IBaseMapper<SysPermission> {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysPermissionFilterQuery 实例对象
     * @return 对象列表
     */
    List<SysPermission> getSysPermissionByCondition(@Param("sysPermissionFilterQuery") SysPermissionFilterQuery sysPermissionFilterQuery);


    SysPermission getSysPermissionByNameAndNoId(@Param("name") String name, @Param("id") Long id);

    /**
     * 根据权限组id获取权限信息
     *
     * @param permissionGroupId 权限组id
     * @return {@link List<SysPermission>}
     */
    List<SysPermission> getPermissionByPermissionGroupId(@Param("permissionGroupId")Long permissionGroupId);


    /**
     * 根据角色ids获取权限列表
     *
     * @param roleId 角色id列表
     * @return {@link List<SysPermission>}
     */
    List<SysPermission>  getPermissionByRoleId(@Param("roleId")Long roleId);

}

