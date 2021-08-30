package com.xdcplus.workflow.controller;

import com.xdcplus.workflow.common.pojo.dto.RequestStrategyDTO;
import com.xdcplus.workflow.common.pojo.vo.FunctionalStrategyVO;
import com.xdcplus.workflow.service.FunctionalStrategyService;
import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
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

/**
 * 功能策略信息前端控制器
 *
 * @author Rong.Jia
 * @since 2021-08-06
 */
@Slf4j
@Api(tags = "功能策略模块管理")
@Validated
@RestController
@RequestMapping("/functionalStrategy")
public class FunctionalStrategyController {

    @Autowired
    private FunctionalStrategyService functionalStrategyService;

    @ApiOperation("同步表单策略")
    @PostMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO syncRequestStrategy(@RequestBody RequestStrategyDTO requestStrategyDTO) {

        log.info("syncRequestStrategy {}", requestStrategyDTO.toString());

        functionalStrategyService.syncRequestStrategy(requestStrategyDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除策略")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "Long", value = "策略ID", required = true),
    })
    public ResponseVO deleteStrategy(@PathVariable("id")
                                        @NotNull(message = "策略ID不能为空")
                                                Long id) {

        log.info("deleteStrategy {}", id);

        functionalStrategyService.deleteStrategy(id);

        return ResponseVO.success();
    }

    @ApiOperation("查询策略")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<FunctionalStrategyVO>> findStrategy(PageDTO pageDTO) {

        log.info("findStrategy {}", pageDTO.toString());

        PageVO<FunctionalStrategyVO> pageVO = functionalStrategyService.findStrategy(pageDTO);

        return ResponseVO.success(pageVO);
    }









}
