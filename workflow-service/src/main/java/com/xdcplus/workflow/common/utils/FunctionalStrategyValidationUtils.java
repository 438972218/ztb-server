package com.xdcplus.workflow.common.utils;

import cn.hutool.core.util.ObjectUtil;
import com.xdcplus.workflow.common.pojo.dto.FormFlowStrategyDTO;
import com.xdcplus.workflow.common.validator.groupvlidator.FunctionalStrategyFormGroupValidator;

import javax.validation.Validation;

/**
 * 功能策略验证工具类
 *
 * @author Rong.Jia
 * @date 2021/08/06 11:14:05
 */
public class FunctionalStrategyValidationUtils {

    /**
     * 验证有效性
     *
     * @param formFlowStrategyDTO 表单流转条件
     */
    public static void validationOfValidity(FormFlowStrategyDTO formFlowStrategyDTO) {

        if (ObjectUtil.isNotNull(formFlowStrategyDTO)) {
            Validation.buildDefaultValidatorFactory().getValidator()
                    .validate(formFlowStrategyDTO, FunctionalStrategyFormGroupValidator.class);

        }

    }

















}
