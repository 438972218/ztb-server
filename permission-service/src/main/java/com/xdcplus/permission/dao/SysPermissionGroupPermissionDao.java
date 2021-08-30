package com.xdcplus.permission.dao;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.permission.common.pojo.entity.SysPermissionGroupPermission;
import com.xdcplus.permission.common.pojo.query.sysPermissiongrouppermission.SysPermissionGroupPermissionFilterQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限组与权限关联表(SysPermissionGroupPermission)表数据库访问层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:05
 */
public interface SysPermissionGroupPermissionDao extends IBaseMapper<SysPermissionGroupPermission> {


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysPermissionGroupPermissionFilterQuery 实例对象
     * @return 对象列表
     */
    List<SysPermissionGroupPermission> getPermissionGroupPermissionByCondition(SysPermissionGroupPermissionFilterQuery sysPermissionGroupPermissionFilterQuery);
    Integer deleteBySysPermissionGroupId(@Param("permissionGroupId") Long permissionGroupId);
}

