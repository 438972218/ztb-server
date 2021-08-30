package com.xdcplus.biz.controller;


import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.biz.common.pojo.dto.ProjectMemberDTO;
import com.xdcplus.biz.common.pojo.dto.ProjectMemberFilterDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.biz.common.pojo.vo.ProjectMemberVO;
import com.xdcplus.biz.service.ProjectMemberService;
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
 * 项目成员信息(ProjectMember)表服务控制层
 *
 * @author Fish.Fei
 * @since 2021-08-24 09:40:42
 */
@Api(tags = "项目成员信息(ProjectMember)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("projectMember")
public class ProjectMemberController extends AbstractController {

    @Autowired
    private ProjectMemberService projectMemberService;

    @ApiOperation("新增项目成员信息")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveProjectMember(@RequestBody ProjectMemberDTO projectMemberDTO) {

        log.info("saveProjectMember {}", projectMemberDTO.toString());

        projectMemberDTO.setCreatedUser(getAccount());
        projectMemberService.saveProjectMember(projectMemberDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改项目成员信息")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateProjectMember(@RequestBody ProjectMemberDTO projectMemberDTO) {

        log.info("updateProjectMember {}", projectMemberDTO.toString());

        projectMemberDTO.setUpdatedUser(getAccount());
        projectMemberService.updateProjectMember(projectMemberDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除项目成员信息")
    @DeleteMapping(value = "/{projectMemberId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "projectMemberId", dataType = "Long", value = "项目成员信息ID", required = true),
    })
    public ResponseVO deleteProjectMemberLogical(@PathVariable("projectMemberId")
                                                 @NotNull(message = "项目成员信息ID不能为空") Long projectMemberId) {

        log.info("deleteProjectMemberLogical {}", projectMemberId);

        projectMemberService.deleteProjectMemberLogical(projectMemberId);

        return ResponseVO.success();
    }

    @ApiOperation("查询项目成员信息")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<ProjectMemberVO>> findProjectMember(ProjectMemberFilterDTO projectMemberFilterDTO) {

        log.info("findProjectMember {}", projectMemberFilterDTO);

        Validation.buildDefaultValidatorFactory().getValidator().validate(projectMemberFilterDTO);

        return ResponseVO.success(projectMemberService.queryProjectMember(projectMemberFilterDTO));
    }


}
