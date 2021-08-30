package com.xdcplus.biz.remote.fallback;

import com.xdcplus.biz.remote.IeRequestRemote;
import com.xdcplus.ztb.common.remote.domain.workflow.dto.*;
import com.xdcplus.ztb.common.remote.domain.workflow.vo.ProcessConfigVO;
import com.xdcplus.ztb.common.remote.domain.workflow.vo.RequestFlowVO;
import com.xdcplus.ztb.common.remote.domain.workflow.vo.RequestVO;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Component
public class IeRequestRemoteFallbackFactory implements FallbackFactory<IeRequestRemote> {

    @Override
    public IeRequestRemote create(Throwable throwable) {
        return new IeRequestRemote() {

            @Override
            public ResponseVO<RequestVO> saveRequest(RequestDTO requestDTO) {
                return null;
            }

            @Override
            public ResponseVO<PageVO<RequestVO>> findRequest(RequestFilterDTO requestFilterDTO) {
                return null;
            }

            @Override
            public ResponseVO<RequestVO> findRequestById(@NotNull(message = "表单ID不能为空") Long requestId) {
                return null;
            }

            @Override
            public ResponseVO<PageVO<RequestVO>> handleMatters(HandleMattersFilterDTO handleMattersFilterDTO) {
                return null;
            }

            @Override
            public ResponseVO processTransfor(ProcessTransforDTO processTransforDTO) {
                return null;
            }

            @Override
            public ResponseVO<List<RequestFlowVO>> findRequestFlowByRequestId(@NotNull(message = "表单ID不能为空") Long requestId) {
                return null;
            }

            @Override
            public ResponseVO<PageVO<ProcessConfigVO>> findProcessConfigFilter(ProcessConfigFilterDTO processConfigFilterDTO) {
                return null;
            }
        };
    }
}
