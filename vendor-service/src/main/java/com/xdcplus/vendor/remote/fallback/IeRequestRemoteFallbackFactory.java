package com.xdcplus.vendor.remote.fallback;

import com.xdcplus.vendor.remote.IeRequestRemote;
import com.xdcplus.ztb.common.remote.domain.workflow.dto.RequestFilterDTO;
import com.xdcplus.ztb.common.remote.domain.workflow.vo.RequestVO;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Slf4j
@Component
public class IeRequestRemoteFallbackFactory implements FallbackFactory<IeRequestRemote> {

    @Override
    public IeRequestRemote create(Throwable throwable) {
        return new IeRequestRemote() {

            @Override
            public ResponseVO<PageVO<RequestVO>> findRequest(RequestFilterDTO requestFilterDTO) {
                return null;
            }

            @Override
            public ResponseVO<RequestVO> findRequestById(@NotNull(message = "表单ID不能为空") Long requestId) {
                return null;
            }

        };
    }
}
