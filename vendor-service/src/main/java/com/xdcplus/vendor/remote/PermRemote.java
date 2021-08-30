package com.xdcplus.vendor.remote;

import com.xdcplus.vendor.remote.fallback.PermRemoteFallbackFactory;
import com.xdcplus.ztb.common.remote.domain.perm.dto.RegisterUserDTO;
import com.xdcplus.ztb.common.remote.domain.perm.vo.SysUserInfoVO;
import com.xdcplus.ztb.common.tool.constants.ServiceConstant;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 权限收放调用
 *
 * @author Rong.Jia
 * @date 2021/08/03 16:21:01
 */
@Component
@RequestMapping("/api")
@FeignClient(value = ServiceConstant.VENDOR_PERM_SERVICE, fallbackFactory = PermRemoteFallbackFactory.class)
public interface PermRemote {

    @PostMapping(value = "registerUser", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<SysUserInfoVO> registerUser(@Validated @RequestBody RegisterUserDTO registerUserDto);

    @GetMapping(value = "getSysRoleMarkByUserName/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<String> getSysRoleMarkByUserName(@PathVariable("userName") String userName);

    @GetMapping(value = "queryByUserName/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<SysUserInfoVO> queryByUserName(@PathVariable("userName")
                                                 @NotNull(message = "userName不能为空")String userName);

}
