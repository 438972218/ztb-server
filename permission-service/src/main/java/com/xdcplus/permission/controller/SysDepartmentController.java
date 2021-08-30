package com.xdcplus.permission.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.xdcplus.permission.common.validator.groupvlidator.InsertGroupValidator;
import com.xdcplus.permission.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.permission.common.pojo.dto.sysdepartment.SysDepartmentDto;
import com.xdcplus.permission.common.pojo.dto.sysdepartment.SysDepartmentFilterDto;
import com.xdcplus.permission.common.pojo.vo.ldap.TableColumnVo;
import com.xdcplus.permission.common.pojo.vo.sysdepartment.SysDepartmentVo;
import com.xdcplus.permission.service.SysDepartmentService;
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
import java.util.List;

/**
 * 部门表(SysDepartment)表控制层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:10
 */
@Slf4j
@Validated
@RestController
@RequestMapping("sysDepartment")
@Api(value = "部门管理", tags = "部门管理")
public class SysDepartmentController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private SysDepartmentService sysDepartmentService;


    /**
     * 获取部门分页通过条件
     *
     * @param sysDepartmentFilterDto 部门dto
     * @return {@link ResponseVO<PageInfo<SysDepartmentVo>>}
     */
    @ApiOperation("获取部门分页数据")
    @PostMapping(value = "getSysDepartmentPageByCondition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<SysDepartmentVo>> getSysDepartmentPageByCondition(@RequestBody @Validated()  SysDepartmentFilterDto sysDepartmentFilterDto){
        log.info("getSysDepartmentPageByCondition:{}",sysDepartmentFilterDto.toString());
       return ResponseVO.success(sysDepartmentService.getSysDepartmentPageByCondition(sysDepartmentFilterDto));
    }

    @ApiOperation("获取部门列表数据")
    @GetMapping(value = "getSysDepartmentByCondition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<SysDepartmentVo>> getSysDepartmentByCondition(){
        return ResponseVO.success(sysDepartmentService.getSysDepartmentByCondition());
    }


    /**
     * 查询通过id
     *
     * @param id id
     * @return {@link SysDepartmentVo}
     */
    @ApiOperation("根据主键查询部门")
    @GetMapping(value = "queryById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<SysDepartmentVo> queryById(@PathVariable("id")
                                         @NotNull(message = "ID不能为空")Long id) {
        log.info("queryById:{}",id);
        return ResponseVO.success(this.sysDepartmentService.queryById(id));
    }

    @ApiOperation("获取部门表的字段信息")
    @GetMapping(value = "getDepartmentTableColumns", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<List<TableColumnVo>> getDepartmentTableColumns(){
        return ResponseVO.success(this.sysDepartmentService.getDepartmentTableColumns());
    }
    /**
     * 插入部门
     *
     * @param sysDepartmentDto 部门dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("新增部门")
    @PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO insert(@Validated(InsertGroupValidator.class) @RequestBody SysDepartmentDto sysDepartmentDto){
        log.info("insert:{}",sysDepartmentDto.toString());
        this.sysDepartmentService.insert(sysDepartmentDto, AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }

    /**
     * 更新通过id
     *
     * @param sysDepartmentDto 部门dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("更新部门根据主键id")
    @PutMapping(value = "updateById", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO updateById(@RequestBody @Validated(UpdateGroupValidator.class)SysDepartmentDto sysDepartmentDto){

        log.info("updateById:{}",JSON.toJSONString(sysDepartmentDto));
        this.sysDepartmentService.updateById(sysDepartmentDto,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }


    /**
     * 通过主键id删除
     *
     * @param id id
     * @return {@link ResponseVO}
     */
    @ApiOperation("删除部门根据主键id")
    @DeleteMapping(value = "deleteById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO deleteById(@PathVariable("id")
                          @NotNull(message = "ID不能为空") Long id){
        this.sysDepartmentService.deleteById(id,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }


    /**
     * 获取部门tree
     *
     * @return {@link ResponseVO<List<SysDepartmentVo>>}
     */
    @ApiOperation("获取部门tree")
    @GetMapping(value = "getDepartmentTree", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<List<SysDepartmentVo>> getDepartmentTree(){
        return ResponseVO.success(this.sysDepartmentService.getDepartmentTree());
    }
}
