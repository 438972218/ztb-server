package com.xdcplus.workflow.controller;


import com.xdcplus.workflow.common.pojo.dto.ProcessConfigFilterDTO;
import com.xdcplus.workflow.common.pojo.dto.ProcessConfigInfoFilterDTO;
import com.xdcplus.workflow.common.pojo.dto.ProcessConfigTableDTO;
import com.xdcplus.workflow.common.pojo.vo.ProcessConfigInfoVO;
import com.xdcplus.workflow.common.pojo.dto.ProcessConfigTreeDTO;
import com.xdcplus.workflow.common.pojo.vo.ProcessConfigVO;
import com.xdcplus.workflow.service.ProcessConfigControlService;
import com.xdcplus.workflow.service.ProcessConfigService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validation;
import java.util.List;

/**
 * 流程配置表 前端控制器
 *
 * @author Rong.Jia
 * @date 2021-05-31
 */
@RestController
@Validated
@Slf4j
@Api(tags = "流程配置模块管理")
@RequestMapping("/processConfig")
public class ProcessConfigController {

    @Autowired
    private ProcessConfigService processConfigService;

    @Autowired
    private ProcessConfigControlService processConfigControlService;

    @ApiOperation("添加流程配置(Tree配置模式)")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveProcessConfig(@RequestBody @Validated ProcessConfigTreeDTO processConfigTreeDTO) {

        log.info("saveProcessConfig {}", processConfigTreeDTO.toString());

        processConfigControlService.saveProcessConfig(processConfigTreeDTO);

        return ResponseVO.success();
    }

    @ApiOperation("查询流程配置信息(Tree配置模式)")
    @GetMapping(value = "/chart", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<ProcessConfigInfoVO>> findProcessConfig(ProcessConfigInfoFilterDTO processConfigInfoFilterDTO) {

        log.info("findProcessConfig  {} ", processConfigInfoFilterDTO.toString());

        Validation.buildDefaultValidatorFactory().getValidator().validate(processConfigInfoFilterDTO);

        return ResponseVO.success(processConfigControlService.findProcessConfig(processConfigInfoFilterDTO.getProcessId(),
                processConfigInfoFilterDTO.getVersion()));
    }

    @ApiOperation("查询流程配置信息")
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<ProcessConfigVO>> findProcessConfigFilter(ProcessConfigFilterDTO processConfigFilterDTO) {

        log.info("findProcessConfig {}", processConfigFilterDTO.toString());

        return ResponseVO.success(processConfigService.findProcessConfig(processConfigFilterDTO));

    }

    @ApiOperation("查询流程配置信息(Table配置模式)")
    @GetMapping(value = "/tables", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<ProcessConfigVO>> findProcessConfigTable(ProcessConfigInfoFilterDTO processConfigInfoFilterDTO) {

        log.info("findProcessConfigTable  {} ", processConfigInfoFilterDTO.toString());

        Validation.buildDefaultValidatorFactory().getValidator().validate(processConfigInfoFilterDTO);

        return ResponseVO.success(processConfigControlService.findProcessConfigTable(processConfigInfoFilterDTO.getProcessId(),
                processConfigInfoFilterDTO.getVersion()));
    }

    @ApiOperation("添加流程配置(Table配置模式)")
    @PostMapping(value = "/table", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveProcessConfigTable(@RequestBody @Validated List<ProcessConfigTableDTO> processConfigTables) {

        log.info("saveProcessConfigTable {}", processConfigTables.toString());

        processConfigControlService.saveProcessConfig(processConfigTables);

        return ResponseVO.success();
    }







}
