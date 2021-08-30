package com.xdcplus.vendorperm.controller;

import com.github.pagehelper.PageInfo;
import com.xdcplus.vendorperm.common.validator.groupvlidator.InsertGroupValidator;
import com.xdcplus.vendorperm.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.vendorperm.common.pojo.dto.sysjob.SysJobDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysjob.SysJobFilterDto;
import com.xdcplus.vendorperm.common.pojo.vo.sysjob.SysJobVo;
import com.xdcplus.vendorperm.service.SysJobService;
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
 * 职务表(SysJob)表控制层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:04
 */
@Slf4j
@Validated
@RestController
@RequestMapping("sysJob")
@Api(value = "职务管理", tags = "职务管理")
public class SysJobController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private SysJobService sysJobService;


    /**
     * 获取职务分页通过条件
     *
     * @param sysJobFilterDto sys dto工作
     * @return {@link ResponseVO<PageInfo<SysJobVo>>}
     */
    @ApiOperation("获取职务分页数据")
    @PostMapping(value = "getSysJobPageByCondition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<SysJobVo>> getSysJobPageByCondition(@RequestBody @Validated() SysJobFilterDto sysJobFilterDto){
        return ResponseVO.success(sysJobService.getSysJobPageByCondition(sysJobFilterDto));
    }

    @ApiOperation("获取职务列表数据")
    @GetMapping(value = "getSysJobByCondition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<SysJobVo>> getSysJobByCondition(@RequestBody @Validated() SysJobFilterDto sysJobFilterDto){
        return ResponseVO.success(sysJobService.getSysJobByCondition(sysJobFilterDto));
    }


    /**
     * 查询通过id
     *
     * @param id id
     * @return {@link SysJobVo}
     */
    @ApiOperation("根据主键查询")
    @GetMapping(value = "queryById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<SysJobVo> queryById(@PathVariable("id")
                                  @NotNull(message = "ID不能为空")Long id) {
        return ResponseVO.success(this.sysJobService.queryById(id));
    }

    /**
     * 插入
     *
     * @param sysJobDto 职务dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("新增职务")
    @PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO insert(@Validated(InsertGroupValidator.class) @RequestBody SysJobDto sysJobDto){
        log.info("insert:{}", sysJobDto.toString());
        sysJobService.insert(sysJobDto, AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }


    /**
     * 更新通过id
     *
     * @param sysJobDto 职务dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("更新职务根据主键id")
    @PutMapping(value = "updateById", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO updateById(@RequestBody @Validated(UpdateGroupValidator.class)SysJobDto sysJobDto){
        log.info("updateById:{}", sysJobDto.toString());
        sysJobService.updateById(sysJobDto,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }


    /**
     * 删除职务通过id
     *
     * @param id 主键id
     * @return {@link ResponseVO}
     */
    @ApiOperation("删除职务根据主键id")
    @DeleteMapping(value = "deleteById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO deleteById(@PathVariable("id")
                          @NotNull(message = "ID不能为空")Long id){
        log.info("deleteById:{}",id);
        sysJobService.deleteById(id,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }

}
