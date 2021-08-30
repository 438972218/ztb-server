package com.xdcplus.vendor.remote.fallback;

import com.xdcplus.vendor.remote.PermRemote;
import com.xdcplus.ztb.common.remote.domain.perm.dto.RegisterUserDTO;
import com.xdcplus.ztb.common.remote.domain.perm.vo.SysRoleVO;
import com.xdcplus.ztb.common.remote.domain.perm.vo.SysUserInfoVO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 权限收放降级处理
 *
 * @author Rong.Jia
 * @date 2021/08/03 16:27:47
 */
@Slf4j
@Component
public class PermRemoteFallbackFactory implements FallbackFactory<PermRemote> {

    @Override
    public PermRemote create(Throwable cause) {
        return new PermRemote() {
            @Override
            public ResponseVO registerUser(RegisterUserDTO registerUserDto) {
                return null;
            }

            @Override
            public ResponseVO<String> getSysRoleMarkByUserName(String userName) {
                return null;
            }

            @Override
            public ResponseVO<SysUserInfoVO> queryByUserName(@NotNull(message = "userName不能为空") String userName) {
                return null;
            }
        };
    }










}
