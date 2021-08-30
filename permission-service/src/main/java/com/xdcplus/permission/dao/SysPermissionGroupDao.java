package com.xdcplus.permission.dao;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.permission.common.pojo.entity.SysPermission;
import com.xdcplus.permission.common.pojo.entity.SysPermissionGroup;
import com.xdcplus.permission.common.pojo.query.syspermissionGroup.SysPermissionGroupFilterQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限组表(SysPermissionGroup)表数据库访问层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:12
 */
public interface SysPermissionGroupDao extends IBaseMapper<SysPermissionGroup> {


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysPermissionGroupFilterQuery 实例对象
     * @return 对象列表
     */
    List<SysPermissionGroup> getSysPermissionGroupByCondition(@Param("sysPermissionGroupFilterQuery") SysPermissionGroupFilterQuery sysPermissionGroupFilterQuery);


    SysPermissionGroup getCodeByCodeAndNoId(@Param("code") String code, @Param("id") Long id);

    /**
     * 获取许可通过角色id
     *
     * @param roleId 角色id
     * @return {@link SysPermission}
     */
    List<SysPermissionGroup> getPermissionGroupByRoleId(@Param("roleId") Long roleId);
}

