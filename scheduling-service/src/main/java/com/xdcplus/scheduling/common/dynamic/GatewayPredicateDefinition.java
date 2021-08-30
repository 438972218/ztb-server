package com.xdcplus.scheduling.common.dynamic;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 路由断言模型
 *
 * @author Rong.Jia
 * @date 2021/07/28 16:54:06
 */
@Data
public class GatewayPredicateDefinition {

    /**
     * 断言对应的Name
     */
    private String name;

    /**
     * 配置的断言规则
     */
    private Map<String, String> args = new LinkedHashMap<>();

}
