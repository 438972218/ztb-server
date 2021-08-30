package com.xdcplus.permission.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Oauth2Token {

    private AdditionalInformationBean additionalInformation;
    private String expiration;
    private boolean expired;
    private int expiresIn;
    private RefreshTokenBean refreshToken;
    private String tokenType;
    private String value;
    private List<String> scope;

    @NoArgsConstructor
    @Data
    public static class AdditionalInformationBean {
        /**
         * id : 18134572594098177
         * account : admin
         * jti : c17226cc-7de9-482f-a070-4832a697305d
         */

        private String id;
        private String account;
        private String jti;
    }

    @NoArgsConstructor
    @Data
    public static class RefreshTokenBean {
        private String expiration;
        private String value;
    }
}
