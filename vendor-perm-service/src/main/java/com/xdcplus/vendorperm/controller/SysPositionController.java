package com.xdcplus.vendorperm.controller;

import com.github.pagehelper.PageInfo;
import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.vendorperm.common.validator.groupvlidator.InsertGroupValidator;
import com.xdcplus.vendorperm.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.vendorperm.common.pojo.dto.sysposition.SysPositionDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysposition.SysPositionFilterDto;
import com.xdcplus.vendorperm.common.pojo.vo.sysposition.SysPositionVo;
import com.xdcplus.vendorperm.service.SysPositionService;
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
 * 岗位表(SysPosition)表控制层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:08
 */
@Slf4j
@Validated
@RestController
@RequestMapping("sysPosition")
@Api(value = "岗位管理", tags = "岗位管理")
public class SysPositionController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private SysPositionService sysPositionService;


    /**
     * 获取岗位分页通过条件
     *
     * @param sysPositionFilterDto 岗位 dto
     * @return {@link ResponseVO<PageInfo<SysPositionVo>>}
     */
    @ApiOperation("获取岗位分页数据")
    @PostMapping(value = "getSysPositionPageByCondition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<SysPositionVo>> getSysPositionPageByCondition(@RequestBody @Validated() SysPositionFilterDto sysPositionFilterDto){
        return ResponseVO.success(sysPositionService.getSysPositionPageByCondition(sysPositionFilterDto));
    }

    /**
     * 获取岗位列表数据
     *
     * @return {@link ResponseVO<List<SysPositionVo>>}
     */
    @ApiOperation("获取岗位列表数据")
    @GetMapping(value = "getSysPositionByCondition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<SysPositionVo>> getSysPositionByCondition(){
        return ResponseVO.success(sysPositionService.getSysPositionByCondition());
    }

//    @ApiOperation("根据公司及部门查询所属的所有岗位。")
//    @PostMapping(value = "getSysPositionByCompanyOrDepart", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseVO<List<SysPositionVo>> getSysPositionByCompanyOrDepart(GetSysPositionByCompanyOrDepartFilterDto getSysPositionByCompanyOrDepartFilterDto){
//        return ResponseVO.success(sysPositionService.getSysPositionByCompanyOrDepart(getSysPositionByCompanyOrDepartFilterDto));
//    }

    /**
     * 查询通过id
     *
     * @param id 主键id
     * @return {@link SysPositionVo}
     */
    @ApiOperation("根据主键查询")
    @GetMapping(value = "queryById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<SysPositionVo> queryById(@PathVariable("id")
                                       @NotNull(message = "ID不能为空") Long id) {
        return ResponseVO.success(this.sysPositionService.queryById(id));
    }


    /**
     * 插入
     *
     * @param sysPositionDto 岗位dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("新增岗位")
    @PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO insert(@Validated(InsertGroupValidator.class) @RequestBody SysPositionDto sysPositionDto){
        log.info("insert:{}", sysPositionDto.toString());
        sysPositionService.insert(sysPositionDto, AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }

    /**
     * 更新通过id
     *
     * @param sysPositionDto 岗位dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("更新岗位根据主键id")
    @PutMapping(value = "updateById", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO updateById(@RequestBody @Validated(UpdateGroupValidator.class)SysPositionDto sysPositionDto){
        log.info("updateById:{}", sysPositionDto.toString());
        sysPositionService.updateById(sysPositionDto,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }


    /**
     * 通过主键id删除
     *
     * @param id id
     * @return {@link ResponseVO}
     */
    @ApiOperation("删除岗位根据主键id")
    @DeleteMapping(value = "deleteById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO deleteById(@PathVariable("id")
                          @NotNull(message = "ID不能为空") Long id){
        log.info("deleteById:{}", id);
        sysPositionService.deleteById(id,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }
}
