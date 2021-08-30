package com.xdcplus.workflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.workflow.common.pojo.dto.ProcessConfigLineDTO;
import com.xdcplus.workflow.common.pojo.entity.ProcessConfigLine;
import com.xdcplus.workflow.common.pojo.vo.ProcessConfigLineVO;
import com.xdcplus.workflow.mapper.ProcessConfigLineMapper;
import com.xdcplus.workflow.service.ProcessConfigLineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 流程配置线信息 服务实现类
 * @author Rong.Jia
 * @date  2021-06-17
 */
@Slf4j
@Service
public class ProcessConfigLineServiceImpl extends BaseServiceImpl<ProcessConfigLine, ProcessConfigLineVO, ProcessConfigLine, ProcessConfigLineMapper> implements ProcessConfigLineService {

    @Autowired
    private ProcessConfigLineMapper processConfigLineMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveConfigLine(Long processId, ProcessConfigLineDTO processConfigLineDTO, String version) {

        Assert.notNull(processId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        List<ProcessConfigLine> processConfigLineList = processConfigLineMapper.findConfigLineByProcessIdAndVersion(processId, version);

        if (CollectionUtil.isNotEmpty(processConfigLineList)
                && processConfigLineList.stream().anyMatch(a -> !StrUtil.equals(version, a.getVersion()))) {
            log.error("Process configuration versions are duplicated");
            throw new ZtbWebException(ResponseEnum.PROCESS_CONFIGURATION_VERSIONS_ARE_DUPLICATED);
        }

        ProcessConfigLine processConfigLine = BeanUtil.copyProperties(processConfigLineDTO, ProcessConfigLine.class);
        processConfigLine.setCreatedTime(DateUtil.current());
        processConfigLine.setProcessId(processId);
        processConfigLine.setVersion(version);
        processConfigLine.setFromMark(processConfigLineDTO.getFrom());
        processConfigLine.setToMark(processConfigLineDTO.getTo());

        return this.save(processConfigLine);
    }

    @Override
    public List<ProcessConfigLine> findConfigLine(Long processId, @Nullable String version) {

        Assert.notNull(processId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return processConfigLineMapper.findConfigLineByProcessIdAndVersion(processId, version);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteConfigLine(Long processId, String version) {

        List<ProcessConfigLine> processConfigLineList = processConfigLineMapper.findConfigLineByProcessIdAndVersion(processId, version);
        Assert.notEmpty(processConfigLineList, ResponseEnum.THE_PROCESS_CONFIGURATION_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        Set<Long> ids = processConfigLineList.stream().map(ProcessConfigLine::getId).collect(Collectors.toSet());

        return this.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateConfigLine(Long processId, ProcessConfigLineDTO processConfigLineDTO, String version) {

        this.deleteConfigLine(processId, version);

        return this.saveConfigLine(processId, processConfigLineDTO, version);
    }


}
