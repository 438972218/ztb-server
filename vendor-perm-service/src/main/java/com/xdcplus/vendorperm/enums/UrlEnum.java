package com.xdcplus.vendorperm.enums;

import lombok.Getter;

/**
 * url 枚举
 */
@Getter
public enum UrlEnum {

    // Oauth2
    OAUTH_TOKEN("/oauth/token"),


    ;


    UrlEnum(String value) {
        this.value = value;
    }

    private String value;


}
