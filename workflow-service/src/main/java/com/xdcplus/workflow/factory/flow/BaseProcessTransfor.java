package com.xdcplus.workflow.factory.flow;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.xdcplus.workflow.common.pojo.vo.QualifierVO;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.workflow.factory.user.UserDestinationFactory;
import com.xdcplus.workflow.factory.user.UserDestinationParam;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.workflow.common.pojo.dto.RequestFlowDTO;
import com.xdcplus.workflow.common.pojo.vo.ProcessConfigVO;
import com.xdcplus.workflow.common.pojo.vo.ProcessStatusVO;
import com.xdcplus.workflow.common.pojo.vo.RequestFlowVO;
import com.xdcplus.workflow.service.ProcessConfigService;
import com.xdcplus.workflow.service.ProcessStatusService;
import com.xdcplus.workflow.service.RequestFlowService;
import com.xdcplus.workflow.service.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 流转转移过程
 *
 * @author Rong.Jia
 * @date 2021/06/07
 */
@Slf4j
public class BaseProcessTransfor implements ProcessTransfor {

    @Autowired
    RequestFlowService requestFlowService;

    @Autowired
    RequestService requestService;

    @Autowired
    ProcessStatusService processStatusService;

    @Autowired
    ProcessConfigService processConfigService;

    @Autowired
    UserDestinationFactory userDestinationFactory;

    /**
     * 查询配置配置
     * @param processId 流程主键
     * @param requestCreatedUser  表单创建人
     * @param userId 审批者用户标识
     * @param statusId  状态主键
     * @param version 版本号
     * @return {@link List<ProcessConfigVO>} 流程配置信息
     */
    List<ProcessConfigVO> findConfigByProcessIdAndFromStatusId(Long processId,
                                                               Long statusId, String version,
                                                               Long userId, String requestCreatedUser) {

        List<ProcessConfigVO> processConfigVOList =  processConfigService.findConfigByProcessIdAndFromStatusId(processId, statusId, version);

        if (CollectionUtil.isNotEmpty(processConfigVOList)) {
            processConfigVOList.forEach(a -> {

                UserDestinationParam userDestinationParam = UserDestinationParam.builder()
                        .createUserName(requestCreatedUser)
                        .userId(userId)
                        .userToType(a.getUserTo())
                        .build();

                Optional.ofNullable(userDestinationFactory.postProcess(userDestinationParam))
                        .ifPresent(a::setToUserId);
            });
        }

        return processConfigVOList;
    }

    /**
     * 保存、修改流程信息
     *
     * @param syncFlow 流程信息
     */
    void syncFlow(SyncFlow syncFlow) {

        RequestFlowDTO requestFlowDTO = new RequestFlowDTO();
        BeanUtil.copyProperties(syncFlow, requestFlowDTO);

        requestFlowDTO.setId(syncFlow.getFlowId());

        if (ObjectUtil.isNull(syncFlow.getFlowId())) {

            requestFlowDTO.setBeginTime(DateUtil.current());

            requestFlowService.saveRequestFlow(requestFlowDTO);
        } else {
            requestFlowService.updateRequestFlow(requestFlowDTO);
        }
    }

    /**
     * 根据表单主键修改 流程状态
     *
     * @param requestId 表单主键
     * @param statusId  流程状态
     */
    void updateRequestStatusIdById(Long requestId, Long statusId) {
        requestService.updateStatusIdById(requestId, statusId);
    }

    /**
     * 查询过程状态通过标识
     *
     * @param mark 标识
     * @return {@link ProcessStatusVO} 过程状态
     */
    ProcessStatusVO findProcessStatusByMark(String mark) {

        ProcessStatusVO processStatusVO = processStatusService.findProcessStatusByMark(mark);
        if (ObjectUtil.isNull(processStatusVO)) {
            log.error("findProcessStatusByMark() Abnormal flow, signature status does not exist");
            throw new ZtbWebException(ResponseEnum.ABNORMAL_FLOW_SIGNATURE_STATUS_DOES_NOT_EXIST);
        }

        return processStatusVO;
    }

    /**
     * 查询当前流转节点
     *
     * @param statusId  状态ID
     * @param toUserId  来用户主键
     * @param roleIds   用户拥有角色
     * @param requestId 表单主键
     * @return {@link RequestFlowVO} 流转节点
     */
    RequestFlowVO getCurrentRequestFlow(Long requestId, Long statusId, Long toUserId, List<Long> roleIds) {

        List<RequestFlowVO> requestFlowVOList = getCurrentRequestFlows(requestId, statusId, toUserId, roleIds);
        RequestFlowVO requestFlowVO;

        if (CollectionUtil.isNotEmpty(requestFlowVOList) && requestFlowVOList.size() > NumberConstant.ONE) {
            requestFlowVO = requestFlowVOList.stream()
                    .filter(a -> equal(a.getFlowOption().getValue(), NumberConstant.A_NEGATIVE))
                    .findAny().orElse(null);

        } else {
            requestFlowVO = getCurrentRequestFlows(requestId, statusId, toUserId, roleIds).stream().findAny().orElse(null);
        }

        if (ObjectUtil.isNull(requestFlowVO)) {
            log.error("Process exceptions, The current node could not be found");
            throw new ZtbWebException(ResponseEnum.FLOW_ABNORMAL_CURRENT_NODE_CANNOT_BE_FOUND);
        }

        return requestFlowVO;
    }

    /**
     * 查询当前的流转信息
     *
     * @param requestId 表单主键
     * @param statusId  状态主键
     * @param toUserId  来用户主键
     * @param roleIds   用户拥有角色
     * @return {@link List<RequestFlowVO>} 流转信息
     */
    List<RequestFlowVO> getCurrentRequestFlows(Long requestId, Long statusId, Long toUserId, List<Long> roleIds) {

        List<RequestFlowVO> currentRequestFlows = this.getRequestFlows(requestId, statusId);

        return currentRequestFlows.stream()
                .filter(a -> {

                    boolean flag = Boolean.TRUE;

                    if (ObjectUtil.isAllNotEmpty(toUserId, a.getToUserId())) {
                        flag = ObjectUtil.equal(a.getToUserId(), toUserId);
                    }

                    if (ObjectUtil.isAllNotEmpty(roleIds, a.getToRoleId())) {
                        flag = flag || roleIds.contains(a.getToRoleId());
                    }

                    return flag && ObjectUtil.equal(requestId, a.getRequest().getId());
                }).collect(Collectors.toList());

    }

    /**
     * 查询流转节点
     *
     * @param requestId 表单主键
     * @param statusId  表单状态主键
     * @return {@link List<RequestFlowVO>}  流程当前节点, 如果是会签，或者多加签则为多个
     */
    List<RequestFlowVO> getRequestFlows(Long requestId, Long statusId) {

        List<RequestFlowVO> requestFlowVOList = requestFlowService.findRequestFlowByRequestIdAndToStatusId(requestId, statusId);

        if (CollectionUtil.isEmpty(requestFlowVOList)) {
            log.error("getCurrentRequestFlows() Flow abnormal, current node cannot be found");
            throw new ZtbWebException(ResponseEnum.FLOW_ABNORMAL_CURRENT_NODE_CANNOT_BE_FOUND);
        }

        return requestFlowVOList;
    }

    /**
     * 获取最近一条非加签流转记录
     *
     * @param requestFlowVOList 流转信息集合
     * @param currentFlowId 当前流转节点ID
     * @param mark 标识
     * @return {@link RequestFlowVO} 非加签流转记录
     */
    RequestFlowVO getRecentUnsignedFlow(List<RequestFlowVO> requestFlowVOList, Long currentFlowId, Integer mark) {

        requestFlowVOList.removeIf(a -> ObjectUtil.equal(a.getId(), currentFlowId));
        requestFlowVOList.removeIf(a -> notEqual(a.getFlowOption().getValue(), mark));

        requestFlowVOList = requestFlowVOList.stream()
                .sorted(Comparator.comparingLong(RequestFlowVO::getEndTime).reversed())
                .collect(Collectors.toList());

        // 非加签流转记录
        RequestFlowVO nonSignedFlowRecords = null;

        if (CollectionUtil.isNotEmpty(requestFlowVOList) && requestFlowVOList.size() >= NumberConstant.ONE) {
            nonSignedFlowRecords = requestFlowVOList.get(NumberConstant.ZERO);
        }

        if (ObjectUtil.isNull(nonSignedFlowRecords)) {
            log.error("The unsigned flow record was not obtained correctly");
            throw new ZtbWebException(ResponseEnum.THE_UNSIGNED_FLOW_RECORD_WAS_NOT_OBTAINED_CORRECTLY);
        }

        return nonSignedFlowRecords;
    }

    /**
     * 获取非加签去向配置
     *
     * @param currentConfigList 当前配置列表
     * @param flowConditions    流转条件
     * @return {@link List<ProcessConfigVO>} 去向配置
     */
    List<ProcessConfigVO> getsConfigNonAddedDestination(List<ProcessConfigVO> currentConfigList, Object flowConditions) {

        // 下一个配置信息集合
        List<ProcessConfigVO> nextConfigList = CollectionUtil.newArrayList();

        // 当前同级去向配置toStatus是否相同
        boolean toStatusEq = Validator.equal(currentConfigList.stream()
                .map(a -> a.getToStatus().getMark())
                .collect(Collectors.toSet()).size(), NumberConstant.ONE);

        // 同级去向配置Qualifier是否为空
        boolean isQualifierNull = currentConfigList.stream().allMatch(a -> ObjectUtil.isNull(a.getQualifier()));

        // 去向是否等于2
        boolean toConfigureEq = Validator.equal(currentConfigList.size(), NumberConstant.TWO);

        if ((!toStatusEq || !isQualifierNull) && toConfigureEq) {

            ProcessConfigVO currentProcessConfig = getCurrentProcessConfig(currentConfigList);

            if (!doExpression(currentProcessConfig.getQualifier(), flowConditions)) {

                //获取失败去向配置
                currentProcessConfig = getFailureToProcessConfig(currentConfigList, currentProcessConfig);
            }

            nextConfigList.add(currentProcessConfig);
        }else {
            nextConfigList.addAll(currentConfigList);
        }

        if (CollectionUtil.isEmpty(nextConfigList)) {
            log.error("Failed to get to the process");
            throw new ZtbWebException(ResponseEnum.A_VALID_PROCESS_CONFIGURATION_WAS_NOT_FOUND);
        }


        return nextConfigList;
    }

    /**
     * 获取失败去向配置
     * @param currentConfigList 流程配置集合
     * @param normalToConfig 正常去向配置
     * @return {@link ProcessConfigVO} 流程配置
     */
    private ProcessConfigVO getFailureToProcessConfig(List<ProcessConfigVO> currentConfigList,
                                                      ProcessConfigVO normalToConfig) {

        ProcessConfigVO processConfigVO = currentConfigList.stream()
                .filter(a -> ObjectUtil.equal(a.getProcess().getId(), normalToConfig.getProcess().getId())
                        && ObjectUtil.notEqual(a.getId(), normalToConfig.getId()))
                .findAny().orElse(null);

        if (ObjectUtil.isNull(processConfigVO)) {
            log.error("Flow abnormal, The process configuration is not valid");
            throw new ZtbWebException(ResponseEnum.THE_PROCESS_CONFIGURATION_INFORMATION_IS_NOT_VALID);
        }

        return processConfigVO;
    }

    /**
     * 执行 条件
     * @param qualifierVO   限定符VO
     * @param flowConditionParam 流程状态参数
     * @return {@link Boolean} true: 通过，false: 失败
     */
    private Boolean doExpression(QualifierVO qualifierVO, Object flowConditionParam) {

        // 获取流转条件
        Map<String, String> flowConditions = getFlowConditions(flowConditionParam);

        // 限定条件, 没有条件则为空
        if (ObjectUtil.isNotNull(qualifierVO) && MapUtil.isNotEmpty(flowConditions)) {

            String script = qualifierVO.getScript();
            for (Map.Entry<String, String> entry : flowConditions.entrySet()) {
                script = StrUtil.replace(script, entry.getKey(), entry.getValue());
            }

            ExpressionParser parser = new SpelExpressionParser();
            try {
                return parser.parseExpression(script).getValue(boolean.class);
            }catch (Exception e) {
                log.error("Expression failed to run {}", e.getMessage());
            }
        }

        // 非正常则跳过验证条件
        return Boolean.TRUE;
    }

    /**
     * 获取当前去向配流程置
     * @param currentConfigList 流程配置集合
     * @return {@link ProcessConfigVO} 流程配置
     */
    ProcessConfigVO getCurrentProcessConfig(List<ProcessConfigVO> currentConfigList) {

        ProcessConfigVO processConfigVO = currentConfigList.stream()
                .filter(a -> ObjectUtil.isNotNull(a.getQualifier())).findAny().orElse(null);

        if (ObjectUtil.isNull(processConfigVO)) {
            processConfigVO = currentConfigList.stream()
                    .filter(a -> ObjectUtil.isNull(a.getQualifier())).findAny().orElse(null);
        }

        if (ObjectUtil.isNull(processConfigVO)) {
            log.error("Flow abnormal, The process configuration is not valid");
            throw new ZtbWebException(ResponseEnum.THE_PROCESS_CONFIGURATION_INFORMATION_IS_NOT_VALID);
        }

        return processConfigVO;
    }

    /**
     * 获取流转条件参数信息
     * @param flowCondition 流转条件参数信息
     * @return {@link Map}  key: 字段名， value: 值
     */
    private Map<String, String> getFlowConditions(Object flowCondition) {

        if (ObjectUtil.isNotNull(flowCondition)) {
            if (flowCondition instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) flowCondition;
                Map<String, Object> innerMap = jsonObject.getInnerMap();
                if (MapUtil.isNotEmpty(innerMap)) {
                    return innerMap.entrySet().stream()
                            .collect(Collectors.toMap(Map.Entry::getKey, a -> Convert.toStr(a.getValue())));
                }
            }else {
                Field[] fields = ReflectUtil.getFields(flowCondition.getClass());
                if (ArrayUtil.isNotEmpty(fields)) {
                    Map<String, String> flowConditions = MapUtil.newHashMap();
                    for (Field field : fields) {
                        field.setAccessible(Boolean.TRUE);
                        String fieldName = field.getName();
                        try {

                            Object fieldValue = ReflectUtil.getFieldValue(flowCondition, field);
                            if (ObjectUtil.isNotNull(fieldValue)) {
                                flowConditions.put(fieldName, Convert.toStr(fieldValue));
                            }
                        }catch (Exception e) {
                            log.error("getFlowConditions {}, {}",
                                    String.format("field name  %s Value extraction anomaly", fieldName), e.getMessage());
                        }
                        field.setAccessible(Boolean.FALSE);
                    }
                    return flowConditions;
                }
            }
        }

        return null;
    }

    /**
     * 判断是否相等
     * @param obj1 obj1
     * @param obj2 obj2
     * @return boolean 是否相等
     */
    boolean equal(Object obj1, Object obj2) {

        if (obj1 instanceof Number && obj2 instanceof Number) {
            return Validator.equal(obj1, obj2);
        }else {
            return StrUtil.equals(Convert.toStr(obj1), Convert.toStr(obj2));
        }
    }

    /**
     * 判断是否不相等
     *
     * @param obj1 obj1
     * @param obj2 obj2
     * @return boolean 是否相等
     */
    boolean notEqual(Object obj1, Object obj2) {
        return !equal(obj1, obj2);
    }

















}
