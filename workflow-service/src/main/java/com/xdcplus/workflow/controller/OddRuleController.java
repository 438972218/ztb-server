package com.xdcplus.workflow.controller;

import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.workflow.common.pojo.dto.OddRuleDTO;
import com.xdcplus.workflow.common.pojo.dto.OddRuleFilterDTO;
import com.xdcplus.workflow.common.pojo.vo.OddRuleVO;
import com.xdcplus.workflow.service.OddRuleService;
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

import javax.validation.Validation;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 单号规则 前端控制器
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
@Slf4j
@RestController
@Validated
@RequestMapping("/oddRule")
@Api(tags = "单号规则管理")
public class OddRuleController {

    @Autowired
    private OddRuleService oddRuleService;

    @ApiOperation("新增单号规则")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveOddRule(@RequestBody OddRuleDTO oddRuleDTO) {

        log.info("saveOddRule {}", oddRuleDTO.toString());

        oddRuleService.saveOddRule(oddRuleDTO);

        return ResponseVO.success();

    }

    @ApiOperation("修改单号规则")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateOddRule(@RequestBody OddRuleDTO oddRuleDTO) {

        log.info("updateOddRule {}", oddRuleDTO.toString());

        oddRuleService.updateOddRule(oddRuleDTO);

        return ResponseVO.success();

    }

    @ApiOperation("删除单号规则")
    @DeleteMapping(value = "/{ruleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "ruleId", dataType = "Long", value = "单号规则ID", required = true),
    })
    public ResponseVO deleteOddRule(@PathVariable("ruleId") @NotNull(message = "单号规则ID不能为空") Long ruleId) {

        log.info("deleteOddRule {}", ruleId);

        oddRuleService.deleteOddRule(ruleId);

        return ResponseVO.success();

    }

    @ApiOperation("查询单号规则")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<OddRuleVO>> findOddRule(OddRuleFilterDTO oddRuleFilterDTO) {

        log.info("findOddRule {}", oddRuleFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(oddRuleFilterDTO);

        return ResponseVO.success(oddRuleService.findOddRule(oddRuleFilterDTO));

    }

    @ApiOperation("查询单个单号规则")
    @GetMapping(value = "/{ruleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "ruleId", dataType = "Long", value = "规则ID", required = true),
    })
    public ResponseVO<OddRuleVO> findOddRuleById(@PathVariable("ruleId")
                                                     @NotNull(message = "规则ID 不能为空")
                                                             Long ruleId) {

        log.info("findOddRuleById {}", ruleId);

        return ResponseVO.success(oddRuleService.findOne(ruleId));

    }

    @ApiOperation("验证规则是否存在")
    @GetMapping(value = "/existRule/{nameOrPrefix}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "nameOrPrefix", dataType = "String", value = "规则名或者规则前缀", required = true),
    })
    public ResponseVO<Boolean> validationRuleExists(@PathVariable("nameOrPrefix")
                                                 @NotBlank(message = "规则名或者规则前缀 不能为空")
                                                         String nameOrPrefix) {

        log.info("validationRuleExists {}", nameOrPrefix);

        return ResponseVO.success(oddRuleService.validationRuleExists(nameOrPrefix));

    }
















}
