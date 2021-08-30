package com.xdcplus.permission.dao;

import com.xdcplus.permission.common.pojo.vo.sysuser.GetAllUserAndDepartAndPostionVO;
import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.permission.common.pojo.entity.SysUser;
import com.xdcplus.permission.common.pojo.query.sysuser.SysUserFilterQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息表(SysUser)表数据库访问层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:09
 */
public interface SysUserDao extends IBaseMapper<SysUser>{


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUserFilterQuery 实例对象
     * @return 对象列表
     */
    List<SysUser> getSysUserByCondition(@Param("sysUserFilterQuery") SysUserFilterQuery sysUserFilterQuery);


    SysUser getSysUserByUserNameAndNoId(@Param("userName") String userName, @Param("id") Long id);

    SysUser getSysUserByEmail(@Param("mail") String mail);


    /**
     *根据用户id 或者用户名获取用户信息
     *
     * @param userName 用户名
     * @param id       id
     * @return {@link SysUser}
     */
    SysUser getSysUserByUserIdOrUserName(@Param("userName") String userName, @Param("id") Long id);
    List<SysUser> getSysUserByEmployeeAndNoId(@Param("employeeId") Long employeeId,@Param("id") Long id);

    /**
     * 根据员工id获取用户信息
     * @param employeeId 员工id
     * @return
     */
    SysUser getSysUserByEmployeeId(@Param("employeeId") Long employeeId);

    /**
     * 根据用户名查询用户信息
     * @param userName 用户名
     * @return
     */
    SysUser getSysUserByUserName(@Param("userName") String userName);

    /**
     * 查询所有用户的用户信息，及用户及关联的部门及岗位信息
     *
     * @return {@link SysUser}
     */
    List<GetAllUserAndDepartAndPostionVO> findAllUserAndDepartAndPostion();
}

