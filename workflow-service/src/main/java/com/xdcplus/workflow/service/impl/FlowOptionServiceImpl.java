package com.xdcplus.workflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.workflow.common.pojo.dto.FlowOptionDTO;
import com.xdcplus.workflow.common.pojo.entity.FlowOption;
import com.xdcplus.workflow.common.pojo.vo.FlowOptionVO;
import com.xdcplus.workflow.mapper.FlowOptionMapper;
import com.xdcplus.workflow.service.FlowOptionService;
import com.xdcplus.workflow.service.RequestFlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 流程操作表 服务实现类
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
@Slf4j
@Service
public class FlowOptionServiceImpl extends BaseServiceImpl<FlowOption, FlowOptionVO, FlowOption, FlowOptionMapper> implements FlowOptionService {

    @Autowired
    private FlowOptionMapper flowOptionMapper;

    @Autowired
    private RequestFlowService flowService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveFlowOption(FlowOptionDTO flowOptionDTO) {

        FlowOption flowOption = flowOptionMapper.findFlowOptionByValue(flowOptionDTO.getValue());
        if (ObjectUtil.isNotNull(flowOption)) {
            log.error("saveFlowOption() The flow option already exists");
            throw new ZtbWebException(ResponseEnum.THE_FLOW_OPTION_ALREADY_EXISTS);
        }

        flowOption = flowOptionMapper.findFlowOptionByValueStr(flowOptionDTO.getValueString());
        if (ObjectUtil.isNotNull(flowOption)) {
            log.error("saveFlowOption() The flow option already exists");
            throw new ZtbWebException(ResponseEnum.THE_FLOW_OPTION_ALREADY_EXISTS);
        }

        flowOption = new FlowOption();
        BeanUtil.copyProperties(flowOptionDTO, flowOption);
        flowOption.setCreatedTime(DateUtil.current());

        return this.save(flowOption);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateFlowOption(FlowOptionDTO flowOptionDTO) {

        FlowOption flowOption = this.getById(flowOptionDTO.getId());
        if (ObjectUtil.isNull(flowOption)) {
            log.error("updateFlowOption() The flow option does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.THE_FLOW_OPTION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED);
        }

        if (ObjectUtil.notEqual(flowOption.getValue(), flowOptionDTO.getValue())) {
            if (ObjectUtil.isNotNull( flowOptionMapper.findFlowOptionByValue(flowOptionDTO.getValue()))) {
                log.error("updateFlowOption() The flow option already exists");
                throw new ZtbWebException(ResponseEnum.THE_FLOW_OPTION_ALREADY_EXISTS);
            }
        }

        if (!StrUtil.equals(flowOption.getValueString(), flowOptionDTO.getValueString())) {
            if (ObjectUtil.isNotNull(flowOptionMapper.findFlowOptionByValueStr(flowOptionDTO.getValueString()))) {
                log.error("updateFlowOption() The flow option already exists");
                throw new ZtbWebException(ResponseEnum.THE_FLOW_OPTION_ALREADY_EXISTS);
            }
        }

        flowOption.setValue(flowOptionDTO.getValue());
        flowOption.setValueString(flowOptionDTO.getValueString());
        flowOption.setUpdatedUser(flowOptionDTO.getUpdatedUser());
        flowOption.setUpdatedTime(DateUtil.current());
        flowOption.setDescription(flowOptionDTO.getDescription());

        return this.updateById(flowOption);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteFlowOption(Long flowOptionId) {

        Assert.notNull(flowOptionId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        FlowOption flowOption = this.getById(flowOptionId);

        Assert.notNull(flowOption, ResponseEnum.THE_FLOW_OPTION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());
        Assert.isFalse(flowService.existRequestFlowByFlowOption(flowOption.getValue()), ResponseEnum.DATA_QUOTE.getMessage());

        return this.removeById(flowOptionId);
    }

    @Override
    public List<FlowOptionVO> findFlowOption() {

        List<FlowOption> flowOptionList = flowOptionMapper.findFlowOptionsNot(CollectionUtil.newArrayList(NumberConstant.A_NEGATIVE));

        return this.objectConversion(flowOptionList);
    }
}
