package com.xdcplus.biz.remote.fallback;

import com.xdcplus.biz.remote.PermRemote;
import com.xdcplus.ztb.common.remote.domain.perm.vo.*;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 烫远程后备工厂
 *
 * @author Fish.Fei
 * @date 2021/08/17
 */
@Slf4j
@Component
public class PermRemoteFallbackFactory implements FallbackFactory<PermRemote> {

    @Override
    public PermRemote create(Throwable cause) {
        return new PermRemote() {

            @Override
            public ResponseVO<SysUserInfoVO> sysUserQueryById(Long id) {
                return null;
            }

            @Override
            public ResponseVO<SysUserInfoVO> queryByUserName(String userName) {
                return null;
            }

            @Override
            public ResponseVO<SysEmployeeVO> getEmployeeVoByUserName(String userName) {
                return null;
            }

            @Override
            public ResponseVO<SysCompanyVO> sysCompanyQueryById(Long id) {
                return null;
            }

            @Override
            public ResponseVO<List<SysCompanyVO>> getDepartmentTree() {
                return null;
            }

            @Override
            public ResponseVO judgeGroupCompany(Long id) {
                return null;
            }

            @Override
            public ResponseVO<List<SysRoleVO>> queryByUserId(Long userId) {
                return null;
            }

            @Override
            public ResponseVO<GetDepartmentManagerEmployeeVoAndSysUserVoByUserNameVO> getDepartmentManagerEmployeeVoAndSysUserVoByUserName(String userName) {
                return null;
            }
        };
    }










}
