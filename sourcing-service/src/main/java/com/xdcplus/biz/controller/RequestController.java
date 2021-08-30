package com.xdcplus.biz.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.xdcplus.biz.common.constants.ZtbConstant;
import com.xdcplus.biz.common.pojo.entity.BidSpecialist;
import com.xdcplus.biz.common.pojo.vo.BidSheetVO;
import com.xdcplus.biz.common.pojo.vo.BidSpecialistVO;
import com.xdcplus.biz.remote.service.IeService;
import com.xdcplus.biz.remote.service.PermService;
import com.xdcplus.biz.service.BidSheetService;
import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.remote.domain.workflow.dto.HandleMattersFilterDTO;
import com.xdcplus.ztb.common.remote.domain.workflow.dto.ProcessTransforDTO;
import com.xdcplus.ztb.common.remote.domain.workflow.dto.RequestDTO;
import com.xdcplus.ztb.common.remote.domain.workflow.dto.RequestFlowDTO;
import com.xdcplus.ztb.common.remote.domain.workflow.vo.RequestVO;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 表单(request)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-06 14:13:07
 */
@Api(tags = "表单(request)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/biz/request")
public class RequestController extends AbstractController {

    @Autowired
    private IeService ieService;

    @Autowired
    private PermService permService;

    @Autowired
    private BidSheetService bidSheetService;

    @ApiOperation("退回")
    @PostMapping(value = "/back", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO backRequest(@RequestBody RequestFlowDTO requestFlowDTO) {

        log.info("backRequest {}", requestFlowDTO.toString());

        ProcessTransforDTO processTransforDTO = new ProcessTransforDTO();
        processTransforDTO.setFlowOption(2);
        processTransforDTO.setRequestId(requestFlowDTO.getRequestId());
        processTransforDTO.setUserId(requestFlowDTO.getUserId());
        processTransforDTO.setDescription("退回");

        ProcessTransforDTO.SendBack sendBack = new ProcessTransforDTO.SendBack();
        sendBack.setToStatusId(requestFlowDTO.getStatusId());
        sendBack.setToUserId(requestFlowDTO.getToUserId());
        processTransforDTO.setSendBack(sendBack);

        ResponseVO responseVO = ieService.processTransfor(processTransforDTO);

        return responseVO;
    }

    @ApiOperation("同意")
    @PostMapping(value = "/approve", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO approveRequest(@RequestBody ProcessTransforDTO processTransforDTO) {

        log.info("approveRequest {}", processTransforDTO.toString());

        processTransforDTO.setFlowOption(1);
        ProcessTransforDTO.Agree agree = new ProcessTransforDTO.Agree();
        List<Long> roleIds = permService.queryByUserId(processTransforDTO.getUserId());
        agree.setRoleIds(roleIds);
        processTransforDTO.setAgree(agree);

        ResponseVO responseVO = ieService.processTransfor(processTransforDTO);

        return responseVO;
    }

    @ApiOperation("show")
    @GetMapping(value = "/show/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO showRequest(@PathVariable("requestId")
                                      @NotNull(message = "requestID不能为空") Long requestId) {

        log.info("showRequest {}", requestId);

        RequestVO requestVO = ieService.findRequestById(requestId);

        return ResponseVO.success(requestVO);
    }

    @ApiOperation("表单处理事项")
    @GetMapping(value = "/handleMatters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO handleMattersRequest(HandleMattersFilterDTO handleMattersFilterDTO) {

        log.info("handleMattersRequest {}", handleMattersFilterDTO.toString());

        List<RequestVO> requestVOS = ieService.handleMatters(handleMattersFilterDTO);

        ListIterator<RequestVO> it = requestVOS.listIterator();
        while(it.hasNext()){
            RequestVO requestVO =it.next();
            if(CollectionUtil.isNotEmpty(requestVO.getParent())){
                it.remove();
                it.add(requestVO.getParent().get(0));
            }
        }

        requestVOS = requestVOS.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(RequestVO::getId))), ArrayList::new)
        );

        //过滤招标单待评估节点的单子
//        ListIterator<RequestVO> it1 = requestVOS.listIterator();
//        while(it1.hasNext()){
//            RequestVO requestVO =it1.next();
//            if(ZtbConstant.BID_PROCESS.equals(requestVO.getProcess().getName())
//                    && ZtbConstant.BID_STATUS_PINGGU.contains(requestVO.getStatus().getName())){
//                BidSheetVO bidSheetVO = bidSheetService.showBidSheetByRequestId(requestVO.getId());
//                List<BidSpecialistVO> bidSpecialistVOS =bidSheetVO.getBidSpecialistVOS();
//                if(CollectionUtil.isNotEmpty(bidSpecialistVOS)){
//                    List<Long> userIds = bidSpecialistVOS.stream().map(BidSpecialistVO::getUserId).collect(Collectors.toList());
//                    if(!userIds.contains(handleMattersFilterDTO.getUserId())){
//                        it1.remove();
//                    }
//                }
//            }
//        }
        return ResponseVO.success(requestVOS);
    }

    @ApiOperation("招标审核单同意")
    @PostMapping(value = "/bidAuditSheet/approve", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO approveBidRequest(@RequestBody ProcessTransforDTO processTransforDTO) {

        log.info("approveRequest {}", processTransforDTO.toString());

        processTransforDTO.setFlowOption(1);
        ProcessTransforDTO.Agree agree = new ProcessTransforDTO.Agree();
        List<Long> roleIds = permService.queryByUserId(processTransforDTO.getUserId());
        agree.setRoleIds(roleIds);
        processTransforDTO.setAgree(agree);

        ResponseVO responseVO = ieService.processTransfor(processTransforDTO);

        RequestVO requestVO = ieService.findRequestById(processTransforDTO.getRequestId());

        if(requestVO.getStatus().getName().equals(ZtbConstant.ARCHIVED)){
            //无法做联动，操作人取不到
        }

        return responseVO;
    }

    @ApiOperation("招标审核单退回")
    @PostMapping(value = "/bidAuditSheet/back", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO backBidAuditSheet(@RequestBody RequestFlowDTO requestFlowDTO) {

        log.info("backBidAuditSheet {}", requestFlowDTO.toString());

        ProcessTransforDTO processTransforDTO = new ProcessTransforDTO();
        processTransforDTO.setFlowOption(4);
        processTransforDTO.setRequestId(requestFlowDTO.getRequestId());
        processTransforDTO.setDescription("取消");
        processTransforDTO.setUserId(requestFlowDTO.getUserId());

        ProcessTransforDTO.SendBack sendBack = new ProcessTransforDTO.SendBack();
        sendBack.setToStatusId(requestFlowDTO.getStatusId());
        sendBack.setToUserId(requestFlowDTO.getToUserId());
        processTransforDTO.setSendBack(sendBack);

        ResponseVO responseVO = ieService.processTransfor(processTransforDTO);

        RequestVO requestVO = ieService.findRequestById(processTransforDTO.getRequestId());

        if(requestVO.getStatus().getName().equals(ZtbConstant.CANCELED)){
            //无法与招标单做联动，操作人取不到
        }

        return responseVO;
    }

}
