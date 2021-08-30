package com.xdcplus.scheduling.common.dynamic;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 过滤器模型
 *
 * @author Rong.Jia
 * @date 2021/07/28 16:53:36
 */
@Data
public class GatewayFilterDefinition {

    /**
     *  过滤名
     */
    private String name;
    /**
     * 对应的路由规则
     */
    private Map<String, String> args = new LinkedHashMap<>();

}
