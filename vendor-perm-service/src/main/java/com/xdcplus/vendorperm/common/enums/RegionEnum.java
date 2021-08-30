package com.xdcplus.vendorperm.common.enums;

/**
 *  数据信息状态枚举类
 * @author Rong.Jia
 * @date 2019/4/2
 */
public enum RegionEnum {
    PROVINCE(1,"省"),
    CITY(2,"市"),
    AREA(3,"区/县"),
























































    ;

    private Integer code;
    private String message;

    RegionEnum(Integer code, String message) {
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
