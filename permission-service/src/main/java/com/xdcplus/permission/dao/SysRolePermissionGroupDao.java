package com.xdcplus.permission.dao;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.permission.common.pojo.entity.SysRolePermissionGroup;
import com.xdcplus.permission.common.pojo.query.sysrolepermissiongroup.SysRolePermissionGroupFilterQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色权限组信息表(SysRolePermissionGroup)表数据库访问层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:08
 */
public interface SysRolePermissionGroupDao extends IBaseMapper<SysRolePermissionGroup> {


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysRolePermissionGroupFilterQuery 实例对象
     * @return 对象列表
     */
    List<SysRolePermissionGroup> getSysRolePermissionGroupByCondition(@Param("sysRolePermissionGroupFilterQuery") SysRolePermissionGroupFilterQuery sysRolePermissionGroupFilterQuery);

    /**
     * 按角色删除id
     *
     * @param roleId 角色id
     */
    void deleteByRoleId(@Param("roleId") Long roleId);
}

