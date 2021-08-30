package com.xdcplus.workflow.factory.matters;

import cn.hutool.core.lang.Validator;
import com.xdcplus.workflow.common.pojo.vo.RequestFlowVO;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 未办理事项处理器
 *
 * @author Rong.Jia
 * @date 2021/07/21
 */
@Component
public class UnhandledMattersProcessor extends BaseRequestHandleMattersProcessor {

    @Override
    public Boolean supportType(Integer handleOption) {
        return Validator.equal(NumberConstant.TWO, handleOption);
    }

    @Override
    public Set<Long> postProcess(RequestHandleMattersParam requestHandleMattersParam) {

        List<RequestFlowVO> requestFlowVOList = getRequestFlows(requestHandleMattersParam.getRoleIds(),
                requestHandleMattersParam.getUserId());

        return getRequestIds(requestFlowVOList, NumberConstant.A_NEGATIVE, Boolean.TRUE);
    }
}
