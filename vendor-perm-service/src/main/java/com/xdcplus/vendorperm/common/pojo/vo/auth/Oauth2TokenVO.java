package com.xdcplus.vendorperm.common.pojo.vo.auth;

import lombok.Builder;
import lombok.Data;

/**
 * Oauth2获取Token返回信息封装
 */
@Data
@Builder
public class Oauth2TokenVO {
    /**
     * 访问令牌
     */
    private String accessToken;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 访问令牌头前缀
     */
    private String tokenHead;
    /**
     * 有效时间（秒）
     */
    private int expiresIn;
}
