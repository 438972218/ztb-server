package com.xdcplus.permission.controller;

import com.github.pagehelper.PageInfo;
import com.xdcplus.permission.common.validator.groupvlidator.InsertGroupValidator;
import com.xdcplus.permission.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.permission.common.pojo.dto.sysjobtype.SysJobTypeDto;
import com.xdcplus.permission.common.pojo.dto.sysjobtype.SysJobTypeFilterDto;
import com.xdcplus.permission.common.pojo.entity.SysRole;
import com.xdcplus.permission.common.pojo.vo.sysjobtype.SysJobTypeVo;
import com.xdcplus.permission.service.SysJobTypeService;
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
 * 职务类别表(SysJobType)表控制层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:11
 */
@Slf4j
@Validated
@RestController
@RequestMapping("sysJobType")
@Api(value = "职务类别管理", tags = "职务类别管理")
public class SysJobTypeController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private SysJobTypeService sysJobTypeService;


    /**
     * 获取职务类别分页通过条件
     *
     * @param sysJobTypeFilterDto sys dto的工作类型
     * @return {@link ResponseVO<PageInfo<SysJobTypeVo>>}
     */
    @ApiOperation("获取职务类别分页数据")
    @PostMapping(value = "getSysJobTypePageByCondition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<SysJobTypeVo>> getSysJobTypePageByCondition(@RequestBody @Validated() SysJobTypeFilterDto sysJobTypeFilterDto){
        return ResponseVO.success(sysJobTypeService.getSysJobTypePageByCondition(sysJobTypeFilterDto));
    }

    @ApiOperation("获取职务类别列表数据")
    @GetMapping(value = "getSysJobTypeByCondition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<SysJobTypeVo>> getSysJobTypeByCondition(){
        return ResponseVO.success(sysJobTypeService.getSysJobTypeByCondition());
    }

    /**
     * 通过主键id查询
     *
     * @param id id
     * @return {@link SysRole}
     */
    @ApiOperation("根据主键查询")
    @GetMapping(value = "queryById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<SysJobTypeVo> queryById(@PathVariable("id")
                                      @NotNull(message = "ID不能为空")Long id) {
        return ResponseVO.success(this.sysJobTypeService.queryById(id));
    }


    /**
     * 插入
     *
     * @param sysJobTypeDto 职务类别dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("新增职务类别")
    @PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO insert(@Validated(InsertGroupValidator.class) @RequestBody SysJobTypeDto sysJobTypeDto){
        log.info("insert:{}", sysJobTypeDto.toString());
        sysJobTypeService.insert(sysJobTypeDto, AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }


    /**
     * 更新通过id
     *
     * @param sysJobTypeDto 职务类别dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("更新职务类别根据主键id")
    @PutMapping(value = "updateById", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO updateById(@RequestBody @Validated(UpdateGroupValidator.class)SysJobTypeDto sysJobTypeDto){
        log.info("updateById:{}", sysJobTypeDto.toString());
        sysJobTypeService.updateById(sysJobTypeDto,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }


    /**
     * 通过主键id删除
     *
     * @param id id
     * @return {@link ResponseVO}
     */
    @ApiOperation("删除职务类别根据主键id")
    @DeleteMapping(value = "deleteById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO deleteById(@PathVariable("id")
                          @NotNull(message = "ID不能为空") Long id){
        log.info("deleteById:{}", id);
        sysJobTypeService.deleteById(id,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }

}
