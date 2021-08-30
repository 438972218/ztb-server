package com.xdcplus.permission.controller;

import com.github.pagehelper.PageInfo;
import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.permission.common.validator.groupvlidator.InsertGroupValidator;
import com.xdcplus.permission.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.permission.common.pojo.dto.sysregion.SysRegionDto;
import com.xdcplus.permission.common.pojo.dto.sysregion.SysRegionFilterDto;
import com.xdcplus.permission.common.pojo.entity.SysRole;
import com.xdcplus.permission.common.pojo.vo.sysregion.SysRegionVo;
import com.xdcplus.permission.service.SysRegionService;
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
 * 行政区域表(SysRegion)表控制层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:06
 */
@Slf4j
@Validated
@RestController
@RequestMapping("sysRegion")
@Api(value = "行政区域管理", tags = "行政区域管理")
public class SysRegionController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private SysRegionService sysRegionService;


    /**
     * 获取行政区域分页通过条件
     *
     * @param sysRegionFilterDto sys地区dto
     * @return {@link ResponseVO<PageInfo<SysRegionVo>>}
     */
    @ApiOperation("获取行政区域分页数据")
    @PostMapping(value = "getSysRegionPageByCondition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<SysRegionVo>> getSysRegionPageByCondition(@RequestBody @Validated() SysRegionFilterDto sysRegionFilterDto){
        return ResponseVO.success(sysRegionService.getSysRegionPageByCondition(sysRegionFilterDto));
    }

    /**
     * 获取行政区域列表数据
     *
     * @param sysRegionFilterDto sys地区过滤器dto
     * @return {@link ResponseVO<List<SysRegionVo>>}
     */
    @ApiOperation("获取行政区域列表数据")
    @PostMapping(value = "getSysRegionByCondition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<SysRegionVo>> getSysRegionByCondition(@RequestBody @Validated() SysRegionFilterDto sysRegionFilterDto){
        return ResponseVO.success(sysRegionService.getSysRegionByCondition(sysRegionFilterDto));
    }

    /**
     * 通过主键id查询
     *
     * @param id id
     * @return {@link SysRole}
     */
    @ApiOperation("根据主键查询")
    @GetMapping(value = "queryById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<SysRegionVo> queryById(@PathVariable("id")
                                     @NotNull(message = "ID不能为空")Long id) {
        return ResponseVO.success(this.sysRegionService.queryById(id));
    }


    /**
     * 插入
     *
     * @param sysRegionDto 行政区域dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("新增行政区域")
    @PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO insert(@Validated(InsertGroupValidator.class) @RequestBody SysRegionDto sysRegionDto){
        log.info("insert:{}", sysRegionDto.toString());
        sysRegionService.insert(sysRegionDto, AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }


    /**
     * 更新通过id
     *
     * @param sysRegionDto 行政区域dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("更新行政区域根据主键id")
    @PutMapping(value = "updateById", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO updateById(@RequestBody @Validated(UpdateGroupValidator.class)SysRegionDto sysRegionDto){
        log.info("updateById:{}", sysRegionDto.toString());
        sysRegionService.updateById(sysRegionDto,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }


    /**
     * 通过主键id删除
     *
     * @param id 主键id
     * @return {@link ResponseVO}
     */
    @ApiOperation("删除角色根据主键id")
    @DeleteMapping(value = "deleteById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO deleteById(@PathVariable("id")
                          @NotNull(message = "ID不能为空") Long id){
        log.info("deleteById:{}", id);
        sysRegionService.deleteById(id,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }

    /**
     * 获取行政区域tree
     *
     * @return {@link ResponseVO<List<SysRegionVo>>}
     */
    @ApiOperation("获取行政区域tree")
    @GetMapping(value = "getRegionTree", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<List<SysRegionVo>> getRegionTree(){
        return ResponseVO.success(this.sysRegionService.getRegionTree());
    }

}
