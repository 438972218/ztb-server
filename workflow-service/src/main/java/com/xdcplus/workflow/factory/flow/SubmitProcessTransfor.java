package com.xdcplus.workflow.factory.flow;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Validator;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.workflow.common.pojo.vo.ProcessConfigVO;
import com.xdcplus.workflow.common.pojo.vo.ProcessStatusVO;
import com.xdcplus.workflow.common.pojo.vo.RequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 提交 流转过程
 * @author Rong.Jia
 * @date 2021/06/07
 */
@Slf4j
@Component
public class SubmitProcessTransfor extends BaseProcessTransfor {

    @Override
    public Boolean supportType(Integer flowOption) {
        return Validator.equal(NumberConstant.THREE, flowOption);
    }

    @Override
    public void postProcess(ProcessTransforParam processTransforParam) {

        RequestVO requestVO = processTransforParam.getRequest();
        Long userId = processTransforParam.getUserId();

        ProcessStatusVO processStatusVO = super.findProcessStatusByMark(Convert.toStr(NumberConstant.ZERO));

        List<ProcessConfigVO> currentConfigList = super.findConfigByProcessIdAndFromStatusId(requestVO.getProcess().getId(),
                processStatusVO.getId(), requestVO.getConfigVersion(), userId, requestVO.getCreatedUser());

        if (CollectionUtil.isEmpty(currentConfigList)) {
            log.error("A valid process configuration was not found");
            throw new ZtbWebException(ResponseEnum.A_VALID_PROCESS_CONFIGURATION_WAS_NOT_FOUND);
        }

        // 去向配置
        List<ProcessConfigVO> nextConfigList = getsConfigNonAddedDestination(currentConfigList,
                processTransforParam.getAgree().getFlowConditions());

        for (ProcessConfigVO processConfigVO : nextConfigList) {

            SyncFlow syncFlow = SyncFlow.builder()
                    .requestId(requestVO.getId())
                    .fromStatusId(requestVO.getStatus().getId())
                    .toStatusId(processConfigVO.getToStatus().getId())
                    .toRoleId(processConfigVO.getToRoleId())
                    .fromUserId(userId)
                    .toUserId(processConfigVO.getToUserId())
                    .description(processTransforParam.getDescription())
                    .flowOptionValue(NumberConstant.A_NEGATIVE)
                    .configVersion(requestVO.getConfigVersion())
                    .build();

            super.syncFlow(syncFlow);
            super.updateRequestStatusIdById(requestVO.getId(), processConfigVO.getToStatus().getId());
        }
    }


}
