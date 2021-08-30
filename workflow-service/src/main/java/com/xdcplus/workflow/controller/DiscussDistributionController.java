package com.xdcplus.workflow.controller;


import cn.hutool.extra.validation.ValidationUtil;
import com.xdcplus.workflow.common.pojo.dto.DiscussGroupFilterDTO;
import com.xdcplus.workflow.common.pojo.dto.PostDiscussionsDTO;
import com.xdcplus.workflow.common.pojo.dto.ReplyDiscussDTO;
import com.xdcplus.workflow.service.DiscussDistributionService;
import com.xdcplus.workflow.common.pojo.vo.DiscussGroupVO;
import com.xdcplus.workflow.common.pojo.vo.DiscussRecordVO;
import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.ztb.common.tool.validator.groupvlidator.PageGroupValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *  讨论模块前端控制器
 *
 * @author Rong.Jia
 * @since 2021-08-18
 */
@Api(tags = "讨论模块管理")
@Validated
@Slf4j
@RestController
@RequestMapping("/discuss/")
public class DiscussDistributionController extends AbstractController {

    @Autowired
    private DiscussDistributionService discussDistributionService;

    @ApiOperation("发起讨论")
    @PostMapping(value = "initiate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO postDiscussions(@RequestBody PostDiscussionsDTO postDiscussionsDTO) {

        log.info("postDiscussions {}", postDiscussionsDTO.toString());

        discussDistributionService.postDiscussions(postDiscussionsDTO);

        return ResponseVO.success();
    }

    @ApiOperation("回复讨论")
    @PutMapping(value = "reply", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO replyDiscuss(@RequestBody ReplyDiscussDTO replyDiscussDTO) {

        log.info("replyDiscuss {}", replyDiscussDTO.toString());

        discussDistributionService.replyDiscuss(replyDiscussDTO);

        return ResponseVO.success();
    }

    @ApiOperation("根据表单ID查询讨论组列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "requestId", dataType = "Long", value = "表单ID", required = true),
    })
    @GetMapping(value = "group/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<DiscussGroupVO>> findDiscussGroupByRequestId(@PathVariable("requestId")
                                                              @NotNull(message = "表单ID不能为空") Long requestId) {

        log.info("findDiscussGroupByRequestId {}", requestId);

        List<DiscussGroupVO> discussGroupVOList = discussDistributionService.findDiscussGroup(requestId);

        return ResponseVO.success(discussGroupVOList);
    }

    @ApiOperation("查询讨论组列表")
    @GetMapping(value = "group", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<DiscussGroupVO>> findDiscussGroup(DiscussGroupFilterDTO filterDTO) {

        log.info("findDiscussGroup {}", filterDTO);

        ValidationUtil.validate(filterDTO, PageGroupValidator.class);
        PageVO<DiscussGroupVO> discussGroupVOList = discussDistributionService.findDiscussGroup(filterDTO);

        return ResponseVO.success(discussGroupVOList);
    }

    @ApiOperation("根据讨论组ID查询讨论记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "groupId", dataType = "Long", value = "讨论组ID", required = true),
    })
    @GetMapping(value = "/record/group/{groupId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<DiscussRecordVO>> findDiscussRecordsByGroupId(@PathVariable("groupId")
                                                      @NotNull(message = "讨论组ID不能为空") Long groupId) {

        log.info("findDiscussRecordsByGroupId {}", groupId);

        List<DiscussRecordVO> discussRecordVOList = discussDistributionService.findDiscussRecordsByGroupId(groupId);

        return ResponseVO.success(discussRecordVOList);
    }

    @ApiOperation("根据表单ID查询讨论记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "requestId", dataType = "Long", value = "表单ID", required = true),
    })
    @GetMapping(value = "/record/request/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<DiscussRecordVO>> findDiscussRecordsByRequestId(@PathVariable("requestId")
                                                                         @NotNull(message = "表单ID不能为空") Long requestId) {

        log.info("findDiscussRecordsByRequestId {}", requestId);

        List<DiscussRecordVO> discussRecordVOList = discussDistributionService.findDiscussRecordsByRequestId(requestId);

        return ResponseVO.success(discussRecordVOList);
    }







}
