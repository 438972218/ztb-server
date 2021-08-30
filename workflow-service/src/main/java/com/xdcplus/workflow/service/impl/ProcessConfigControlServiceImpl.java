package com.xdcplus.workflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.xdcplus.workflow.common.pojo.params.ProcessConfigTableParam;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.workflow.common.pojo.bo.ProcessConfigBO;
import com.xdcplus.workflow.common.pojo.dto.*;
import com.xdcplus.workflow.common.pojo.entity.ProcessConfig;
import com.xdcplus.workflow.common.pojo.entity.ProcessConfigLine;
import com.xdcplus.workflow.common.pojo.entity.ProcessConfigNode;
import com.xdcplus.workflow.common.pojo.vo.ConfigInfoVO;
import com.xdcplus.workflow.common.pojo.vo.ProcessConfigInfoVO;
import com.xdcplus.workflow.common.pojo.vo.ProcessConfigVO;
import com.xdcplus.workflow.common.pojo.vo.ProcessStatusVO;
import com.xdcplus.workflow.common.utils.ProcessValidationUtils;
import com.xdcplus.workflow.common.utils.VersionUtils;
import com.xdcplus.workflow.service.*;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 过程配置控制服务实现
 *
 * @author Rong.Jia
 * @date 2021/07/29 17:07:58
 */
@Slf4j
@Service
public class ProcessConfigControlServiceImpl implements ProcessConfigControlService {

    @Autowired
    private ProcessService processService;

    @Autowired
    private ProcessConfigService processConfigService;

    @Autowired
    private ProcessStatusService processStatusService;

    @Autowired
    private QualifierService qualifierService;

    @Autowired
    private ProcessConfigLineService processConfigLineService;

    @Autowired
    private ProcessConfigNodeService processConfigNodeService;

    @Override
    public List<ProcessConfigInfoVO> findProcessConfig(Long processId, @Nullable String version) {

        Assert.notNull(processId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        // 流程配置信息
        List<ProcessConfigBO> processConfigList = processConfigService.findConfigAssociatedByProcessId(processId, version);

        //  节点，线信息
        List<ProcessConfigLine> configLineList = processConfigLineService.findConfigLine(processId, version);
        List<ProcessConfigNode> configNodeList = processConfigNodeService.findConfigNode(processId, version);

        return objectConversion(processConfigList, configLineList, configNodeList);
    }

    @Override
    public List<ProcessConfigVO> findProcessConfigTable(Long processId, String version) {

        Assert.notNull(processId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return processConfigService.objectConversion(processConfigService.findConfigAssociatedByProcessId(processId, version));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveProcessConfig(List<ProcessConfigTableDTO> processConfigTables) {

        Assert.notEmpty(processConfigTables,
                ResponseEnum.THE_PROCESS_CONFIGURATION_INFORMATION_IS_EMPTY.getMessage());

        // 验证合法性
        ProcessValidationUtils.validationOfValidity(processConfigTables.stream()
                .map(a -> ProcessConfigValidationDTO.builder()
                        .from(a.getFromStatusMask())
                        .to(a.getToStatusMask())
                        .type(a.getType()).build()).collect(Collectors.toList()));

        Set<Long> processIds = processConfigTables.stream()
                .map(ProcessConfigTableDTO::getProcessId)
                .collect(Collectors.toSet());

        if (ObjectUtil.isNull(processIds) || processIds.size() != 1) {
            log.error("This parameter cannot be configured for multiple processes simultaneously");
            throw new ZtbWebException(ResponseEnum.THIS_PARAMETER_CANNOT_BE_CONFIGURED_FOR_MULTIPLE_PROCESSES_SIMULTANEOUSLY);
        }

        List<ProcessConfigTableParam> configTableParamList = refreshConfigParam(processConfigTables);

        List<ProcessConfig> processConfigList = combination(CollectionUtil.newArrayList(processIds).get(NumberConstant.ZERO),
                configTableParamList);

        processConfigService.saveProcessConfig(processConfigList);

        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveProcessConfig(ProcessConfigTreeDTO processConfigTreeDTO) {

        Long processId = processConfigTreeDTO.getProcessId();

        Assert.notNull(processService.findOne(processId),
                ResponseEnum.THE_PROCESS_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        String version = versionGetAndIncrement(processId);

        // 流程配置 线信息
        List<ProcessConfigLineDTO> configLines = processConfigTreeDTO.getLines();

        // 流程配置 节点信息
        List<ProcessConfigNodeDTO> configNodes = processConfigTreeDTO.getNodes();

        if (CollectionUtil.isEmpty(configLines) || CollectionUtil.isEmpty(configNodes)) {
            log.error("saveProcessConfig() The process configuration information is empty");
            throw new ZtbWebException(ResponseEnum.THE_PROCESS_CONFIGURATION_INFORMATION_IS_EMPTY);
        }

        // 验证合法信息
        ProcessValidationUtils.validationOfValidity(configLines, configNodes);

        refreshNodeLine(configNodes, configLines);

        // 转换对象
        List<ProcessConfig> processConfigList = combination(processId, version, configNodes, configLines);

        saveLine(processId, version, configLines);
        saveNode(processId, version, configNodes);

        processConfigService.saveProcessConfig(processConfigList);

        return Boolean.TRUE;
    }

    /**
     * 对象组合
     *
     * @param processId           流程ID
     * @param configTableParamList 流程配置信息
     * @return {@link List<ProcessConfig>} 流程实体对象
     */
    private List<ProcessConfig> combination(Long processId, List<ProcessConfigTableParam> configTableParamList) {

        String version = versionGetAndIncrement(processId);

        return configTableParamList.stream()
                .map(a -> {
                    ProcessConfig processConfig = new ProcessConfig();
                    BeanUtil.copyProperties(a, processConfig);
                    processConfig.setCreatedTime(DateUtil.current());
                    processConfig.setVersion(version);
                    return processConfig;
                }).collect(Collectors.toList());

    }

    /**
     * 更新配置参数
     *
     * @param processConfigTables 流程配置表
     * @return {@link List<ProcessConfigTableParam>}
     */
    private List<ProcessConfigTableParam> refreshConfigParam(List<ProcessConfigTableDTO> processConfigTables) {

        return processConfigTables.stream()
                .map(a -> {
                    ProcessConfigTableParam processConfigTableParam = new ProcessConfigTableParam();
                    BeanUtil.copyProperties(a, processConfigTableParam);
                    processConfigTableParam.setFromStatusId(getProcessStatusIdByMark(a.getFromStatusMask()));
                    processConfigTableParam.setToStatusId(getProcessStatusIdByMark(a.getToStatusMask()));

                    ProcessConfigTableDTO.Expression expression = a.getExpression();
                    if (StrUtil.isNotBlank(expression.getExpression())) {
                        QualifierDTO qualifierDTO = new QualifierDTO();
                        qualifierDTO.setName(IdUtil.fastSimpleUUID() + DateUtil.current());
                        qualifierDTO.setScript(expression.getExpression());
                        qualifierDTO.setDescription(expression.getDescription());
                        processConfigTableParam.setQualifierId(qualifierService.saveQualifier(qualifierDTO));
                    }
                    return processConfigTableParam;
                }).collect(Collectors.toList());
    }


    /**
     * 对象转换
     *
     * @param processConfigList 过程配置列表
     * @param configLineList    配置行列表
     * @param configNodeList    配置节点列表
     * @return {@link List<ProcessConfigInfoVO>} 流程配置信息
     */
    private List<ProcessConfigInfoVO> objectConversion(List<ProcessConfigBO> processConfigList,
                                                       List<ProcessConfigLine> configLineList, List<ProcessConfigNode> configNodeList) {

        if (CollectionUtil.isEmpty(processConfigList)
                || CollectionUtil.isEmpty(configLineList) || CollectionUtil.isEmpty(configNodeList)) {
            return null;
        }

        // 节点信息 Map, key：版本，value: 节点信息
        Map<String, List<ProcessConfigNode>> configNodeMap = configNodeList.stream()
                .collect(Collectors.groupingBy(ProcessConfigNode::getVersion));

        // 线信息 Map, key：版本，value: 线信息
        Map<String, List<ProcessConfigLine>> configLineMap = configLineList.stream()
                .collect(Collectors.groupingBy(ProcessConfigLine::getVersion));

        // 配置信息 Map, key：版本，value: 配置信息集合
        Map<String, List<ProcessConfigBO>> processConfigMap = processConfigList.stream()
                .collect(Collectors.groupingBy(ProcessConfigBO::getVersion));

        return processConfigMap.entrySet().stream().map(config -> {

            String version = config.getKey();
            List<ProcessConfigBO> processConfigBOList = config.getValue();

            ProcessConfigInfoVO processConfigInfoVO = new ProcessConfigInfoVO();
            processConfigInfoVO.setVersion(version);

            processConfigBOList.stream()
                    .filter(a -> ObjectUtil.equal(version, a.getVersion())).findAny()
                    .ifPresent(a -> processConfigInfoVO.setProcess(processService.objectConversion(a.getProcess())));

            List<ConfigInfoVO.ConfigLineVO> configLineVOList = configLineMap.get(version)
                    .stream().map(line -> {
                        ConfigInfoVO.ConfigLineVO lineVO = new ConfigInfoVO.ConfigLineVO();
                        lineVO.setFrom(line.getFromMark());
                        lineVO.setTo(line.getToMark());
                        return lineVO;
                    }).collect(Collectors.toList());

            List<ConfigInfoVO.ConfigNodeVO> configNodeVOList = configNodeMap.get(version)
                    .stream().map(node -> {

                        ConfigInfoVO.ConfigNodeVO nodeVO = new ConfigInfoVO.ConfigNodeVO();
                        BeanUtil.copyProperties(node, nodeVO);
                        nodeVO.setLeft(node.getLocationLeft());
                        nodeVO.setTop(node.getLocationTop());

                        ProcessStatusVO processStatusVO = processStatusService.findProcessStatusByMark(node.getStatusMark());
                        Optional.ofNullable(processStatusVO).ifPresent(a -> nodeVO.setName(a.getName()));

                        processConfigBOList.stream().filter(a ->
                                configLineVOList.stream()
                                        .anyMatch(b -> ObjectUtil.equal(a.getFromStatus().getMark(), b.getFrom())
                                                && ObjectUtil.equal(a.getToStatus().getMark(), b.getTo()))).findAny()
                                .ifPresent(c -> {
                                    Optional.ofNullable(c.getQualifier()).ifPresent(d -> nodeVO.setCondition(qualifierService.objectConversion(d)));
                                    nodeVO.setToRoleId(c.getToRoleId());
                                    nodeVO.setToUserId(c.getToUserId());
                                    nodeVO.setTimeoutAction(c.getTimeoutAction());
                                    nodeVO.setUserTo(c.getUserTo());
                                });

                        return nodeVO;
                    }).collect(Collectors.toList());

            processConfigInfoVO.setLines(configLineVOList);
            processConfigInfoVO.setNodes(configNodeVOList);

            return processConfigInfoVO;
        }).collect(Collectors.toList());

    }

    /**
     * 版本号获取并自增1返回
     *
     * @param processId 流程ID
     * @return {@link String} 版本号
     */
    private String versionGetAndIncrement (Long processId) {

        List<String> versionList = processConfigService.findConfigVersionByProcessId(processId);
        return VersionUtils.upgradeVersion(VersionUtils.maxVersion(versionList));
    }

    /**
     * 刷新节点，线信息
     *
     * @param configNodes 配置节点
     * @param configLines 配置线
     */
    private void refreshNodeLine(List<ProcessConfigNodeDTO> configNodes,
                                 List<ProcessConfigLineDTO> configLines) {

        Map<String, ProcessConfigNodeDTO> configNodeMap = configNodes.stream()
                .collect(Collectors.toMap(ProcessConfigNodeDTO::getStatusMark, o->o,(o, o1) -> o));

        configLines.forEach(line -> {
            line.setFrom(getProcessStatus(configNodeMap.get(line.getFrom()).getName(), line.getFrom()).getMark());
            line.setTo(getProcessStatus(configNodeMap.get(line.getTo()).getName(), line.getTo()).getMark());
        });

        configNodes.forEach(node -> {
            node.setStatusMark(getProcessStatus(node.getName(), node.getStatusMark()).getMark());
        });
    }

    /**
     *  组合对象
     * @param processId  流程ID
     * @param configNodes 配置节点
     * @param configLines 配置线
     * @return {@link List<ProcessConfig>} 流程配置集合
     */
    private List<ProcessConfig> combination(Long processId, String version, List<ProcessConfigNodeDTO> configNodes,
                                            List<ProcessConfigLineDTO> configLines) {

        // key: 标识，value: 节点信息
        Map<String, ProcessConfigNodeDTO> configNodeMap = configNodes.stream()
                .collect(Collectors.toMap(ProcessConfigNodeDTO::getStatusMark, o->o,(o,o1) -> o));

        return configLines.stream().map(line -> {

            ProcessConfigNodeDTO processConfigNodeDTO = configNodeMap.get(line.getFrom());

            ProcessConfig processConfig = new ProcessConfig();
            processConfig.setProcessId(processId);
            processConfig.setUserTo(processConfigNodeDTO.getUserTo());
            processConfig.setFromStatusId(getProcessStatus(processConfigNodeDTO.getName(), line.getFrom()).getId());
            processConfig.setToStatusId(getProcessStatus(configNodeMap.get(line.getTo()).getName(), line.getTo()).getId());
            processConfig.setVersion(version);
            processConfig.setTimeoutAction(processConfigNodeDTO.getTimeoutAction());
            processConfig.setToRoleId(processConfigNodeDTO.getToRoleId());
            processConfig.setToUserId(processConfigNodeDTO.getToUserId());
            if (isAddConditions(line.getLabel()) && ObjectUtil.isNotNull(processConfigNodeDTO.getCondition())) {
                processConfig.setQualifierId(qualifierService.getQualifier(processConfigNodeDTO.getCondition()));
            }

            processConfig.setCreatedTime(DateUtil.current());

            return processConfig;
        }).collect(Collectors.toList());

    }
    /**
     * 查询过程状态
     *
     * @param name 状态名
     * @param mark 标识
     * @return {@link ProcessStatusVO} 状态信息
     */
    private ProcessStatusVO getProcessStatus(String name, String mark) {
        return processStatusService.getProcessStatus(name, mark);
    }

    /**
     * 获取标识获取流程状态ID
     *
     * @param mark 标识
     * @return {@link Long} 流程状态ID
     */
    private Long getProcessStatusIdByMark(String mark) {
        return processStatusService.findProcessStatusByMark(mark).getId();
    }

    /**
     * 是否添加条件
     *
     * @param label 标签
     * @return {@link Boolean} true/false
     */
    private Boolean isAddConditions(String label) {

        Boolean flag = Boolean.FALSE;

        if (StrUtil.isNotBlank(label)) {
            if (StrUtil.equals(label, Convert.toStr(NumberConstant.ONE))) {
                flag = Boolean.TRUE;
            }else  if (StrUtil.equals(label, Convert.toStr(NumberConstant.TWO))){
                flag = Boolean.FALSE;
            }
        }

        return flag;
    }

    /**
     * 保存线信息
     *
     * @param processId          流程主键
     * @param version            版本
     * @param processConfigLines 过程配置线
     */
    private void saveLine(Long processId, String version, List<ProcessConfigLineDTO> processConfigLines) {

        if (CollectionUtil.isNotEmpty(processConfigLines)) {

            for (ProcessConfigLineDTO processConfigLine : processConfigLines) {
                processConfigLineService.saveConfigLine(processId, processConfigLine, version);
            }
        }
    }

    /**
     * 添加节点
     *
     * @param processId          流程主键
     * @param version            版本
     * @param processConfigNodes 过程配置节点
     */
    private void saveNode(Long processId, String version, List<ProcessConfigNodeDTO> processConfigNodes) {

        if (CollectionUtil.isNotEmpty(processConfigNodes)) {

            for (ProcessConfigNodeDTO processConfigNode : processConfigNodes) {
                processConfigNodeService.saveConfigNode(processId, processConfigNode, version);
            }
        }
    }

    /**
     * 验证条件是否存在
     *
     * @param qualifiedIds 条件集合
     */
    private void validationCriteria(Set<Long> qualifiedIds) {

       if (CollectionUtil.isNotEmpty(qualifiedIds)) {
            qualifiedIds.forEach(a -> Optional.ofNullable(qualifierService.findOne(a))
                    .orElseThrow(() -> new ZtbWebException(ResponseEnum.THE_QUALIFIER_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED)));
       }
    }




}
