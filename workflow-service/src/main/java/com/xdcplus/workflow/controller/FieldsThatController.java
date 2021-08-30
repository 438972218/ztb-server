package com.xdcplus.workflow.controller;

import com.xdcplus.workflow.common.pojo.dto.FieldsThatDTO;
import com.xdcplus.workflow.common.pojo.vo.FieldsThatVO;
import com.xdcplus.workflow.service.FieldsThatService;
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
import java.util.List;

/**
 * 对接字段说明 前端控制器
 *
 * @author Rong.Jia
 * @date 2021-06-24
 */
@Validated
@Slf4j
@RestController
@Api(value = "对接字段说明管理", tags = "对接字段说明管理")
@RequestMapping("/fieldsThat")
public class FieldsThatController {

    @Autowired
    private FieldsThatService fieldsThatService;

    @ApiOperation("同步字段对应说明")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO syncFieldsThat(@RequestBody FieldsThatDTO fieldsThatDTO) {

        log.info("saveFieldsThat {}", fieldsThatDTO.toString());

        fieldsThatService.syncFieldsThat(fieldsThatDTO);

        return ResponseVO.success();

    }

    @ApiOperation("删除字段对应说明")
    @DeleteMapping(value = "/{thatId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "thatId", dataType = "integer", value = "字段说明ID", required = true),
    })
    public ResponseVO deleteFieldsThat(@PathVariable("thatId") @NotNull(message = "字段说明ID不能为空") Long thatId) {

        log.info("deleteFieldsThat {}", thatId);

        fieldsThatService.deleteFieldsThat(thatId);

        return ResponseVO.success();

    }

    @ApiOperation("查询字段说明")
    @GetMapping(value = "/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "type", dataType = "integer", value = "类型(详见数据字典)", required = true),
    })
    public ResponseVO<List<FieldsThatVO>> findFieldsThat(@PathVariable("type")
                                                         @NotNull(message = "类型不能为空") Byte type) {

        log.info("findFieldsThat {}", type);

        return ResponseVO.success(fieldsThatService.findFieldsThats(type));

    }


}
