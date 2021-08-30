package com.xdcplus.workflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.xdcplus.workflow.common.pojo.dto.RequestStrategyDTO;
import com.xdcplus.workflow.common.pojo.entity.FunctionalStrategy;
import com.xdcplus.workflow.common.pojo.vo.FunctionalStrategyVO;
import com.xdcplus.workflow.common.pojo.vo.ProcessVO;
import com.xdcplus.workflow.common.pojo.vo.RequestTypeVO;
import com.xdcplus.workflow.mapper.FunctionalStrategyMapper;
import com.xdcplus.workflow.service.FunctionalStrategyService;
import com.xdcplus.workflow.service.ProcessConfigService;
import com.xdcplus.workflow.service.ProcessService;
import com.xdcplus.workflow.service.RequestTypeService;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.utils.PageableUtils;
import com.xdcplus.ztb.common.tool.utils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 功能策略信息 服务实现类
 *
 * @author Rong.Jia
 * @since 2021-08-06
 */
@Slf4j
@Service
public class FunctionalStrategyServiceImpl extends BaseServiceImpl<FunctionalStrategy, FunctionalStrategyVO, FunctionalStrategy, FunctionalStrategyMapper> implements FunctionalStrategyService {

    @Autowired
    private FunctionalStrategyMapper functionalStrategyMapper;

    @Autowired
    private RequestTypeService requestTypeService;

    @Autowired
    private ProcessService processService;

    @Autowired
    private ProcessConfigService processConfigService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long syncRequestStrategy(RequestStrategyDTO requestStrategyDTO) {

        RequestTypeVO requestTypeVO = requestTypeService.findOne(requestStrategyDTO.getRequestTypeId());
        Assert.notNull(requestTypeVO, ResponseEnum.THE_FORM_TYPE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        ProcessVO processVO = processService.findOne(requestStrategyDTO.getProcessId());
        Assert.notNull(processVO, ResponseEnum.THE_PROCESS_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        if (StrUtil.isNotBlank(requestStrategyDTO.getConfigVersion())) {
            List<String> configVersionList = processConfigService.findConfigVersionByProcessId(requestStrategyDTO.getProcessId());
            if (CollectionUtil.isEmpty(configVersionList) || !configVersionList.contains(requestStrategyDTO.getConfigVersion())) {
                throw new ZtbWebException(ResponseEnum.THE_PROCESS_CONFIGURATION_VERSION_DOES_NOT_EXIST);
            }
        }

        Long strategyId = requestStrategyDTO.getId();

        FunctionalStrategy functionalStrategy = Validator.isNull(strategyId)
                ? new FunctionalStrategy()
                : Optional.ofNullable(this.getById(strategyId))
                .orElseThrow(()
                        -> new ZtbWebException(ResponseEnum.THE_FUNCTION_POLICY_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED));

        BeanUtil.copyProperties(requestStrategyDTO, functionalStrategy);
        functionalStrategy.setType(NumberConstant.THREE);

        if (Validator.isNull(strategyId)) {
            this.save(functionalStrategy);
        } else {
            this.updateById(functionalStrategy);
        }

        return functionalStrategy.getId();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteStrategy(Long strategyId) {

        Assert.notNull(strategyId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        FunctionalStrategy functionalStrategy = this.getById(strategyId);
        Assert.notNull(functionalStrategy, ResponseEnum.THE_FUNCTION_POLICY_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        return this.removeById(strategyId);
    }

    @Override
    public PageVO<FunctionalStrategyVO> findStrategy(PageDTO pageDTO) {

        PageVO<FunctionalStrategyVO> pageVO = new PageVO<>();

        if (pageDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(pageDTO);
        }

        List<FunctionalStrategy> strategyList = this.list();
        PageInfo<FunctionalStrategy> pageInfo = new PageInfo<>(strategyList);

        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(pageInfo.getList()));

        return pageVO;
    }

    @Override
    public FunctionalStrategyVO calculateFormPolicy(Long requestTypeId, Object conditions) {

        Assert.notNull(requestTypeId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        List<FunctionalStrategy> functionalStrategyList = functionalStrategyMapper.findFunctionalStrategies(requestTypeId, NumberConstant.THREE);
        Assert.notEmpty(functionalStrategyList, ResponseEnum.THE_FUNCTION_POLICY_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        // 条件
        Map<String, String> conditionMap = getConditions(conditions);

        FunctionalStrategy functionalStrategy = null;

        for (FunctionalStrategy function : functionalStrategyList) {
            if (execScript(function.getScript(), conditionMap)) {
                functionalStrategy = function;
                break;
            }
        }

        if (ObjectUtil.isNull(functionalStrategy)) {
            functionalStrategy = functionalStrategyList.stream()
                    .filter(a -> StrUtil.isBlank(a.getScript()))
                    .findAny().orElse(null);
        }

        Assert.notNull(functionalStrategy, ResponseEnum.NO_VALID_POLICY_WAS_FOUND.getMessage());

        return this.objectConversion(functionalStrategy);
    }

    @Override
    public FunctionalStrategyVO objectConversion(FunctionalStrategy functionalStrategy) {

        FunctionalStrategyVO functionalStrategyVO = super.objectConversion(functionalStrategy);
        if (ObjectUtil.isNotNull(functionalStrategyVO)) {
            Optional.ofNullable(functionalStrategy.getProcessId())
                    .ifPresent(a -> functionalStrategyVO.setProcess(processService.findOne(a)));

            Optional.ofNullable(functionalStrategy.getRequestTypeId())
                    .ifPresent(a -> functionalStrategyVO.setRequestType(requestTypeService.findOne(a)));

        }

        return functionalStrategyVO;
    }

    /**
     * 执行脚本
     *
     * @param script     脚本
     * @param conditions 条件
     * @return {@link Boolean} 是否成功
     */
    private Boolean execScript(String script, Map<String, String> conditions) {

        if (MapUtil.isNotEmpty(conditions)) {
            for (Map.Entry<String, String> entry : conditions.entrySet()) {
                script = StrUtil.replace(script, entry.getKey(), entry.getValue());
            }

            ExpressionParser parser = new SpelExpressionParser();
            try {
                return parser.parseExpression(script).getValue(boolean.class);
            }catch (Exception e) {
                log.error("execScript {}", e.getMessage());
                throw new ZtbWebException(ResponseEnum.SCRIPT_RUNNING_EXCEPTION);
            }
        }

        return Boolean.FALSE;
    }

    /**
     * 获取条件
     *
     * @param conditions 条件
     * @return {@link Map} 条件 key: 字段名，value: 字段值
     */
    private Map<String, String> getConditions(Object conditions) {

        if (ObjectUtil.isNotNull(conditions)) {

            if (conditions instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) conditions;
                return jsonObject.getInnerMap().entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey,
                                e -> Convert.toStr(e.getValue())));
            } else if (conditions instanceof Map) {
                return JSONObject.parseObject(JSON.toJSONString(conditions), new TypeReference<Map<String, String>>(){});
            } else {

                try {
                    List<Field> fields = CollectionUtil.newArrayList(ReflectUtil.getFields(conditions.getClass()));
                    return fields.stream().collect(Collectors.toMap(Field::getName,
                            a -> Convert.toStr(ReflectUtil.getFieldValue(conditions, a))));
                }catch (Exception e) {
                    log.error("Failed to obtain the condition value {}", e.getMessage());
                }
            }
        }

        return null;
    }

}
