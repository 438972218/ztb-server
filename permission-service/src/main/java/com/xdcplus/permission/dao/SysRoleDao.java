package com.xdcplus.permission.dao;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.permission.common.pojo.entity.SysRole;
import com.xdcplus.permission.common.pojo.query.sysrole.SysRoleFilterQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色表(SysRole)表数据库访问层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:08:43
 */
public interface SysRoleDao extends IBaseMapper<SysRole> {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysRoleFilterQuery 实例对象
     * @return 对象列表
     */
    List<SysRole> getSysRolePageByCondition(@Param("sysRoleFilterQuery") SysRoleFilterQuery sysRoleFilterQuery);

    /**
     * 通过角色的名字查询角色
     *
     * @param roleName 角色名
     * @return {@link List<SysRole>}
     */
    SysRole getSysRoleByRoleNameAndNoId(@Param("roleName") String roleName,@Param("id") Long id);

    List<SysRole> getByCompanyId(@Param("companyId") Long companyId);

    /**
     * 根据用户id查询角色信息
     *
     * @param userId 用户id
     * @return {@link List<SysRole>}
     */
    List<SysRole> getByUserId(@Param("userId") Long userId);

    /**
     * 根据用户id获取角色标识
     * @param userName 用户名
     * @return
     */
    List<String> getSysRoleMarkByUserId(@Param("userName") String userName);


}

