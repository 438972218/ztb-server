package com.xdcplus.vendorperm.common.enums;

/**
 * 组织结构类型
 *
 * @author Bullion.Yan
 * @date 2021/07/09
 */
public enum OrganizationStructureType {
    COMPANY(1,"公司"),
    DEPARTMENT(2,"部门"),
    ;
    private Integer code;
    private String message;

    OrganizationStructureType(Integer code, String message) {
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
