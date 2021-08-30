package com.xdcplus.vendorperm.enums;

/**
 * 身份验证异常枚举
 *
 * @author Martin.Ji
 * @date 2021/07/23
 */
public enum AuthExceptionEnum {

    //
    JSON_USER_ROLE_IS_INCORRECT(1017, "用户角色权限的JSON缺少数据");

    private Integer code;
    private String message;

    AuthExceptionEnum(Integer code, String message) {
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
