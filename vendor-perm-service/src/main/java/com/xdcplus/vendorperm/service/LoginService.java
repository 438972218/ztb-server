package com.xdcplus.vendorperm.service;

import com.xdcplus.vendorperm.common.pojo.dto.auth.UserLoginDTO;
import com.xdcplus.vendorperm.common.pojo.vo.sysuser.UserPermVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录业务层接口
 *
 * @author Rong.Jia
 * @date 2019/08/30 19:20
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param userLoginDTO 登录信息
     * @param request
     * @param response
     * @return UserPermVO 用户信息
     * @date 2019/08/30 19:23:22
     * @author Rong.Jia
     */
    UserPermVO login(UserLoginDTO userLoginDTO, HttpServletRequest request, HttpServletResponse response);

    /**
     * 登出
     *
     * @param request
     * @param response
     * @date 2019/08/30 19:23:22
     * @author Rong.Jia
     */
    void logout(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取验证码
     *
     * @param account  账号
     * @param request
     * @param response
     * @date 2019/08/30 19:23:22
     * @author Rong.Jia
     */
    void getPngCode(String account, HttpServletRequest request, HttpServletResponse response);

    /**
     * 检测用户
     *
     * @param account 账号
     * @date 2019/08/30 19:23:22
     * @author Rong.Jia
     */
    void checkUser(String account);


}

