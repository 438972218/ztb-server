package com.xdcplus.vendorperm.controller;

import com.github.pagehelper.PageInfo;
import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.vendorperm.common.validator.groupvlidator.InsertGroupValidator;
import com.xdcplus.vendorperm.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.vendorperm.common.pojo.dto.sysPermission.SysPermissionDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysPermission.SysPermissionFilterDto;
import com.xdcplus.vendorperm.common.pojo.vo.sysPermission.SysPermissionVo;
import com.xdcplus.vendorperm.service.SysPermissionService;
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
 * 权限信息表(SysPermission)表控制层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:13
 */
@Slf4j
@Validated
@RestController
@RequestMapping("sysPermission")
@Api(value = "权限信息管理", tags = "权限信息管理")
public class SysPermissionController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private SysPermissionService sysPermissionService;


    /**
     * 获取权限信息分页通过条件
     *
     * @param sysPermissionFilterDto 权限信息dto
     * @return {@link ResponseVO<PageInfo<SysPermissionVo>>}
     */
    @ApiOperation("获取系统角色分页数据")
    @PostMapping(value = "getSysPermissionPageByCondition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<SysPermissionVo>> getSysPermissionPageByCondition(@RequestBody @Validated() SysPermissionFilterDto sysPermissionFilterDto){
       return ResponseVO.success(sysPermissionService.getSysPermissionPageByCondition(sysPermissionFilterDto));
    }


    /**
     * 查询通过id
     *
     * @param id id
     * @return {@link SysPermissionVo}
     */
    @ApiOperation("根据主键查询")
    @GetMapping(value = "queryById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<SysPermissionVo> queryById(@PathVariable("id")
                                         @NotNull(message = "ID不能为空")Long id) {
        return ResponseVO.success(this.sysPermissionService.queryById(id));
    }


    /**
     * 插入
     *
     * @param sysPermissionDto 权限信息dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("新增权限信息")
    @PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO insert(@Validated(InsertGroupValidator.class) @RequestBody SysPermissionDto sysPermissionDto){
        log.info("insert:{}", sysPermissionDto.toString());
        sysPermissionService.insert(sysPermissionDto, AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }


    /**
     * 更新通过id
     *
     * @param sysPermissionDto 权限信息dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("更新权限信息根据主键id")
    @PutMapping(value = "updateById", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO updateById(@RequestBody @Validated(UpdateGroupValidator.class)SysPermissionDto sysPermissionDto){
        log.info("updateById:{}", sysPermissionDto.toString());
        sysPermissionService.updateById(sysPermissionDto,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }


    /**
     * 通过主键id删除
     *
     * @param id id
     * @return {@link ResponseVO}
     */
    @ApiOperation("删除权限信息根据主键id")
    @DeleteMapping(value = "deleteById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO deleteById(@PathVariable("id")
                          @NotNull(message = "ID不能为空") Long id){
        log.info("deleteById:{}", id);
        sysPermissionService.deleteById(id,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }

    /**
     * 获取权限信息根据权限组id
     *
     * @param permissionGroupId 权限组id
     * @return {@link ResponseVO<List<SysPermissionVo>>}
     */
    @ApiOperation("获取权限信息根据权限组id")
    @GetMapping(value = "getPermissionByPermissionGroupId/{permissionGroupId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<SysPermissionVo>> getPermissionByPermissionGroupId(@PathVariable("permissionGroupId")
                                                                              @NotNull(message = "权限组id不能为空")Long permissionGroupId){
        return ResponseVO.success(sysPermissionService.getPermissionByPermissionGroupId(permissionGroupId));
    }

    /**
     * 获取权限树
     *
     * @return {@link ResponseVO<List<SysPermissionVo>>}
     */
    @ApiOperation("获取tree")
    @GetMapping(value = "getSysPermissionTree", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<List<SysPermissionVo>> getSysPermissionTree(){
        return ResponseVO.success(this.sysPermissionService.getSysPermissionTree());
    }


}
