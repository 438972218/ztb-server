package com.xdcplus.permission.controller;

import com.github.pagehelper.PageInfo;
import com.xdcplus.permission.common.validator.groupvlidator.InsertGroupValidator;
import com.xdcplus.permission.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.permission.common.pojo.dto.sysdictionary.SysDictionaryDto;
import com.xdcplus.permission.common.pojo.dto.sysdictionary.SysDictionaryFilterDto;
import com.xdcplus.permission.common.pojo.vo.sysdictionary.SysDictionaryVo;
import com.xdcplus.permission.service.SysDictionaryService;
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
 * 字典表(SysDictionary)表控制层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:12
 */
@Slf4j
@Validated
@RestController
@RequestMapping("sysDictionary")
@Api(value = "字典管理", tags = "字典管理")
public class SysDictionaryController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private SysDictionaryService sysDictionaryService;


    /**
     * 获取字典分页通过条件
     *
     * @param sysDictionaryFilterDto 系统字典dto
     * @return {@link ResponseVO<PageInfo<SysDictionaryVo>>}
     */
    @ApiOperation("获取字典分页数据")
    @PostMapping(value = "getSysDictionaryPageByCondition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<SysDictionaryVo>> getSysDictionaryPageByCondition(@RequestBody @Validated() SysDictionaryFilterDto sysDictionaryFilterDto){
        return ResponseVO.success(sysDictionaryService.getSysDictionaryPageByCondition(sysDictionaryFilterDto));
    }


    /**
     * 查询通过id
     *
     * @param id 主键id
     * @return {@link SysDictionaryVo}
     */
    @ApiOperation("根据主键查询")
    @GetMapping(value = "queryById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<SysDictionaryVo> queryById(@PathVariable("id")
                                         @NotNull(message = "ID不能为空")Long id) {
        return ResponseVO.success(this.sysDictionaryService.queryById(id));
    }


    /**
     * 插入字典
     *
     * @param sysDictionaryDto 字典dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("新增字典")
    @PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO insert(@Validated({InsertGroupValidator.class}) @RequestBody SysDictionaryDto sysDictionaryDto){
        log.info("insert:{}", sysDictionaryDto.toString());
        sysDictionaryService.insert(sysDictionaryDto, AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }


    /**
     * 更新通过id
     *
     * @param sysDictionaryDto 字典dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("更新字典根据主键id")
    @PutMapping(value = "updateById", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO updateById(@RequestBody @Validated(UpdateGroupValidator.class)SysDictionaryDto sysDictionaryDto){
        log.info("updateById:{}", sysDictionaryDto.toString());
        sysDictionaryService.updateById(sysDictionaryDto,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }

    /**
     * 通过主键id删除
     *
     * @param id id
     * @return {@link ResponseVO}
     */
    @ApiOperation("删除字典根据主键id")
    @DeleteMapping(value = "deleteById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO deleteById(@PathVariable("id")
                          @NotNull(message = "ID不能为空") Long id){
        log.info("deleteById:{}", id);
        sysDictionaryService.deleteById(id,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }


}
