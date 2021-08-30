package com.xdcplus.vendor.remote;

import com.xdcplus.vendor.remote.fallback.PermRemoteFallbackFactory;
import com.xdcplus.ztb.common.remote.domain.sourcing.PaidSheetInfoDTO;
import com.xdcplus.ztb.common.remote.domain.sourcing.PaidSheetInfoVO;
import com.xdcplus.ztb.common.tool.constants.ServiceConstant;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Component
@RequestMapping("/")
@FeignClient(value = ServiceConstant.SOURCING_SERVICE, fallbackFactory = PermRemoteFallbackFactory.class)
public interface SourcingRemote {

    /**
     * show竞价单ByRequestId
     *
     * @param requestId
     * @return
     */
    @GetMapping(value = "paidSheet/showByRequestId/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<PaidSheetInfoVO> showPaidInvitationByRequestId(@PathVariable("requestId")
                                                              @NotNull(message = "requestID不能为空") Long requestId);

    /**
     * 修改竞价单
     * @param paidSheetDTO
     * @return
     */
    @PutMapping(value = "paidSheet", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO updatePaidSheet(@RequestBody PaidSheetInfoDTO paidSheetDTO);

}
