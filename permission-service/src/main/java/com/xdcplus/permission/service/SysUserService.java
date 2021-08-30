package com.xdcplus.permission.service;

import com.github.pagehelper.PageInfo;
import com.xdcplus.permission.common.pojo.dto.sysuser.*;
import com.xdcplus.permission.common.pojo.entity.SysRole;
import com.xdcplus.permission.common.pojo.entity.SysUser;
import com.xdcplus.permission.common.pojo.vo.sysuser.GetAllUserAndDepartAndPostionVO;
import com.xdcplus.permission.common.pojo.vo.sysuser.GetDepartmentManagerEmployeeVoAndSysUserVoByUserNameVO;
import com.xdcplus.permission.common.pojo.vo.sysuser.SysUserVo;
import com.xdcplus.permission.common.pojo.vo.sysuser.UserPermVO;
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
     * @param userName 用户名
     * @return {@link SysUserVo}
     */
    SysUserVo queryByUserName(String userName);

    /**
     * 获取sys用户通过用户id或用户的名字
     *
     * @param getSysUserByUserIdOrUserNameDto 参数
     * @return {@link SysUserVo}
     */
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
     * 查询所有用户的用户信息，及用户及关联的部门及岗位信息
     *
     * @return {@link SysUserVo}
     */
    List<GetAllUserAndDepartAndPostionVO> findAllUserAndDepartAndPostion();


    /**
     * 根据用户名，查询部门经理的员工信息、用户信息
     *
     * @param userName 用户名
     * @return {@link GetDepartmentManagerEmployeeVoAndSysUserVoByUserNameVO}
     */
    GetDepartmentManagerEmployeeVoAndSysUserVoByUserNameVO getDepartmentManagerEmployeeVoAndSysUserVoByUserName(String userName);


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
     * 忘记密码
     *
     * @param userName 用户名
     */
    void forgetPassword(String userName);





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
