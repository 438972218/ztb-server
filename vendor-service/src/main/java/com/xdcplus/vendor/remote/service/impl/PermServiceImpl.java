package com.xdcplus.vendor.remote.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.xdcplus.vendor.remote.PermRemote;
import com.xdcplus.vendor.remote.service.PermService;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.remote.domain.perm.dto.RegisterUserDTO;
import com.xdcplus.ztb.common.remote.domain.perm.vo.SysUserInfoVO;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.ztb.common.tool.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 烫发服务impl
 *
 * @author Fish.Fei
 * @date 2021/08/17
 */
@Slf4j
@Service("permService")
public class PermServiceImpl implements PermService {

    @Autowired
    PermRemote permRemote;

    @Override
    public SysUserInfoVO queryByUserName(String userName) {
        ResponseVO<SysUserInfoVO> responseVO = permRemote.queryByUserName(userName);
        if (!NumberConstant.ZERO.equals(responseVO.getCode()) || ObjectUtil.isNull(responseVO.getData())) {

            log.error("queryByUserName failed");
            throw new ZtbWebException(ResponseEnum.API_USER_SELECT_FAIL, responseVO.getMessage());
        }
        return ResponseUtils.getResponse(responseVO);
    }

    @Override
    public SysUserInfoVO registerUser(RegisterUserDTO registerUserDto) {
        ResponseVO<SysUserInfoVO> responseVO = permRemote.registerUser(registerUserDto);
        if (!NumberConstant.ZERO.equals(responseVO.getCode()) || ObjectUtil.isNull(responseVO.getData())) {

            log.error("registerUser failed");
            throw new ZtbWebException(ResponseEnum.API_USER_SELECT_FAIL, responseVO.getMessage());
        }
        return ResponseUtils.getResponse(responseVO);
    }

}
