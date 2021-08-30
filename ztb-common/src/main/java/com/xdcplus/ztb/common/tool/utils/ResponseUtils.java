package com.xdcplus.ztb.common.tool.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 响应工具类
 *
 * @author Rong.Jia
 * @date 2021/08/03 17:36:07
 */
@Slf4j
public class ResponseUtils {

    /**
     * 获取响应
     *
     * @param response 响应
     * @return {@link T} 响应信息
     */
    public static <T> T getResponse(ResponseVO<T> response) {

        if (Validator.equal(NumberConstant.ZERO, response.getCode())) {
            return response.getData();
        }

        return null;
    }

    /**
     * 获取字段值
     *
     * @param object    对象
     * @param fieldName 字段名
     * @param fieldType 字段类型
     * @return {@link T} 字段值
     */
    public static <T> T getFieldValue(Object object, String fieldName, Class<T> fieldType) {

        try {
            if (ObjectUtil.isNotNull(object)) {
                return Convert.convert(fieldType, ReflectUtil.getFieldValue(object, fieldName));
            }
        }catch (Exception e) {
            log.error("Failed to obtain the value of field : {}, message : {}", fieldName, e.getMessage());
        }

        return null;
    }

















}
