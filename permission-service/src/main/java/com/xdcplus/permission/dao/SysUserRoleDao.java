package com.xdcplus.permission.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xdcplus.permission.common.pojo.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色权限组信息表(SysUserRole)表数据库访问层
 *
 * @author Bullion.Yan
 * @since 2021-07-08 09:18:20
 */
public interface SysUserRoleDao extends BaseMapper<SysUserRole> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserRole queryById(Long id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUserRole 实例对象
     * @return 对象列表
     */
    List<SysUserRole> queryAll(SysUserRole sysUserRole);

    /**
     * 按用户id删除
     *
     * @param userId 用户id
     * @return {@link Integer}
     */
    Integer deleteByUserId(@Param("userId")Long userId);

    /**
     * 获取通过角色id
     *
     * @param roleId 角色id
     * @return {@link List<SysUserRole>}
     */
    List<SysUserRole> getByRoleId(@Param("roleId")Long roleId);

    /**
     * 获取通过用户id
     *
     * @param userId 用户id
     * @return {@link List<SysUserRole>}
     */
    List<SysUserRole> getByUserId(@Param("userId")Long userId);

}

