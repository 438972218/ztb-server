package com.xdcplus.biz.remote;

import com.xdcplus.biz.remote.fallback.IeRequestRemoteFallbackFactory;
import com.xdcplus.ztb.common.remote.domain.workflow.dto.*;
import com.xdcplus.ztb.common.remote.domain.workflow.vo.ProcessConfigVO;
import com.xdcplus.ztb.common.remote.domain.workflow.vo.RequestFlowVO;
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
import java.util.List;


/**
 * 即请求远程
 *
 * @author Fish.Fei
 * @date 2021/08/12
 */
@Component
@RequestMapping("/")
@FeignClient(value = ServiceConstant.WORKFLOW_SERVICE, fallbackFactory = IeRequestRemoteFallbackFactory.class)
public interface IeRequestRemote {

    @PostMapping(value = "request", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<RequestVO> saveRequest(@RequestBody RequestDTO requestDTO);

    @GetMapping(value = "request", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<PageVO<RequestVO>> findRequest(@SpringQueryMap RequestFilterDTO requestFilterDTO);

    @GetMapping(value = "request/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<RequestVO> findRequestById(@PathVariable("requestId") @NotNull(message = "表单ID不能为空") Long requestId);

    @GetMapping(value = "request/handleMatters", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<PageVO<RequestVO>> handleMatters(@SpringQueryMap HandleMattersFilterDTO handleMattersFilterDTO);


    @PostMapping(value = "requestFlow", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO processTransfor(@RequestBody
                                       ProcessTransforDTO processTransforDTO);

    @GetMapping(value = "requestFlow/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<List<RequestFlowVO>> findRequestFlowByRequestId(@PathVariable("requestId")
                                                               @NotNull(message = "表单ID不能为空")
                                                                       Long requestId);

    @GetMapping(value = "processConfig/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<PageVO<ProcessConfigVO>> findProcessConfigFilter(@SpringQueryMap ProcessConfigFilterDTO processConfigFilterDTO);


}
