package com.xdcplus.vendor.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举的方向
 *
 * @author Rong.Jia
 * @date 2021/08/17 15:21:51
 */
@Getter
@AllArgsConstructor
public enum DirectionOfEnum {

    // 正向
    POSITIVE("正向"),

    // 反向
    REVERSE("反向"),

    ;


    private String value;


}
