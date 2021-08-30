package com.xdcplus.vendorperm.service;

import com.github.pagehelper.PageInfo;
import com.xdcplus.vendorperm.common.pojo.dto.sysuser.*;
import com.xdcplus.vendorperm.common.pojo.entity.SysRole;
import com.xdcplus.vendorperm.common.pojo.vo.sysuser.SysUserVo;
import com.xdcplus.vendorperm.common.pojo.vo.sysuser.UserPermVO;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;

import java.util.List;

/**
 * 用户信息表(SysUser)表服务接口
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:09
 */
public interface SysUserService {


    /**
     * 获取用户信息分页通过条件
     *
     * @param sysUserFilterDto 系统用户dto
     * @return {@link PageInfo<SysUserVo>}
     */
    PageVO<SysUserVo> getSysUserServicePageByCondition(SysUserFilterDto sysUserFilterDto);


    SysUserVo getSysUserManagerByDepartmentId(Long departmentId);

    /**
     * 通过主键id查询
     *
     * @param id id
     * @return {@link SysRole}
     */
    SysUserVo queryById(Long id);

    /**
     * 根据用户账号获取用户信息
     *
     * @param
     * @return {@link SysUserVo}
     */
    SysUserVo queryByUserName(String userName);

    SysUserVo getSysUserByUserIdOrUserName(GetSysUserByUserIdOrUserNameDto getSysUserByUserIdOrUserNameDto);


    /**
     * 插入
     *
     * @param sysUserDto 用户信息dto
     */
    void insert(SysUserDto sysUserDto,String loginUser);

    /**
     * 注册用户
     *
     * @param registerUserDto 系统用户dto
     */
    SysUserVo registerUser(RegisterUserDto registerUserDto);




    /**
     * 更新通过id
     *
     * @param sysUserDto 用户信息dto
     */
    void updateById(SysUserDto sysUserDto,String loginUser);

    /**
     * 更新密码通过id
     *
     * @param updatePasswordByIdDto 更新密码通过id dto
     */
    void updatePasswordById(UpdatePasswordByIdDto updatePasswordByIdDto,String loginUser);


    /**
     * 通过主键id删除
     *
     * @param id id
     */
    void deleteById(Long id,String loginUser);

    /**
     * 获取用户通过用户id或用户名
     *
     * @param id       id
     * @param userName 用户名
     * @return {@link UserPermVO}
     */
    UserPermVO getUserByUserIdOrUserName(Long id, String userName);

    /**
     * 获取总经理用户信息
     *
     * @return {@link SysUserVo}
     */
    SysUserVo getGeneralManagerSysUser();

}
