package com.xdcplus.biz.controller;


import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.dto.ProjectAttachmentDTO;
import com.xdcplus.biz.common.pojo.dto.ProjectAttachmentFilterDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.biz.common.pojo.vo.ProjectAttachmentVO;
import com.xdcplus.biz.service.ProjectAttachmentService;
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


/**
 * 项目附件(ProjectAttachment)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-08-20 16:27:42
 */
@Api(tags = "项目附件(ProjectAttachment)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("projectAttachment")
public class ProjectAttachmentController extends AbstractController {

    @Autowired
    private ProjectAttachmentService projectAttachmentService;

    @ApiOperation("新增项目附件")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveProjectAttachment(@RequestBody ProjectAttachmentDTO projectAttachmentDTO) {

        log.info("saveProjectAttachment {}", projectAttachmentDTO.toString());

        projectAttachmentDTO.setCreatedUser(getAccount());
        projectAttachmentService.saveProjectAttachment(projectAttachmentDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改项目附件")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateProjectAttachment(@RequestBody ProjectAttachmentDTO projectAttachmentDTO) {

        log.info("updateProjectAttachment {}", projectAttachmentDTO.toString());

        projectAttachmentDTO.setUpdatedUser(getAccount());
        projectAttachmentService.updateProjectAttachment(projectAttachmentDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除项目附件")
    @DeleteMapping(value = "/{projectAttachmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "projectAttachmentId", dataType = "Long", value = "项目附件ID", required = true),
    })
    public ResponseVO deleteProjectAttachmentLogical(@PathVariable("projectAttachmentId")
                                                     @NotNull(message = "项目附件ID不能为空") Long projectAttachmentId) {

        log.info("deleteProjectAttachmentLogical {}", projectAttachmentId);

        projectAttachmentService.deleteProjectAttachmentLogical(projectAttachmentId);

        return ResponseVO.success();
    }

    @ApiOperation("查询项目附件")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<ProjectAttachmentVO>> findProjectAttachment(ProjectAttachmentFilterDTO projectAttachmentFilterDTO) {

        log.info("findProjectAttachment {}", projectAttachmentFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(projectAttachmentFilterDTO);

        return ResponseVO.success(projectAttachmentService.queryProjectAttachment(projectAttachmentFilterDTO));
    }


}
