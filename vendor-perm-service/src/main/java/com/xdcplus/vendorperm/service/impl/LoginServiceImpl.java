package com.xdcplus.vendorperm.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdcplus.vendorperm.captcha.SpecCaptcha;
import com.xdcplus.vendorperm.captcha.base.Captcha;
import com.xdcplus.vendorperm.constants.Oauth2Constant;
import com.xdcplus.vendorperm.enums.UrlEnum;
import com.xdcplus.vendorperm.common.pojo.dto.auth.Oauth2TokenDTO;
import com.xdcplus.vendorperm.common.pojo.dto.auth.UserLoginDTO;
import com.xdcplus.vendorperm.common.pojo.vo.auth.Oauth2TokenVO;
import com.xdcplus.vendorperm.security.Oauth2Token;
import com.xdcplus.vendorperm.service.LoginService;
import com.xdcplus.vendorperm.common.pojo.vo.sysuser.UserPermVO;
import com.xdcplus.vendorperm.service.SysUserService;
import com.xdcplus.ztb.common.cache.RedisUtils;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.remote.domain.perm.dto.SysUserInfoDTO;
import com.xdcplus.ztb.common.tool.constants.AuthConstant;
import com.xdcplus.ztb.common.tool.constants.NetworkProtocol;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 登录业务层接口实现类
 *
 * @author Rong.Jia
 * @date 2019/08/30 19:20
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private ServerProperties serverProperties;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserPermVO login(UserLoginDTO userLoginDTO, HttpServletRequest request, HttpServletResponse response) {

        // 账号
        String account = userLoginDTO.getAccount();

        // 查询并验证用户是否存在
        SysUserInfoDTO userInfoDTO = SysUserInfoDTO.builder().userName(account).build();
        UserPermVO userPermVO = sysUserService.getUserByUserIdOrUserName(userInfoDTO.getId(), userInfoDTO.getUserName());
        checkUser(account);

        // 校验验证码
//        checkCaptcha(userLoginDTO.getCaptcha(), account);

        //  登录次数限制
//        retryLimit(account, userLoginDTO.getPassword(), userPermVO.getPassword(), userPermVO.getSourceType());

        // token 信息产生
        Oauth2TokenVO oauth2TokenVO = generateToken(response, account, userLoginDTO.getPassword());

        userPermVO.setPassword(null);

        redisUtils.setEx(AuthConstant.PREFIX_SHIRO_REFRESH_TOKEN + oauth2TokenVO.getAccessToken(),
                JSON.toJSONString(userPermVO), NumberConstant.THREE_THOUSAND_AND_SIX_HUNDRED, TimeUnit.SECONDS);
        return userPermVO;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {

        String token = request.getHeader(AuthConstant.AUTHORIZATION_HEADER);

        if (redisUtils.hasKey(AuthConstant.PREFIX_SHIRO_REFRESH_TOKEN + token)) {
            redisUtils.delete(AuthConstant.PREFIX_SHIRO_REFRESH_TOKEN + token);
        } else {
            throw new ZtbWebException(ResponseEnum.ACCOUNT_AUTOMATIC_LOGOUT);
        }

        response.setHeader(AuthConstant.AUTHORIZATION_HEADER, null);
    }

    @Override
    public void getPngCode(String account, HttpServletRequest request, HttpServletResponse response) {

        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType(MediaType.IMAGE_PNG_VALUE);

            // jgp格式验证码 宽，高，位数。
            Captcha captcha = new SpecCaptcha(NumberConstant.ONE_HUNDRED_AND_FORTY_SIX,
                    NumberConstant.THIRTY_THREE, NumberConstant.SIX);

            //输出
            captcha.out(response.getOutputStream());

            // 正确验证码
            String vcCode = captcha.text();

            // 存入缓存, 并限制为5分钟过时
            redisUtils.getRedisTemplate().opsForValue().set(AuthConstant.PREFIX_AUTH_VC_CODE_CACHE + account, vcCode,
                    NumberConstant.FIVE, TimeUnit.MINUTES);

        } catch (Exception e) {
            log.error("getJpgCode 获取验证码异常 {}", e.getMessage());
        }

    }

    @Override
    public void checkUser(String account) {

        // 查询并验证用户是否存在
        SysUserInfoDTO userInfoDTO = SysUserInfoDTO.builder().userName(account).build();
        UserPermVO userPermVO = sysUserService.getUserByUserIdOrUserName(userInfoDTO.getId(), userInfoDTO.getUserName());
        checkUser(userPermVO);

    }

    /**
     * token 产生并出入response 中
     *
     * @param response
     * @param account  登录账号
     * @param password 密码
     * @date 2019/07/26 13:55:33
     * @author Rong.Jia
     */
    private Oauth2TokenVO generateToken(HttpServletResponse response, String account, String password) {

        Oauth2TokenDTO oauth2TokenDTO = new Oauth2TokenDTO(account, password);
        Map<String, Object> parameters = JSONObject.parseObject(JSON.toJSONString(oauth2TokenDTO),
                new TypeReference<Map<String, Object>>() {
                });

        String url = NetworkProtocol.HTTP_PROTOCOL + Oauth2Constant.LOCAL_HOST +
                StrUtil.C_COLON + serverProperties.getPort() + UrlEnum.OAUTH_TOKEN.getValue();
        String post = HttpUtil.post(url, parameters);
        Oauth2Token oAuth2AccessToken = JSONObject.parseObject(post, Oauth2Token.class);

        Oauth2TokenVO oauth2TokenVO = Oauth2TokenVO.builder()
                .accessToken(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead(Oauth2Constant.BEARER).build();

        String token = oauth2TokenVO.getAccessToken();

        // 清除可能存在的Shiro权限信息缓存
        if (redisUtils.hasKey(AuthConstant.PREFIX_SHIRO_CACHE + token)) {
            redisUtils.delete(AuthConstant.PREFIX_SHIRO_CACHE + token);
        }

        response.setHeader(AuthConstant.AUTHORIZATION_HEADER, oauth2TokenVO.getTokenHead() + token);
        response.setHeader("Access-Control-Expose-Headers", AuthConstant.AUTHORIZATION_HEADER);

        return oauth2TokenVO;
    }

    /**
     * 校验验证码
     *
     * @param captcha 验证吗
     * @param account 登录账号
     * @date 2019/07/26 13:55:33
     * @author Rong.Jia
     */
    private void checkCaptcha(String captcha, String account) {

        // 判断账号是否为空
        if (StrUtil.isBlank(account)) {
            log.error("checkCaptcha {}", account);
            throw new ZtbWebException(ResponseEnum.ACCOUNT_IS_EMPTY);

        }

        // 判断验证码是否为空
        if (StrUtil.isBlank(captcha)) {
            log.error("The verification code cannot be empty");
            throw new ZtbWebException(ResponseEnum.THE_VERIFICATION_CODE_IS_EMPTY);

        }

        // 校验验证码

        // 缓存key
        String cacheKey = AuthConstant.PREFIX_AUTH_VC_CODE_CACHE + account;

        if (!redisUtils.hasKey(cacheKey)) {
            log.error("The verification code is out of date ");
            throw new ZtbWebException(ResponseEnum.VERIFICATION_CODE_OUT_OF_DATE_PLEASE_RETRIEVE_IT_AGAIN);
        } else {

            // 正确的验证码
            String vcCode = redisUtils.get(AuthConstant.PREFIX_AUTH_VC_CODE_CACHE + account, String.class);

            // 判断验证码是否相同
            if (!StrUtil.equalsIgnoreCase(captcha, vcCode)) {

                log.error("Incorrect verification code input");
                throw new ZtbWebException(ResponseEnum.THE_VERIFICATION_CODE_IS_INCORRECT);

            }

            //  删除缓存
            redisUtils.delete(cacheKey);

        }

    }

    /**
     * 校验用户
     *
     * @param userPermVO 用户信息
     * @date 2019/07/26 13:55:33
     * @author Rong.Jia
     */
    private void checkUser(UserPermVO userPermVO) {

        if (ObjectUtil.isNull(userPermVO)) {
            log.error("checkUser {} ", "The account does not exist");
            throw new ZtbWebException(ResponseEnum.ACCOUNT_DOES_NOT_EXIST);
        } else {

            if (StrUtil.isBlank(userPermVO.getUserName())) {
                log.error("checkUser {} ", "The account userName does not exist");
                throw new ZtbWebException(ResponseEnum.ACCOUNT_DOES_NOT_EXIST);
            }

            if (!StrUtil.equals(AuthConstant.ADMINISTRATOR, userPermVO.getUserName())) {

                // 账号已关闭
                if (Validator.equal(NumberConstant.ONE.byteValue(), userPermVO.getStatus())) {
                    log.error("checkUser {}", "Account disabled");
                    throw new ZtbWebException(ResponseEnum.UNAUTHORIZED);
                }

                // 账号状态
                if (Validator.equal(userPermVO.getStatus(), NumberConstant.A_NEGATIVE.byteValue())) {
                    log.error("checkUser {}", "Account authorization expired");
                    throw new ZtbWebException(ResponseEnum.ACCOUNT_LOGIN_IS_PROHIBITED);
                } else if (Validator.equal(userPermVO.getStatus(), NumberConstant.ONE.byteValue())) {
                    log.error("checkUser {}", "Account security is prohibited");
                    throw new ZtbWebException(ResponseEnum.ACCOUNT_AUTHORIZATION_EXPIRED);
                }

            }

        }
    }

    /**
     * 登录次数限制
     *
     * @param account         登录账号
     * @param password        登录密码
     * @param correctPassword 账号正确密码
     * @param sourceType      数据来源类型，1- 系统添加, 2- ldap同步
     * @date 2019/07/26 13:55:33
     * @author Rong.Jia
     */
    private void retryLimit(String account, String password, String correctPassword, Byte sourceType) {

        String shiroLoginCount = AuthConstant.PREFIX_SHIRO_LOGIN_COUNT + account;
        String shiroIsLock = AuthConstant.PREFIX_SHIRO_IS_LOCK + account;

        ValueOperations<String, Object> opsForValue = redisUtils.getRedisTemplate().opsForValue();

        Boolean success = Boolean.FALSE;

//        if (Validator.equal(NumberConstant.ONE.byteValue(), sourceType)) {
        success = passwordEncoder.matches(password, correctPassword);
//        } else if (Validator.equal(NumberConstant.TWO.byteValue(), sourceType)) {
//            success = ldapTemplateUtils.authenticate(account, password);
//        }

        // 因为密码加密是以帐号+密码的形式进行加密的，所以解密后的对比是帐号+密码
        if (!success) {

            log.error("Wrong account or password");

            //访问一次，计数一次
            if (Validator.equal(AuthConstant.USER_LOCK_UN.get(NumberConstant.ZERO), opsForValue.get(shiroIsLock))) {

                log.error("验证未通过,错误次数大于5次,账户已锁定  account：{}", account);
                throw new ZtbWebException(ResponseEnum.USER_NAME_OR_PASSWORD_ERRORS_GREATER_THAN_5_TIMES);

            }

            opsForValue.increment(shiroLoginCount, NumberConstant.ONE);

            //计数大于5时，设置用户被锁定一小时
            if (Convert.toInt(opsForValue.get(shiroLoginCount)) >= NumberConstant.FIVE) {

                opsForValue.set(shiroIsLock, AuthConstant.USER_LOCK_UN.get(NumberConstant.ZERO));
                redisUtils.getRedisTemplate().expire(shiroIsLock, NumberConstant.TEN_POINT_ZERO.longValue(), TimeUnit.MINUTES);

            }

            throw new ZtbWebException(ResponseEnum.WRONG_ACCOUNT_OR_PASSWORD);

        } else {

            //清空登录计数
            opsForValue.set(shiroLoginCount, NumberConstant.ZERO);

            //设置未锁定状态
            opsForValue.set(shiroIsLock, AuthConstant.USER_LOCK_UN.get(NumberConstant.ONE));

        }
    }


}
