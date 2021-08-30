package com.xdcplus.workflow.controller;


import com.xdcplus.workflow.common.pojo.dto.RequestTypeDTO;
import com.xdcplus.workflow.common.pojo.dto.RequestTypeFilterDTO;
import com.xdcplus.workflow.common.pojo.vo.RequestTypeVO;
import com.xdcplus.workflow.service.RequestTypeService;
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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 表单类型前端控制器
 *
 * @author Rong.Jia
 * @date 2021-08-05
 */
@Slf4j
@Validated
@RestController
@Api(tags = "表单类型管理")
@RequestMapping("/requestType")
public class RequestTypeController {

    @Autowired
    private RequestTypeService requestTypeService;

    @ApiOperation("添加表单类型")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveRequestType(@RequestBody RequestTypeDTO requestTypeDTO) {

        log.info("saveRequestType {}", requestTypeDTO.toString());

        requestTypeService.saveRequestType(requestTypeDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改表单类型")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateRequestType(@RequestBody RequestTypeDTO requestTypeDTO) {

        log.info("updateRequestType {}", requestTypeDTO.toString());

        requestTypeService.updateRequestType(requestTypeDTO);

        return ResponseVO.success();
    }

    @ApiOperation("查询表单类型")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<RequestTypeVO>> findRequestTypes(RequestTypeFilterDTO requestTypeFilterDTO) {

        log.info("findRequestTypes {}", requestTypeFilterDTO.toString());

        PageVO<RequestTypeVO> pageVO = requestTypeService.findRequestType(requestTypeFilterDTO);

        return ResponseVO.success(pageVO);
    }

    @ApiOperation("删除表单类型")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "Long", value = "类型主键ID", required = true),
    })
    public ResponseVO deleteRequestType(@PathVariable("id")
                                        @NotNull(message = "类型主键ID不能为空")
                                                Long id) {

        log.info("deleteRequestType {}", id);

        requestTypeService.deleteRequestType(id);

        return ResponseVO.success();
    }

    @ApiOperation("验证表单类型是否存在")
    @GetMapping(value = "/existRequest/{typeName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "typeName", dataType = "String", value = "类型名", required = true),
    })
    public ResponseVO<Boolean> validationExists(@PathVariable("typeName")
                                                @NotBlank(message = "类型名 不能为空")
                                                        String typeName) {

        log.info("validationExists {}", typeName);

        return ResponseVO.success(requestTypeService.validationExists(typeName));

    }














}
