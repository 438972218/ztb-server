package com.xdcplus.permission.common.enums;

/**
 * 公司类型的枚举
 *
 * @author Bullion.Yan
 * @date 2021/07/15
 */
public enum CompanyTypeEnum {
    GROUP_COMPANY(0,"集团"),
    NO_GROUP_COMPANY(1,"公司"),


















































    ;

    private Integer code;
    private String message;

    CompanyTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}