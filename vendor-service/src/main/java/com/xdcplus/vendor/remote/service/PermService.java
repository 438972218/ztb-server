package com.xdcplus.vendor.remote.service;

import com.xdcplus.ztb.common.remote.domain.perm.dto.RegisterUserDTO;
import com.xdcplus.ztb.common.remote.domain.perm.vo.SysCompanyVO;
import com.xdcplus.ztb.common.remote.domain.perm.vo.SysUserInfoVO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 烫发服务
 *
 * @author Fish.Fei
 * @date 2021/08/17
 */
public interface PermService {

    SysUserInfoVO queryByUserName(String userName);

    SysUserInfoVO registerUser(RegisterUserDTO registerUserDto);

}
