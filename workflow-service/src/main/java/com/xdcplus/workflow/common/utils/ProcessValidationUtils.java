package com.xdcplus.workflow.common.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.xdcplus.workflow.common.pojo.dto.*;
import com.xdcplus.workflow.common.validator.groupvlidator.ProcessTransforAdditionalSignGroupValidator;
import com.xdcplus.workflow.common.validator.groupvlidator.ProcessTransforAgreeGroupValidator;
import com.xdcplus.workflow.common.validator.groupvlidator.ProcessTransforSendBackGroupValidator;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Validation;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 流程验证工具类
 *
 * @author Rong.Jia
 * @date 2021/06/23
 */
@Slf4j
public class ProcessValidationUtils {

    /**
     * 验证合法性
     *
     * @param configNodes 流程节点集合
     * @param configLines 流程线集合
     * @param to          目标状态ID
     */
    private static void validationOfValidity(List<ProcessConfigLineDTO> configLines,
                                            List<ProcessConfigNodeDTO> configNodes, String to) {

        List<ProcessConfigLineDTO> nextList = getNextLineConfig(configLines, to);

        if (CollectionUtil.isEmpty(nextList)) {

            boolean validation = configNodes.stream()
                    .anyMatch(a -> StrUtil.equals(a.getStatusMark(), to)
                            && ObjectUtil.notEqual(NumberConstant.A_NEGATIVE, a.getType()));

            Assert.isFalse(validation, ResponseEnum.THE_PROCESS_CONFIGURATION_INFORMATION_IS_NOT_VALID.getMessage());
        }

        for (ProcessConfigLineDTO a : nextList) {
            validationOfValidity(configLines, configNodes, a.getTo());
        }

    }

    /**
     * 验证合法性
     *
     * @param configNodes 流程节点集合
     * @param configLines 流程线集合
     */
    public static void validationOfValidity(List<ProcessConfigLineDTO> configLines,
                                            List<ProcessConfigNodeDTO> configNodes) {

        Assert.notEmpty(configLines,
                ResponseEnum.THE_PROCESS_CONFIGURATION_INFORMATION_IS_EMPTY.getMessage());
        Assert.notEmpty(configNodes,
                ResponseEnum.THE_PROCESS_CONFIGURATION_INFORMATION_IS_EMPTY.getMessage());

        ProcessConfigNodeDTO startNode = ProcessValidationUtils.getStartNode(configNodes);

        List<ProcessConfigLineDTO> startLines = configLines.stream()
                .filter(a -> StrUtil.equals(a.getFrom(), startNode.getStatusMark()))
                .collect(Collectors.toList());

        Assert.notEmpty(startLines, ResponseEnum.MISSING_START_NODE.getMessage());

        startLines.forEach(a -> validationOfValidity(configLines, configNodes, a.getTo()));

    }

    /**
     * 验证有效性
     *
     * @param processConfigValidations 流程配置验证
     */
    public static void validationOfValidity(List<ProcessConfigValidationDTO> processConfigValidations) {

        Assert.notEmpty(processConfigValidations,
                ResponseEnum.THE_PROCESS_CONFIGURATION_INFORMATION_IS_EMPTY.getMessage());

        // 会出现多个开始节点
        List<ProcessConfigValidationDTO> startNodes = processConfigValidations.stream()
                .filter(a -> Validator.equal(a.getType(), NumberConstant.ZERO))
                .collect(Collectors.toList());

        Assert.notEmpty(startNodes, ResponseEnum.MISSING_START_NODE.getMessage());

        startNodes.forEach(a -> validationOfValidity(processConfigValidations, a.getTo()));

    }

    /**
     * 验证有效性
     * @param start 开始节点
     * @param processConfigValidations 流程配置验证
     */
    private static void validationOfValidity(List<ProcessConfigValidationDTO> processConfigValidations, String start) {

        List<ProcessConfigValidationDTO> nextConfigs = getNextValidationConfig(processConfigValidations, start);

        if (CollectionUtil.isEmpty(nextConfigs)) {

            boolean validation = processConfigValidations.stream()
                    .anyMatch(a -> StrUtil.equals(a.getFrom(), start)
                            && ObjectUtil.notEqual(NumberConstant.A_NEGATIVE, a.getType()));

            Assert.isFalse(validation, ResponseEnum.THE_PROCESS_CONFIGURATION_INFORMATION_IS_NOT_VALID.getMessage());
        }

        for (ProcessConfigValidationDTO a : nextConfigs) {
            validationOfValidity(processConfigValidations, a.getTo());
        }
    }

    /**
     * 验证过程转移
     *
     * @param processTransforDTO 过程转移DTO
     */
    public static void validationProcessTransfor(ProcessTransforDTO processTransforDTO) {

        javax.validation.Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Integer flowOption = processTransforDTO.getFlowOption();
        switch (flowOption) {
            case 1:
                validator.validate(processTransforDTO, ProcessTransforAgreeGroupValidator.class);
                break;
            case 2:
                validator.validate(processTransforDTO, ProcessTransforSendBackGroupValidator.class);
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
            case 6:
                validator.validate(processTransforDTO, ProcessTransforAdditionalSignGroupValidator.class);
                break;
            default:
                break;
        }
    }

    /**
     * 查询下一个配置
     *
     * @param configLines 配置线
     * @param to          下一个
     * @return {@link List<ProcessConfigLineDTO>} 线集合
     */
    private static List<ProcessConfigLineDTO> getNextLineConfig(List<ProcessConfigLineDTO> configLines, String to) {
        return configLines.stream().filter(a -> StrUtil.equals(a.getFrom(), to)).collect(Collectors.toList());
    }

    /**
     * 查询下一个配置
     *
     * @param configValidationList 配置验证集合
     * @param to     下一个
     * @return {@link List<ProcessConfigValidationDTO>} 配置验证集合
     */
    private static List<ProcessConfigValidationDTO> getNextValidationConfig(List<ProcessConfigValidationDTO> configValidationList, String to) {
        return configValidationList.stream().filter(a -> StrUtil.equals(a.getFrom(), to)).collect(Collectors.toList());
    }

    /**
     * 查询开始节点
     *
     * @param configNodes 配置节点
     * @return {@link ProcessConfigNodeDTO} 开始节点
     */
    public static ProcessConfigNodeDTO getStartNode(List<ProcessConfigNodeDTO> configNodes) {

        ProcessConfigNodeDTO startNode = configNodes.stream()
                .filter(node -> ObjectUtil.equal(NumberConstant.ZERO, node.getType()))
                .findAny().orElse(null);

        Assert.notNull(startNode,
                ResponseEnum.MISSING_START_NODE.getMessage());

        return startNode;
    }

    /**
     * 查询结束节点
     *
     * @param configNodes 配置节点
     * @return {@link ProcessConfigNodeDTO} 束节点
     */
    public static ProcessConfigNodeDTO getEndNode(List<ProcessConfigNodeDTO> configNodes) {

        ProcessConfigNodeDTO endNode = configNodes.stream()
                .filter(node -> ObjectUtil.equal(NumberConstant.A_NEGATIVE, node.getType()))
                .findAny().orElse(null);

        Assert.notNull(endNode,
                ResponseEnum.MISSING_END_NODE.getMessage());

        return endNode;
    }

    /**
     * 验证结束节点
     *
     * @param endStatusMark 结束状态标志
     */
    public static void verifyEndNode(String endStatusMark) {
        if (!Validator.equal(NumberConstant.TWO, endStatusMark)
                && !Validator.equal(NumberConstant.FOUR.longValue(), endStatusMark)) {
            log.error("A process that is documented can only be a final process");
            throwException(ResponseEnum.THE_PROCESS_CONFIGURATION_INFORMATION_IS_NOT_VALID);
        }
    }

    /**
     * 验证开始节点
     *
     * @param startStatusMark 开始状态标识
     */
    public static void verifyStartNode(String startStatusMark) {

        if (Validator.equal(NumberConstant.TWO, startStatusMark)) {
            log.error("The process cannot start archived");
            throwException(ResponseEnum.THE_PROCESS_CONFIGURATION_INFORMATION_IS_NOT_VALID);
        }

        if (Validator.equal(NumberConstant.THREE, startStatusMark)) {
            log.error("The process cannot be returned at the beginning");
            throwException(ResponseEnum.THE_PROCESS_CONFIGURATION_INFORMATION_IS_NOT_VALID);
        }

        if (Validator.equal(NumberConstant.FOUR, startStatusMark)) {
            log.error("Process start cannot be cancelled");
            throwException(ResponseEnum.THE_PROCESS_CONFIGURATION_INFORMATION_IS_NOT_VALID);
        }

        if (Validator.equal(NumberConstant.FIVE, startStatusMark)) {
            log.error("The start process cannot be backsigned");
            throwException(ResponseEnum.THE_PROCESS_CONFIGURATION_INFORMATION_IS_NOT_VALID);
        }

        if (Validator.equal(NumberConstant.SIX, startStatusMark)) {
            log.error("The start process cannot be pre-signed");
            throwException(ResponseEnum.THE_PROCESS_CONFIGURATION_INFORMATION_IS_NOT_VALID);
        }

    }

    /**
     * 异常抛出
     *
     * @param responseEnum 响应枚举
     */
    private static void throwException(ResponseEnum responseEnum) {
        throw new ZtbWebException(responseEnum);
    }

    /**
     * 异常抛出
     *
     * @param responseEnum 响应枚举
     */
    private static void throwException(Object object, ResponseEnum responseEnum) {
        Assert.notNull(object, responseEnum.getMessage());
    }


}
