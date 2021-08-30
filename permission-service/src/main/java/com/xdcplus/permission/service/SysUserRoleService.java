package com.xdcplus.permission.service;

import com.xdcplus.permission.common.pojo.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色权限组信息表(SysUserRole)表服务接口
 *
 * @author Bullion.Yan
 * @since 2021-07-08 09:18:21
 */
public interface SysUserRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserRole queryById(Long id);

    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    SysUserRole insert(SysUserRole sysUserRole);



    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    Integer deleteByUserId(Long userId);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    List<SysUserRole> getByRoleId(Long roleId);

    /**
     * 获取通过用户id
     *
     * @param userId 用户id
     * @return {@link List<SysUserRole>}
     */
    List<SysUserRole> getByUserId(@Param("userId")Long userId);

}
