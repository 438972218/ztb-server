package com.xdcplus.vendor.common.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AssemblyBuilder {
    private Map<String, Object> parameters = new ConcurrentHashMap<>();

    private AssemblyBuilder(){}

    public static AssemblyBuilder builder() {
        return new AssemblyBuilder();
    }

    public AssemblyBuilder addParameter(String key, Object value) {
        parameters.put(key, value);
        return this;
    }

    public Object build() {
        Object result = parameters;
        parameters.clear();
        parameters = null;
        return result;
    }
}
