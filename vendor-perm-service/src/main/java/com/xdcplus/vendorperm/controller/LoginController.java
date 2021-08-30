package com.xdcplus.vendorperm.controller;

import cn.hutool.core.lang.Assert;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.xdcplus.vendorperm.common.pojo.dto.auth.UserLoginDTO;
import com.xdcplus.vendorperm.service.LoginService;
import com.xdcplus.vendorperm.common.pojo.vo.sysuser.UserPermVO;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * 登录/登出管理 Controller层
 *
 * @author Rong.Jia
 * @date 2019/04/17 16:01
 */
@Api(value = "认证管理", tags = "认证模块")
@Slf4j
@Validated
@RestController
@RequestMapping("/anon/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private KeyPair keyPair;

    @ApiOperation(value = "Key", hidden = true)
    @GetMapping("getPublicKey")
    public Map<String, Object> loadPublicKey() {
        log.info("loadPublicKey {}", System.currentTimeMillis());
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }

    @ApiOperation(value = "登录")
    @PostMapping(value = "basicLogin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<UserPermVO> basicLogin(@RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request, HttpServletResponse response) {

        log.info("basicLogin {}", userLoginDTO.toString());

        UserPermVO userPermVO = loginService.login(userLoginDTO, request, response);

        return ResponseVO.success(userPermVO);
    }

    @ApiOperation("退出登录")
    @PostMapping(value = "logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO logout(HttpServletRequest request, HttpServletResponse response) {

        log.debug("logout {}", System.currentTimeMillis());

        loginService.logout(request, response);

        return ResponseVO.success();

    }

    @ApiOperation(value = "获取验证码")
    @RequestMapping(value = "getCode/{account}", method = RequestMethod.GET, produces = {MediaType.IMAGE_PNG_VALUE})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "account", dataType = "string", value = "账号", required = true)
    })
    public void getCode(HttpServletResponse response, HttpServletRequest request,
                        @PathVariable("account") @NotBlank(message = "登录账号不能为空") String account) {

        log.info("getCode 获取验证码 account {}", account);

        loginService.getPngCode(account, request, response);
    }

    @ApiOperation(value = "校验用户是否存在")
    @RequestMapping(value = "checkUser/{account}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "account", dataType = "string", value = "登录账号", required = true)
    })
    public ResponseVO checkUser(@PathVariable("account") @NotBlank(message = "登录账号不能为空") String account) {

        log.info("checkUser {}", account);

        Assert.notBlank(account, ResponseEnum.ACCOUNT_IS_EMPTY.getMessage());

        loginService.checkUser(account);

        return ResponseVO.success();

    }


}
