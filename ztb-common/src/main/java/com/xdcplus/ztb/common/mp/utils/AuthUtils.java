package com.xdcplus.ztb.common.mp.utils;

import cn.hutool.core.util.StrUtil;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.tool.constants.AuthConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 *  权限相关工具类
 * @author Rong.Jia
 * @date 2019/08/15 08:53
 */
@Slf4j
public class AuthUtils {

    /**
     *  获取当前登录用户
     * @date 2019/08/15 08:55:22
     * @author Rong.Jia
     * @return  String 登录用户
     */
    public static String getCurrentUser() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        String account = Objects.requireNonNull(requestAttributes).getRequest().getHeader(AuthConstant.ACCOUNT);

        if (StrUtil.isBlank(account)) {
//            account = AuthConstant.ADMINISTRATOR;
            log.error("getCurrentUser {}", ResponseEnum.NOT_LOGGED_IN.getMessage());
            throw new ZtbWebException(ResponseEnum.NOT_LOGGED_IN);
        }

        return account;

    }


}
