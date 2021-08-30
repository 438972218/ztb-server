package com.xdcplus.biz.remote.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.xdcplus.biz.remote.PermRemote;
import com.xdcplus.biz.remote.service.PermService;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.remote.domain.perm.vo.*;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.ztb.common.tool.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public SysCompanyVO sysCompanyQueryById(Long id) {
        ResponseVO<SysCompanyVO> responseVO = permRemote.sysCompanyQueryById(id);
        if (!NumberConstant.ZERO.equals(responseVO.getCode()) || ObjectUtil.isNull(responseVO.getData())) {

            log.error("sysCompanyQueryById failed");
            throw new ZtbWebException(ResponseEnum.API_COMPANY_SELECT_FAIL, responseVO.getMessage());
        }
        return ResponseUtils.getResponse(responseVO);
    }

    @Override
    public List<SysCompanyVO> getDepartmentTree() {
        ResponseVO<List<SysCompanyVO>> responseVO = permRemote.getDepartmentTree();
        if (!NumberConstant.ZERO.equals(responseVO.getCode()) || ObjectUtil.isNull(responseVO.getData())) {

            log.error("getDepartmentTree failed");
            throw new ZtbWebException(ResponseEnum.API_COMPANY_SELECT_FAIL, responseVO.getMessage());
        }
        return ResponseUtils.getResponse(responseVO);
    }

    @Override
    public ResponseVO judgeGroupCompany(Long id) {
        ResponseVO responseVO = permRemote.judgeGroupCompany(id);
        if (!NumberConstant.ZERO.equals(responseVO.getCode()) || ObjectUtil.isNull(responseVO.getData())) {

            log.error("judgeGroupCompany failed");
            throw new ZtbWebException(ResponseEnum.API_COMPANY_SELECT_FAIL, responseVO.getMessage());
        }
        return responseVO;
    }

    @Override
    public List<Long> queryByUserId(Long userId) {
        ResponseVO<List<SysRoleVO>> responseVO = permRemote.queryByUserId(userId);
        if (!NumberConstant.ZERO.equals(responseVO.getCode()) || ObjectUtil.isNull(responseVO.getData())) {

            log.error("queryByUserId failed");
            throw new ZtbWebException(ResponseEnum.API_ROLE_SELECT_FAIL, responseVO.getMessage());
        }
        List<SysRoleVO> sysRoleVOS = ResponseUtils.getResponse(responseVO);

        if (CollUtil.isEmpty(sysRoleVOS)) {
            log.error("queryByUserId() role found failed");
            throw new ZtbWebException(ResponseEnum.API_ROLE_SELECT_FAIL);
        }
        List<Long> roleIds = sysRoleVOS.stream().map(s -> s.getId()).collect(Collectors.toList());
        return roleIds;
    }

    @Override
    public SysUserInfoVO sysUserQueryById(Long id) {
        ResponseVO<SysUserInfoVO> responseVO = permRemote.sysUserQueryById(id);
        if (!NumberConstant.ZERO.equals(responseVO.getCode()) || ObjectUtil.isNull(responseVO.getData())) {

            log.error("sysUserQueryById failed");
            throw new ZtbWebException(ResponseEnum.API_USER_SELECT_FAIL, responseVO.getMessage());
        }
        return ResponseUtils.getResponse(responseVO);
    }

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
    public SysEmployeeVO getEmployeeVoByUserName(String userName) {
        ResponseVO<SysEmployeeVO> responseVO = permRemote.getEmployeeVoByUserName(userName);
        if (!NumberConstant.ZERO.equals(responseVO.getCode()) || ObjectUtil.isNull(responseVO.getData())) {

            log.error("getEmployeeVoByUserName failed");
            throw new ZtbWebException(ResponseEnum.API_USER_SELECT_FAIL, responseVO.getMessage());
        }
        return ResponseUtils.getResponse(responseVO);
    }

    @Override
    public GetDepartmentManagerEmployeeVoAndSysUserVoByUserNameVO getDepartmentManagerEmployeeVoAndSysUserVoByUserName(String userName) {
        ResponseVO<GetDepartmentManagerEmployeeVoAndSysUserVoByUserNameVO> responseVO = permRemote.getDepartmentManagerEmployeeVoAndSysUserVoByUserName(userName);
        if (!NumberConstant.ZERO.equals(responseVO.getCode()) || ObjectUtil.isNull(responseVO.getData())) {

            log.error("getDepartmentManagerEmployeeVoAndSysUserVoByUserName failed");
            throw new ZtbWebException(ResponseEnum.API_USER_SELECT_FAIL, responseVO.getMessage());
        }
        return ResponseUtils.getResponse(responseVO);
    }

}
