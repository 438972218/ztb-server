package com.xdcplus.workflow.common.builder;

/**
 * 替代属性构建器
 *
 * @author Rong.Jia
 * @date 2021/08/25
 */
public class ReplaceAttributeBuilder {

    private StringBuilder builder = new StringBuilder();

    private ReplaceAttributeBuilder(){}

    public static ReplaceAttributeBuilder builder() {
        return new ReplaceAttributeBuilder();
    }

    public ReplaceAttributeBuilder addParameter(String parameter) {
        builder.append(parameter);
        return this;
    }

    public String build() {
        StringBuilder cBuilder = builder;
        builder = null;
        return "${" + cBuilder.toString() + "}";
    }















}
