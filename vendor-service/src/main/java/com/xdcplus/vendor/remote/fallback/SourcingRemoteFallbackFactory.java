package com.xdcplus.vendor.remote.fallback;

import com.xdcplus.vendor.remote.SourcingRemote;
import com.xdcplus.ztb.common.remote.domain.sourcing.PaidSheetInfoDTO;
import com.xdcplus.ztb.common.remote.domain.sourcing.PaidSheetInfoVO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 业务服务降级处理
 *
 * @author Rong.Jia
 * @date 2021/08/17 15:06:49
 */
@Slf4j
@Component
public class SourcingRemoteFallbackFactory implements FallbackFactory<SourcingRemote> {

    @Override
    public SourcingRemote create(Throwable throwable) {
        return new SourcingRemote() {
            @Override
            public ResponseVO<PaidSheetInfoVO> showPaidInvitationByRequestId(Long requestId) {
                log.error("showPaidInvitationByRequestId {}", throwable.getMessage());
                return ResponseVO.error();
            }

            @Override
            public ResponseVO updatePaidSheet(PaidSheetInfoDTO paidSheetDTO) {
                log.error("updatePaidSheet {}", throwable.getMessage());
                return ResponseVO.error();
            }
        };
    }
}
