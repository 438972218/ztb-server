package com.xdcplus.biz.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.xdcplus.biz.common.config.AssemblyBuilder;
import com.xdcplus.biz.common.constants.ZtbConstant;
import com.xdcplus.biz.remote.service.IeService;
import com.xdcplus.biz.remote.service.PermService;
import com.xdcplus.biz.service.PaidVendorService;
import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.remote.domain.workflow.dto.*;
import com.xdcplus.ztb.common.remote.domain.workflow.vo.RequestVO;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.dto.PaidSheetDTO;
import com.xdcplus.biz.common.pojo.dto.PaidSheetFilterDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.biz.common.pojo.vo.PaidSheetVO;
import com.xdcplus.biz.service.PaidSheetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validation;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 竞价单(PaidSheet)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-08-16 14:02:03
 */
@Api(tags = "竞价单(PaidSheet)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("paidSheet")
public class PaidSheetController extends AbstractController {

    @Autowired
    private PaidSheetService paidSheetService;

    @Autowired
    private IeService ieService;

    @Autowired
    private PermService permService;

    @Autowired
    private PaidVendorService paidVendorService;

    @ApiOperation("新增竞价单")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO savePaidSheet(@RequestBody PaidSheetDTO paidSheetDTO) {

        log.info("savePaidSheet {}", paidSheetDTO.toString());

        paidSheetDTO.setCreatedUser(getAccount());
        paidSheetService.savePaidSheet(paidSheetDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改竞价单")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updatePaidSheet(@RequestBody PaidSheetDTO paidSheetDTO) {

        log.info("updatePaidSheet {}", paidSheetDTO.toString());

        paidSheetDTO.setUpdatedUser(getAccount());
        paidSheetService.updatePaidSheet(paidSheetDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除竞价单")
    @DeleteMapping(value = "/{paidSheetId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "paidSheetId", dataType = "Long", value = "竞价单ID", required = true),
    })
    public ResponseVO deletePaidSheetLogical(@PathVariable("paidSheetId")
                                             @NotNull(message = "竞价单ID不能为空") Long paidSheetId) {

        log.info("deletePaidSheetLogical {}", paidSheetId);

        paidSheetService.deletePaidSheetLogical(paidSheetId);

        return ResponseVO.success();
    }

    @ApiOperation("查询竞价单")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<PaidSheetVO>> findPaidSheet(PaidSheetFilterDTO paidSheetFilterDTO) {

        log.info("findPaidSheet {}", paidSheetFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(paidSheetFilterDTO);

        return ResponseVO.success(paidSheetService.queryPaidSheet(paidSheetFilterDTO));
    }



    @ApiOperation("新增竞价单返回VO")
    @PostMapping(value = "returnvo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO savePaidSheetReturnVO(@RequestBody PaidSheetDTO paidSheetDTO) {

        log.info("savePaidSheetReturnVO {}", paidSheetDTO.toString());

        paidSheetDTO.setCreatedUser(getAccount());
        paidSheetDTO.setTitle("竞价单-" + paidSheetDTO.getCreatedUser() + "-" + DateUtil.formatDateTime(new Date()));
        PaidSheetVO paidSheetVO = paidSheetService.savePaidSheetReturnVO(paidSheetDTO);

        return ResponseVO.success(paidSheetVO);
    }


    @ApiOperation("show竞价单ByRequestId")
    @GetMapping(value = "showByRequestId/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "requestId", dataType = "Long", value = "requestID", required = true),
    })
    public ResponseVO showPaidInvitationByRequestId(@PathVariable("requestId")
                                                    @NotNull(message = "requestID不能为空") Long requestId) {

        log.info("showPaidInvitationByRequestId {}", requestId);

        PaidSheetVO paidSheetVO = paidSheetService.showPaidSheetByRequestId(requestId);

        return ResponseVO.success(paidSheetVO);
    }

    @ApiOperation("查询竞价单(供应商)")
    @GetMapping(value = "/vendor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<PaidSheetVO>> findPaidSheetByVendor(PaidSheetFilterDTO paidSheetFilterDTO) {

        log.info("findPaidSheetByVendor {}", paidSheetFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(paidSheetFilterDTO);
        PageVO<PaidSheetVO> paidSheetVOPageVO = paidSheetService.queryPaidSheetByVendor(paidSheetFilterDTO);

        return ResponseVO.success(paidSheetVOPageVO);
    }

    @ApiOperation("查询竞价单(request)")
    @GetMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<PaidSheetVO>> findPaidSheetWithRequest(PaidSheetFilterDTO paidSheetFilterDTO) {

        log.info("findPaidSheetByVendor {}", paidSheetFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(paidSheetFilterDTO);
        PageVO<PaidSheetVO> paidSheetVOPageVO = paidSheetService.queryPaidSheetWithRequest(paidSheetFilterDTO);

        return ResponseVO.success(paidSheetVOPageVO);
    }

    @ApiOperation("提交审批竞价单")
    @PostMapping(value = "/submit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PaidSheetVO> submitPaidSheet(@RequestBody PaidSheetDTO paidSheetDTO) {

        log.info("submitPaidSheet {}", paidSheetDTO.toString());

        paidSheetDTO.setCreatedUser(getAccount());

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setTitle("竞价" + "-" + paidSheetDTO.getName() + "-" + DateUtil.formatDate(new Date()));
        requestDTO.setRuleId(paidSheetDTO.getRuleId());
        requestDTO.setProcessId(paidSheetDTO.getProcessId());
        requestDTO.setCreatedUser(paidSheetDTO.getCreatedUser());
        submitBeforeJudgeStatus(paidSheetDTO,requestDTO);
        RequestVO requestVO = ieService.saveRequest(requestDTO);

        paidSheetDTO.setRequestId(requestVO.getId());
        PaidSheetVO paidSheetVO = paidSheetService.savePaidSheetReturnVO(paidSheetDTO);
        paidSheetDTO.setId(paidSheetVO.getId());
        submitAfterJudgeStatus(paidSheetDTO);

        return ResponseVO.success(paidSheetService.showPaidSheetByRequestId(paidSheetVO.getRequestId()));
    }

    @ApiOperation("提交审批竞价单2")
    @PostMapping(value = "/submit2", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PaidSheetVO> submitPaidSheet2(@RequestBody PaidSheetDTO paidSheetDTO) {

        log.info("submitPaidSheet2 {}", paidSheetDTO.toString());

        paidSheetDTO.setCreatedUser(getAccount());

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setTitle("竞价" + "-" + paidSheetDTO.getName() + "-" + DateUtil.formatDate(new Date()));
        requestDTO.setRuleId(paidSheetDTO.getRuleId());
        requestDTO.setProcessId(paidSheetDTO.getProcessId());
        requestDTO.setCreatedUser(paidSheetDTO.getCreatedUser());
        submitBeforeJudgeStatus2(paidSheetDTO,requestDTO);
        RequestVO requestVO = ieService.saveRequest(requestDTO);

        paidSheetDTO.setRequestId(requestVO.getId());
        PaidSheetVO paidSheetVO = paidSheetService.savePaidSheetReturnVO(paidSheetDTO);
        paidSheetDTO.setId(paidSheetVO.getId());
        submitAfterJudgeStatus2(paidSheetDTO);

        return ResponseVO.success(paidSheetService.showPaidSheetByRequestId(paidSheetVO.getRequestId()));
    }

    @ApiOperation("流转竞价单")
    @PostMapping(value = "/agree", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO agreePaidSheet(@RequestBody RequestFlowDTO requestFlowDTO) {

        log.info("agreePaidSheet {}", requestFlowDTO.toString());

        ProcessTransforDTO processTransforDTO = approveBeforeJudgeStatus(requestFlowDTO);

        ResponseVO responseVO = ieService.processTransfor(processTransforDTO);

        approveAfterJudgeStatus(requestFlowDTO);

        return responseVO;
    }

    @ApiOperation("流转竞价单2")
    @PostMapping(value = "/agree2", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO agreePaidSheet2(@RequestBody RequestFlowDTO requestFlowDTO) {

        log.info("agreePaidSheet2 {}", requestFlowDTO.toString());

        ProcessTransforDTO processTransforDTO = approveBeforeJudgeStatus2(requestFlowDTO);

        ResponseVO responseVO = ieService.processTransfor(processTransforDTO);

        approveAfterJudgeStatus(requestFlowDTO);

        return responseVO;
    }

    private void submitBeforeJudgeStatus(PaidSheetDTO paidSheetDTO,RequestDTO requestDTO) {
//        Circulation circulation=new Circulation();
//        AssemblyBuilder assemblyBuilder = AssemblyBuilder.builder();
//        if(paidSheetDTO.getApprovalProcess()!=null){
//            assemblyBuilder.addParameter("approvalProcess",paidSheetDTO.getApprovalProcess());
//            circulation.setFlowConditions(assemblyBuilder.build());
//        }
//        circulation.setUserId(paidSheetDTO.getUserId());
//        requestDTO.setCirculation(circulation);
    }

    private void submitAfterJudgeStatus(PaidSheetDTO paidSheetDTO) {
//        PaidSheetVO paidSheetVO = paidSheetService.queryPaidSheetById(paidSheetDTO.getId());
//        RequestVO requestVO = ieService.findRequestById(paidSheetVO.getRequestId());
//
//        if (paidSheetVO.getApprovalProcess() != null && paidSheetVO.getApprovalProcess()!=0) {
//            RequestDTO requestDTO = new RequestDTO();
//            requestDTO.setRuleId(1L);
//            requestDTO.setProcessId(ZtbConstant.SHENGPI_PROCESS);
//            StringBuffer sb = new StringBuffer(requestVO.getTitle());
//            sb.insert(requestVO.getTitle().indexOf("-"),"审批单");
//            requestDTO.setTitle(sb.toString());
//            requestDTO.setCreatedUser(paidSheetVO.getCreatedUser());
//            HashSet<Long> parentIds = new HashSet<>();
//            parentIds.add(paidSheetVO.getRequestId());
//            requestDTO.setParentIds(parentIds);
//
//            Circulation circulation=new Circulation();
//            circulation.setUserId(paidSheetDTO.getUserId());
//            requestDTO.setCirculation(circulation);
//
//            ieService.saveRequest(requestDTO);
//        }
    }

    private ProcessTransforDTO approveBeforeJudgeStatus(RequestFlowDTO requestFlowDTO){
        ProcessTransforDTO processTransforDTO = new ProcessTransforDTO();
        processTransforDTO.setFlowOption(1);
        processTransforDTO.setUserId(requestFlowDTO.getUserId());
        processTransforDTO.setRequestId(requestFlowDTO.getRequestId());
        processTransforDTO.setDescription(requestFlowDTO.getDescription());

        ProcessTransforDTO.Agree agree = new ProcessTransforDTO.Agree();

        Long requestId = requestFlowDTO.getRequestId();
        List<RequestVO> requestVOS = ieService.findRequestByParentId(requestId);
        if (CollectionUtil.isNotEmpty(requestVOS)) {
            RequestVO childRequestVO = requestVOS.get(requestVOS.size() - 1);

            AssemblyBuilder assemblyBuilder = AssemblyBuilder.builder();
            assemblyBuilder.addParameter("requestRelationRequestStatusId",childRequestVO.getStatus().getId()+"L");
            agree.setFlowConditions(assemblyBuilder.build());
        }
        List<Long> roleIds = permService.queryByUserId(requestFlowDTO.getUserId());
        agree.setRoleIds(roleIds);
        processTransforDTO.setAgree(agree);

        return processTransforDTO;
    }

    private void approveAfterJudgeStatus(RequestFlowDTO requestFlowDTO){
    }

    private void submitBeforeJudgeStatus2(PaidSheetDTO paidSheetDTO,RequestDTO requestDTO) {
        Circulation circulation=new Circulation();
        circulation.setUserId(paidSheetDTO.getUserId());
        requestDTO.setCirculation(circulation);

        //新增策略条件
        FormFlowStrategyDTO formFlowStrategyDTO=new FormFlowStrategyDTO();
//        Object obj = JSONArray.toJSON(paidSheetDTO);
//        formFlowStrategyDTO.setStrategyConditions(obj);
        formFlowStrategyDTO.setTypeId(paidSheetDTO.getTypeId());
        requestDTO.setStrategy(formFlowStrategyDTO);

        String json = JSONObject.toJSONString(paidSheetDTO);
        Map map = JSONObject.parseObject(json, Map.class);
        formFlowStrategyDTO.setStrategyConditions(map);
    }

    private void submitAfterJudgeStatus2(PaidSheetDTO paidSheetDTO) {
//        PaidSheetVO paidSheetVO = paidSheetService.queryPaidSheetById(paidSheetDTO.getId());
//        RequestVO requestVO = ieService.findRequestById(paidSheetVO.getRequestId());
//
//        if (paidSheetVO.getApprovalProcess() != null && paidSheetVO.getApprovalProcess()!=0) {
//            RequestDTO requestDTO = new RequestDTO();
//            requestDTO.setRuleId(ZtbConstant.SHENGHE_RULE_ID);
//            StringBuffer sb = new StringBuffer(requestVO.getTitle());
//            sb.insert(requestVO.getTitle().indexOf("-"),"审批单");
//            requestDTO.setTitle(sb.toString());
//            requestDTO.setCreatedUser(paidSheetVO.getCreatedUser());
//            HashSet<Long> parentIds = new HashSet<>();
//            parentIds.add(paidSheetVO.getRequestId());
//            requestDTO.setParentIds(parentIds);
//
//            //新增策略条件
//            FormFlowStrategyDTO formFlowStrategyDTO=new FormFlowStrategyDTO();
//            formFlowStrategyDTO.setTypeId(ZtbConstant.SHENGHE_TYPE_ID);
//            requestDTO.setStrategy(formFlowStrategyDTO);
//
//            Circulation circulation=new Circulation();
//            circulation.setUserId(paidSheetDTO.getUserId());
//            requestDTO.setCirculation(circulation);
//
//            ieService.saveRequest(requestDTO);
//        }
    }

    private ProcessTransforDTO approveBeforeJudgeStatus2(RequestFlowDTO requestFlowDTO){
        ProcessTransforDTO processTransforDTO = new ProcessTransforDTO();
        processTransforDTO.setFlowOption(1);
        processTransforDTO.setUserId(requestFlowDTO.getUserId());
        processTransforDTO.setRequestId(requestFlowDTO.getRequestId());
        processTransforDTO.setDescription(requestFlowDTO.getDescription());

        ProcessTransforDTO.Agree agree = new ProcessTransforDTO.Agree();

        RequestVO requestVO = ieService.findRequestById(requestFlowDTO.getRequestId());
        if (requestVO.getStatus().getName().equals(ZtbConstant.TOAUDIT)) {
            Long requestId = requestFlowDTO.getRequestId();
            List<RequestVO> requestVOS = ieService.findRequestByParentId(requestId);
            if (CollectionUtil.isNotEmpty(requestVOS)) {
                RequestVO childRequestVO = requestVOS.get(requestVOS.size() - 1);

                AssemblyBuilder assemblyBuilder = AssemblyBuilder.builder();
                assemblyBuilder.addParameter("requestRelationRequestStatusId",childRequestVO.getStatus().getId()+"L");
                agree.setFlowConditions(assemblyBuilder.build());
            }
        }

        List<Long> roleIds = permService.queryByUserId(requestFlowDTO.getUserId());
        agree.setRoleIds(roleIds);
        processTransforDTO.setAgree(agree);

        return processTransforDTO;
    }

}
