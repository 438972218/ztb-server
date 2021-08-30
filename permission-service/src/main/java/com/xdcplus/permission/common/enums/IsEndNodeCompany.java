package com.xdcplus.permission.common.enums;

/**
 * 是否是末节点的公司
 * 只该 公司下面没有子公司，
 * 且只有末末公司下面可以添加部门
 *
 * @author Bullion.Yan
 * @date 2021/07/15
 */
public enum IsEndNodeCompany {
    NO_END_NODE(1, "不是末节点公司"),
    END_NODE(0, "末节点公司"),


    ;

    private Integer code;
    private String message;

    IsEndNodeCompany(Integer code, String message) {
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