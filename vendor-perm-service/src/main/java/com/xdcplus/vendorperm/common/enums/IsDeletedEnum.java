package com.xdcplus.vendorperm.common.enums;

/**
 *  数据信息状态枚举类
 * @author Rong.Jia
 * @date 2019/4/2
 */
public enum IsDeletedEnum {
    // 成功
    DELETED(1,"已删除"),
    NO_DELETED(0,"未删除"),


















































    ;

    private Integer code;
    private String message;

    IsDeletedEnum(Integer code, String message) {
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
