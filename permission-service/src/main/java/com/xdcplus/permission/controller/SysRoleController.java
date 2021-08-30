package com.xdcplus.permission.controller;

import com.github.pagehelper.PageInfo;
import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.permission.common.validator.groupvlidator.InsertGroupValidator;
import com.xdcplus.permission.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.permission.common.pojo.dto.sysrole.SysRoleDto;
import com.xdcplus.permission.common.pojo.dto.sysrole.SysRoleFilterDto;
import com.xdcplus.permission.common.pojo.vo.sysrole.SysRoleVo;
import com.xdcplus.permission.service.SysRoleService;
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
 * 角色表(SysRole)表控制层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:08:43
 */

@Slf4j
@Validated
@RestController
@RequestMapping("sysRole")
@Api(value = "角色管理", tags = "角色管理")
public class SysRoleController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleService sysRoleService;

    /**
     * 获取sys角色分页通过条件
     *
     * @param sysRoleFilterDto 系统角色dto
     * @return {@link ResponseVO<PageInfo<SysRoleVo>>}
     */
    @ApiOperation("获取系统角色分页数据")
    @PostMapping(value = "getSysRolePageByCondition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<SysRoleVo>> getSysRolePageByCondition(@RequestBody @Validated() SysRoleFilterDto sysRoleFilterDto){
        return ResponseVO.success(sysRoleService.getSysRolePageByCondition(sysRoleFilterDto));
    }
    @ApiOperation("获取系统角色列表数据")
    @PostMapping(value = "getSysRoleListByCondition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<SysRoleVo>> getSysRoleListByCondition(@RequestBody @Validated() SysRoleFilterDto sysRoleFilterDto){
        return ResponseVO.success(sysRoleService.getSysRoleListByCondition(sysRoleFilterDto));
    }
    @ApiOperation("获取系统角色列表数据")
    @GetMapping(value = "getSysRoleList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<SysRoleVo>> getSysRoleList(){
        return ResponseVO.success(sysRoleService.getSysRoleList());
    }

    /**
     * 通过主键id查询
     *
     * @param id id
     * @return {@link SysRoleVo}
     */
    @ApiOperation("根据主键查询")
    @GetMapping(value = "queryById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<SysRoleVo> queryById(@PathVariable("id") @NotNull(message = "id不能为空") Long id) {
        return ResponseVO.success(this.sysRoleService.queryById(id));
    }

    @ApiOperation("根据用户id查询角色")
    @GetMapping(value = "queryByUserId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<SysRoleVo>> queryByUserId(@PathVariable("userId") @NotNull(message = "userId不能为空") Long userId) {
        return ResponseVO.success(this.sysRoleService.queryByUserId(userId));
    }

    /**
     * 新增角色
     *
     * @param sysRoleDto 角色dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("新增角色")
    @PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO insert(@Validated(InsertGroupValidator.class) @RequestBody SysRoleDto sysRoleDto){
        log.info("insert:{}", sysRoleDto.toString());
        sysRoleService.insert(sysRoleDto, AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }


    /**
     * 更新通过id
     *
     * @param sysRoleDto 系统角色dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("更新角色根据主键id")
    @PutMapping(value = "updateById", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO updateById(@RequestBody @Validated(UpdateGroupValidator.class)SysRoleDto sysRoleDto){
        log.info("updateById:{}", sysRoleDto.toString());
        sysRoleService.updateById(sysRoleDto,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }


    /**
     * 通过主键id删除
     *
     * @param id id
     * @return {@link ResponseVO}
     */
    @ApiOperation("删除角色根据主键id")
    @DeleteMapping(value = "deleteById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO deleteById(@PathVariable("id")
                          @NotNull(message = "角色ID不能为空") Long id){
        log.info("deleteById:{}", id);
        sysRoleService.deleteById(id,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }
}
