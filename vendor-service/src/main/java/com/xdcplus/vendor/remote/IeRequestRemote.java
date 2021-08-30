package com.xdcplus.vendor.remote;

import com.xdcplus.vendor.remote.fallback.IeRequestRemoteFallbackFactory;
import com.xdcplus.ztb.common.remote.domain.workflow.dto.RequestFilterDTO;
import com.xdcplus.ztb.common.remote.domain.workflow.vo.RequestVO;
import com.xdcplus.ztb.common.tool.constants.ServiceConstant;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


/**
 * 即请求远程
 *
 * @author Fish.Fei
 * @date 2021/08/12
 */
@Component
@RequestMapping("/request")
@FeignClient(value = ServiceConstant.WORKFLOW_SERVICE, fallbackFactory = IeRequestRemoteFallbackFactory.class)
public interface IeRequestRemote {

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<PageVO<RequestVO>> findRequest(@SpringQueryMap RequestFilterDTO requestFilterDTO);

    @GetMapping(value = "/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<RequestVO> findRequestById(@PathVariable("requestId") @NotNull(message = "表单ID不能为空") Long requestId);

}
