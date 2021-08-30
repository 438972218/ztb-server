package com.xdcplus.workflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.workflow.common.pojo.vo.ProcessConfigVO;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.workflow.common.pojo.query.ProcessConfigQuery;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.workflow.common.pojo.bo.ProcessConfigBO;
import com.xdcplus.workflow.common.pojo.dto.*;
import com.xdcplus.workflow.common.pojo.entity.ProcessConfig;
import com.xdcplus.workflow.mapper.ProcessConfigMapper;
import com.xdcplus.workflow.service.*;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.utils.PageableUtils;
import com.xdcplus.ztb.common.tool.utils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 流程配置表 服务实现类
 *
 * @author Rong.Jia
 * @date 2021-05-31
 */
@Slf4j
@Service
public class ProcessConfigServiceImpl extends BaseServiceImpl<ProcessConfigBO, ProcessConfigVO, ProcessConfig, ProcessConfigMapper> implements ProcessConfigService {

    @Autowired
    private ProcessConfigMapper processConfigMapper;

    @Autowired
    private ProcessStatusService processStatusService;

    @Autowired
    private ProcessService processService;

    @Autowired
    private QualifierService qualifierService;

    @Autowired
    private RequestService requestService;

    @Override
    public Boolean existConfigByProcessId(Long processId) {

        if (ObjectUtil.isNotNull(processId)) {
            List<ProcessConfig> processConfigList = processConfigMapper.findConfigByProcessId(processId);
            return CollectionUtil.isNotEmpty(processConfigList) ? Boolean.TRUE : Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    @Override
    public List<ProcessConfigVO> findConfigByRequestId(Long requestId) {

        List<ProcessConfigBO> processConfigBOList = processConfigMapper.findConfigByRequestId(requestId);

        return this.objectConversion(processConfigBOList);
    }

    @Override
    public List<ProcessConfigVO> findConfigByProcessIdAndFromStatusId(Long processId, Long fromStatusId, String version) {

        if (ObjectUtil.isAllNotEmpty(processId, fromStatusId)) {
            return this.objectConversion(processConfigMapper.findConfigByProcessIdAndFromStatusId(processId, fromStatusId, version));
        }

        return null;
    }

    @Override
    public Boolean existConfigByProcessIdAndVersion(Long processId, String version) {

        List<ProcessConfig> processConfigList = processConfigMapper.findConfigByProcessIdAndVersion(processId, version);

        return CollectionUtil.isNotEmpty(processConfigList) ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public List<String> findConfigVersionByProcessId(Long processId) {

        Assert.notNull(processId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return processConfigMapper.findConfigVersionByProcessId(processId);
    }

    @Override
    public PageVO<ProcessConfigVO> findProcessConfig(ProcessConfigFilterDTO processConfigFilterDTO) {

        PageVO<ProcessConfigVO> pageVO = new PageVO<>();

        if (processConfigFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(processConfigFilterDTO);
        }

        ProcessConfigQuery query = new ProcessConfigQuery();
        BeanUtil.copyProperties(processConfigFilterDTO, query);
        query.setArchiveId(processStatusService.findProcessStatusByMark(Convert.toStr(NumberConstant.TWO)).getId());

        List<ProcessConfigBO> processConfigBOList = processConfigMapper.findProcessConfig(query);

        PageInfo<ProcessConfigBO> pageInfo = new PageInfo<>(processConfigBOList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(processConfigBOList, Boolean.TRUE));

        return pageVO;
    }

    @Override
    public List<ProcessConfigBO> findConfigAssociatedByProcessId(Long processId, String version) {

        Assert.notNull(processId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return processConfigMapper.findConfigAssociatedByProcessId(processId, version);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveProcessConfig(List<ProcessConfig> processConfigList) {

        Assert.notEmpty(processConfigList,
                ResponseEnum.THE_PROCESS_CONFIGURATION_INFORMATION_IS_EMPTY.getMessage());

        processConfigList.forEach(this::save);
    }

    @Override
    public ProcessConfigVO objectConversion(ProcessConfigBO processConfigBO) {

        ProcessConfigVO processConfigVO = super.objectConversion(processConfigBO);
        if (ObjectUtil.isNotNull(processConfigVO)) {
            processConfigVO.setFromStatus(processStatusService.objectConversion(processConfigBO.getFromStatus()));
            processConfigVO.setToStatus(processStatusService.objectConversion(processConfigBO.getToStatus()));
            processConfigVO.setProcess(processService.objectConversion(processConfigBO.getProcess()));

            if (ObjectUtil.isNotNull(processConfigBO.getQualifier())) {
                processConfigVO.setQualifier(qualifierService.objectConversion(processConfigBO.getQualifier()));
            }
        }

        return processConfigVO;
    }

    /**
     * 对象转换
     *
     * @param processConfigBOList 流程配置BO
     * @param isStatisticsForm     是否统计表单 true: 是，false: 否
     * @return {@link List<ProcessConfigVO>} 流程配置BO
     */
    private List<ProcessConfigVO> objectConversion(List<ProcessConfigBO> processConfigBOList, Boolean isStatisticsForm) {
        if (CollectionUtil.isNotEmpty(processConfigBOList)) {
            return processConfigBOList.stream()
                    .map(a -> this.objectConversion(a, isStatisticsForm))
                    .collect(Collectors.toList());
        }
        return null;
    }

    /**
     * 对象转换
     *
     * @param processConfigBO 流程配置BO
     * @param isStatisticsForm  是否统计表单 true: 是，false: 否
     * @return {@link ProcessConfigVO}
     */
    private ProcessConfigVO objectConversion(ProcessConfigBO processConfigBO, Boolean isStatisticsForm) {

        ProcessConfigVO processConfigVO = this.objectConversion(processConfigBO);
        if (ObjectUtil.isNotNull(processConfigVO) && isStatisticsForm) {
            Optional.ofNullable(processConfigVO.getToStatus().getId())
                    .ifPresent(a -> processConfigVO.setRequestNumber(requestService.countRequestByStatusId(a)));
        }

        return processConfigVO;
    }





























}
