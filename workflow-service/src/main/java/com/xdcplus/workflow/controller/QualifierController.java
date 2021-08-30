package com.xdcplus.workflow.controller;

import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.workflow.common.pojo.dto.QualifierDTO;
import com.xdcplus.workflow.common.pojo.dto.QualifierFilterDTO;
import com.xdcplus.workflow.common.pojo.vo.QualifierVO;
import com.xdcplus.workflow.service.QualifierService;
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
 * 流程规则 前端控制器
 *
 * @author Rong.Jia
 * @date  2021-05-31
 */
@RestController
@Validated
@Slf4j
@Api(tags = "流程规则模块管理")
@RequestMapping("/qualifier")
public class QualifierController {

    @Autowired
    private QualifierService qualifierService;

    @ApiOperation("新增流程规则")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveQualifier(@RequestBody QualifierDTO qualifierDTO) {

        log.info("saveQualifier {}", qualifierDTO.toString());

        qualifierService.saveQualifier(qualifierDTO);

        return ResponseVO.success();

    }

    @ApiOperation("修改流程规则")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateQualifier(@RequestBody QualifierDTO qualifierDTO) {

        log.info("updateQualifier {}", qualifierDTO.toString());

        qualifierService.updateQualifier(qualifierDTO);

        return ResponseVO.success();

    }

    @ApiOperation("删除流程规则")
    @DeleteMapping(value = "/{qualifierId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "qualifierId", dataType = "Long", value = "流程规则ID", required = true),
    })
    public ResponseVO deleteQualifier(@PathVariable("qualifierId")
                                         @NotNull(message = "流程规则ID不能为空") Long qualifierId) {

        log.info("deleteQualifier {}", qualifierId);

        qualifierService.deleteQualifier(qualifierId);

        return ResponseVO.success();

    }

    @ApiOperation("查询流程规则")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<QualifierVO>> findQualifier(QualifierFilterDTO qualifierFilterDTO) {

        log.info("findQualifier {}", qualifierFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(qualifierFilterDTO);

        return ResponseVO.success(qualifierService.findQualifier(qualifierFilterDTO));

    }

    @ApiOperation("查询单个流程规则")
    @GetMapping(value = "/{qualifierId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "qualifierId", dataType = "Long", value = "流程规则ID", required = true),
    })
    public ResponseVO<QualifierVO> findQualifierById(@PathVariable("qualifierId")
                                                             @NotNull(message = "流程规则ID 不能为空")
                                                                     Long qualifierId) {

        log.info("findQualifierById {}", qualifierId);

        return ResponseVO.success(qualifierService.findOne(qualifierId));

    }

    @ApiOperation("验证流程规则信息是否存在")
    @GetMapping(value = "/existQualifier/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "name", dataType = "String", value = "流程规则信息名称", required = true),
    })
    public ResponseVO<Boolean> validationExists(@PathVariable("name")
                                                @NotBlank(message = "流程规则信息名称 不能为空")
                                                        String name) {

        log.info("validationExists {}", name);

        return ResponseVO.success(qualifierService.validationExists(name));

    }




















}
