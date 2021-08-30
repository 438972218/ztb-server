package com.xdcplus.biz.controller;


import com.xdcplus.biz.service.ProjectMemberService;
import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.dto.ProjectSheetDTO;
import com.xdcplus.biz.common.pojo.dto.ProjectSheetFilterDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.biz.common.pojo.vo.ProjectSheetVO;
import com.xdcplus.biz.service.ProjectSheetService;
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
 * 项目(ProjectSheet)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-08-20 16:31:02
 */
@Api(tags = "项目(ProjectSheet)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("projectSheet")
public class ProjectSheetController extends AbstractController {

    @Autowired
    private ProjectSheetService projectSheetService;

    @Autowired
    private ProjectMemberService projectMemberService;

    @ApiOperation("新增项目")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveProjectSheet(@RequestBody ProjectSheetDTO projectSheetDTO) {

        log.info("saveProjectSheet {}", projectSheetDTO.toString());

        projectSheetDTO.setCreatedUser(getAccount());
        projectSheetService.saveProjectSheet(projectSheetDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改项目")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateProjectSheet(@RequestBody ProjectSheetDTO projectSheetDTO) {

        log.info("updateProjectSheet {}", projectSheetDTO.toString());

        projectSheetDTO.setUpdatedUser(getAccount());
        projectSheetService.updateProjectSheet(projectSheetDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除项目")
    @DeleteMapping(value = "/{projectSheetId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "projectSheetId", dataType = "Long", value = "项目ID", required = true),
    })
    public ResponseVO deleteProjectSheetLogical(@PathVariable("projectSheetId")
                                                @NotNull(message = "项目ID不能为空") Long projectSheetId) {

        log.info("deleteProjectSheetLogical {}", projectSheetId);

        projectSheetService.deleteProjectSheetLogical(projectSheetId);

        return ResponseVO.success();
    }

    @ApiOperation("查询项目")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<ProjectSheetVO>> findProjectSheet(ProjectSheetFilterDTO projectSheetFilterDTO) {

        log.info("findProjectSheet {}", projectSheetFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(projectSheetFilterDTO);

        return ResponseVO.success(projectSheetService.queryProjectSheet(projectSheetFilterDTO));
    }


    @ApiOperation("新增项目返回VO")
    @PostMapping(value = "/returnVO", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveProjectSheetReturnVO(@RequestBody ProjectSheetDTO projectSheetDTO) {

        log.info("saveProjectSheetReturnVO {}", projectSheetDTO.toString());

        projectSheetDTO.setCreatedUser(getAccount());
        ProjectSheetVO projectSheetVO = projectSheetService.saveProjectSheetReturnVO(projectSheetDTO);

        return ResponseVO.success(projectSheetVO);
    }

    @ApiOperation("show项目")
    @GetMapping(value = "show/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "Long", value = "id", required = true),
    })
    public ResponseVO showProjectSheetById(@PathVariable("id")
                                              @NotNull(message = "ID不能为空") Long id) {

        log.info("showProjectSheetById {}", id);

        ProjectSheetVO projectSheetVO = projectSheetService.showProjectSheet(id);

        return ResponseVO.success(projectSheetVO);
    }



    @ApiOperation("查询项目(用户)")
    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<ProjectSheetVO>> findProjectSheetWithUser(ProjectSheetFilterDTO projectSheetFilterDTO) {

        log.info("findProjectSheetWithUser {}", projectSheetFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(projectSheetFilterDTO);
        PageVO<ProjectSheetVO> projectSheetVOPageVO = projectSheetService.queryProjectSheetWithUser(projectSheetFilterDTO);

        return ResponseVO.success(projectSheetVOPageVO);
    }

    @ApiOperation("删除项目（判断是否存在关联关系）")
    @DeleteMapping(value = "delete/{projectSheetId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "projectSheetId", dataType = "Long", value = "项目ID", required = true),
    })
    public ResponseVO deleteProjectSheet(@PathVariable("projectSheetId")
                                                @NotNull(message = "项目ID不能为空") Long projectSheetId) {

        log.info("deleteProjectSheet {}", projectSheetId);

        projectSheetService.deleteProjectSheet(projectSheetId);

        return ResponseVO.success();
    }

}
