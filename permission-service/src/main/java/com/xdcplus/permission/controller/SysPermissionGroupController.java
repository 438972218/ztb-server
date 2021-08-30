package com.xdcplus.permission.controller;

import com.github.pagehelper.PageInfo;
import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.permission.common.validator.groupvlidator.InsertGroupValidator;
import com.xdcplus.permission.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.permission.common.pojo.dto.syspermissionGroup.SysPermissionGroupDto;
import com.xdcplus.permission.common.pojo.dto.syspermissionGroup.SysPermissionGroupFilterDto;
import com.xdcplus.permission.common.pojo.vo.syspermissionGroup.SysPermissionGroupVo;
import com.xdcplus.permission.service.SysPermissionGroupService;
import com.xdcplus.ztb.common.mp.utils.AuthUtils;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 权限组表(SysPermissionGroup)表控制层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:12
 */
@Slf4j
@Validated
@RestController
@RequestMapping("sysPermissionGroup")
@Api(value = "权限组管理", tags = "权限组管理")
public class SysPermissionGroupController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private SysPermissionGroupService sysPermissionGroupService;


    /**
     * 获取权限组分页通过条件
     *
     * @param sysPermissionGroupFilterDto 系统权限组dto
     * @return {@link ResponseVO<PageInfo<SysPermissionGroupVo>>}
     */
    @ApiOperation("获取权限组分页通过条件")
    @PostMapping(value = "getSysPermissionGroupPageByCondition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<SysPermissionGroupVo>> getSysPermissionGroupPageByCondition(@RequestBody @Validated() SysPermissionGroupFilterDto sysPermissionGroupFilterDto){
        return ResponseVO.success(sysPermissionGroupService.getSysPermissionGroupPageByCondition(sysPermissionGroupFilterDto));
    }
    @ApiOperation("获取权限组分页通过条件")
    @GetMapping(value = "getSysPermissionGroupByCondition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<SysPermissionGroupVo>> getSysPermissionGroupByCondition(){
        return ResponseVO.success(sysPermissionGroupService.getSysPermissionGroupByCondition());
    }


    /**
     * 根据id查询权限组信息
     * @param id id
     * @return {@link SysPermissionGroupVo} 权限组dto
     */
    @ApiOperation("根据主键查询")
    @GetMapping(value = "queryById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<SysPermissionGroupVo> queryById(@PathVariable("id")
                                              @NotNull(message = "ID不能为空")Long id) {
        return ResponseVO.success(this.sysPermissionGroupService.queryById(id));
    }


    /**
     * 添加
     * @param sysPermissionGroupDto 权限组dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("新增角色")
    @PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO insert(@Validated(InsertGroupValidator.class) @RequestBody SysPermissionGroupDto sysPermissionGroupDto){
        log.info("insert:{}", sysPermissionGroupDto.toString());
        sysPermissionGroupService.insert(sysPermissionGroupDto, AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }


    /**
     * 更新通过id
     * @param sysPermissionGroupDto 权限组dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("更新角色根据主键id")
    @PutMapping(value = "updateById", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO updateById(@RequestBody @Validated(UpdateGroupValidator.class)SysPermissionGroupDto sysPermissionGroupDto){
        log.info("updateById:{}", sysPermissionGroupDto.toString());
        sysPermissionGroupService.updateById(sysPermissionGroupDto,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }


    /**
     * 删除通过id
     *
     * @param id 主键id
     * @return {@link ResponseVO}
     */
    @ApiOperation("删除权限组根据主键id")
    @DeleteMapping(value = "deleteById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO deleteById(@PathVariable("id")
                          @NotNull(message = "ID不能为空") Long id){
        log.info("deleteById:{}", id);
        sysPermissionGroupService.deleteById(id,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }

    /**
     * 根据角色id获取权限组信息
     *
     * @param roleId 角色id
     * @return {@link ResponseVO<List<SysPermissionGroupVo>>}
     */
    @ApiOperation("根据角色id获取权限组信息")
    @GetMapping(value = "getPermissionGroupByRoleId/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<List<SysPermissionGroupVo>> getPermissionGroupByRoleId(@PathVariable("roleId")
                          @NotNull(message = "角色ID不能为空") Long roleId){
        return ResponseVO.success(sysPermissionGroupService.getPermissionGroupByRoleId(roleId));
    }

}
