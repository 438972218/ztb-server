package com.xdcplus.vendorperm.controller;

import com.github.pagehelper.PageInfo;
import com.xdcplus.vendorperm.common.validator.groupvlidator.InsertGroupValidator;
import com.xdcplus.vendorperm.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.vendorperm.common.pojo.dto.sysemployee.QueryByCompanyIdOrDepartIdPageDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysemployee.SysEmployeeDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysemployee.SysEmployeeFilterDto;
import com.xdcplus.vendorperm.common.pojo.vo.sysemployee.SysEmployeeVo;
import com.xdcplus.vendorperm.service.SysEmployeeService;
import com.xdcplus.ztb.common.mp.controller.AbstractController;
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

/**
 * 员工信息表(SysEmployee)表控制层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:09
 */
@Slf4j
@Validated
@RestController
@RequestMapping("sysEmployee")
@Api(value = "员工信息管理", tags = "员工信息管理")
public class SysEmployeeController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private SysEmployeeService sysEmployeeService;


    /**
     * 获取员工信息分页数据根据条件
     *
     * @param sysEmployeeFilterDto sys员工dto
     * @return {@link ResponseVO<PageInfo<SysEmployeeVo>>}
     */
    @ApiOperation("获取员工信息分页数据")
    @PostMapping(value = "getSysEmployeePageByCondition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<SysEmployeeVo>> getSysEmployeePageByCondition(@RequestBody @Validated() SysEmployeeFilterDto sysEmployeeFilterDto){
        log.info("getSysEmployeePageByCondition:{}", sysEmployeeFilterDto.toString());
        return ResponseVO.success(sysEmployeeService.getSysEmployeePageByCondition(sysEmployeeFilterDto));
    }


    /**
     * 查询通过id
     *
     * @param id id
     * @return {@link SysEmployeeVo}
     */
    @ApiOperation("根据主键查询")
    @GetMapping(value = "queryById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<SysEmployeeVo> queryById(@PathVariable("id")
                                       @NotNull(message = "ID不能为空")Long id) {
        return ResponseVO.success(this.sysEmployeeService.queryById(id));
    }

    /**
     * 根据公司或者部门查询员工信息
     * @return {@link SysEmployeeVo}
     */
    @ApiOperation("根据公司或者部门查询员工信息分页")
    @PostMapping(value = "queryByCompanyIdOrDepartIdPage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<SysEmployeeVo>> queryByCompanyIdOrDepartId(@RequestBody @Validated() QueryByCompanyIdOrDepartIdPageDto queryByCompanyIdOrDepartIdPageDto) {
        return ResponseVO.success(this.sysEmployeeService.queryByCompanyIdOrDepartId(queryByCompanyIdOrDepartIdPageDto));
    }

    /**
     * 插入
     *
     * @param sysEmployeeDto 新增员工信息dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("新增员工信息")
    @PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO insert(@Validated(InsertGroupValidator.class) @RequestBody SysEmployeeDto sysEmployeeDto){
        log.info("insert:{}", sysEmployeeDto.toString());
        sysEmployeeService.insert(sysEmployeeDto,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }

    /**
     * 更新员工信息根据主键id
     *
     * @param sysEmployeeDto sys员工dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("更新员工信息根据主键id")
    @PutMapping(value = "updateById", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO updateById(@RequestBody @Validated(UpdateGroupValidator.class)SysEmployeeDto sysEmployeeDto){
        log.info("updateById:{}", sysEmployeeDto.toString());
        sysEmployeeService.updateById(sysEmployeeDto, AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }


    /**
     * 通过主键id删除
     *
     * @param id id
     * @return {@link ResponseVO}
     */
    @ApiOperation("删除员工信息根据主键id")
    @DeleteMapping(value = "deleteById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO deleteById(@PathVariable("id")
                          @NotNull(message = "ID不能为空") Long id){
        log.info("deleteById:{}", id);
        sysEmployeeService.deleteById(id,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }
}
