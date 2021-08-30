package com.xdcplus.vendorperm.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.xdcplus.vendorperm.common.validator.groupvlidator.InsertGroupValidator;
import com.xdcplus.vendorperm.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.ztb.common.tool.validator.ValidList;
import com.xdcplus.vendorperm.common.pojo.dto.syscompany.SysCompanyDto;
import com.xdcplus.vendorperm.common.pojo.dto.syscompany.SysCompanyFilterDto;
import com.xdcplus.vendorperm.common.pojo.vo.syscompany.SysCompanyDepartmentVo;
import com.xdcplus.vendorperm.common.pojo.vo.syscompany.SysCompanyVo;
import com.xdcplus.vendorperm.service.SysCompanyService;
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
 * 分部表(公司表)(SysCompany)表控制层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:07
 */
@Slf4j
@Validated
@RestController
@RequestMapping("sysCompany")
@Api(value = "公司/分部管理", tags = "公司/分部管理")
public class SysCompanyController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private SysCompanyService sysCompanyService;

    /**
     * 获取分部表(公司表)分页通过条件
     *
     * @param sysCompanyFilterDto sys公司dto
     * @return {@link ResponseVO<PageInfo<SysCompanyVo>>}
     */
    @ApiOperation("获取分部表(公司表)分页数据")
    @PostMapping(value = "getSysCompanyPageByCondition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<SysCompanyVo>> getSysCompanyPageByCondition(@RequestBody @Validated() SysCompanyFilterDto sysCompanyFilterDto){
        return ResponseVO.success(sysCompanyService.getSysCompanyPageByCondition(sysCompanyFilterDto));
    }


    /**
     * 查询分部表(公司)
     *
     * @param id 主键id
     * @return {@link SysCompanyVo} 分部表(公司表)Vo
     */
    @ApiOperation("根据主键查询")
    @GetMapping(value = "queryById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<SysCompanyVo> queryById(@PathVariable("id")
                                      @NotNull(message = "ID不能为空")Long id) {
        return ResponseVO.success(this.sysCompanyService.queryById(id));
    }


    /**
     * 新增分部表(公司表)
     * @param sysCompanyDto 分部表(公司表)Vo
     * @return {@link ResponseVO}
     */
    @ApiOperation("新增分部表(公司表)")
    @PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO insert(@Validated(InsertGroupValidator.class) @RequestBody SysCompanyDto sysCompanyDto){
        log.info("insert:{}",sysCompanyDto.toString());
        this.sysCompanyService.insert(sysCompanyDto, AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }

    @ApiOperation("批量新增分部表(公司表)")
    @PostMapping(value = "batchSyncInsert", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO batchSyncInsert(@RequestBody ValidList<SysCompanyDto> sysCompanyDtoList){
        log.info("batchSyncInsert:{}", JSON.toJSONString(sysCompanyDtoList));
        this.sysCompanyService.batchSyncInsert(sysCompanyDtoList,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }

    /**
     * 更新分部表(公司表)根据主键id
     *
     * @param sysCompanyDto 分部表(公司表)Dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("更新分部表(公司表)根据主键id")
    @PutMapping(value = "updateById", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO updateById(@RequestBody @Validated(UpdateGroupValidator.class)SysCompanyDto sysCompanyDto){
        log.info("updateById:{}",sysCompanyDto.toString());
        sysCompanyService.updateById(sysCompanyDto,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }

    /**
     * 通过主键id删除
     *
     * @param id id
     * @return {@link ResponseVO}
     */
    @ApiOperation("删除分部表(公司表)根据主键id")
    @DeleteMapping(value = "deleteById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO deleteById(@PathVariable("id")
                          @NotNull(message = "ID不能为空")Long id){
        log.info("deleteById:{}",id);
        sysCompanyService.deleteById(id,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }

    /**
     * 判断公司是否是最底层的公司，只有最低层的公司，才允许添加部门
     * @param id id
     * @return {@link ResponseVO}
     */
    @ApiOperation("判断公司是否是最底层的公司，只有最低层的公司，才允许添加部门")
    @GetMapping(value = "judgeGroupCompany/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO judgeGroupCompany(@PathVariable("id")
                          @NotNull(message = "ID不能为空")Long id){
        return ResponseVO.success(sysCompanyService.judgeGroupCompany(id));
    }

    /**
     * 获取公司tree
     *
     * @return {@link ResponseVO<List<SysCompanyVo>>}
     */
    @ApiOperation("获取公司tree")
    @GetMapping(value = "getSysCompanyTree", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<List<SysCompanyVo>> getDepartmentTree(){
        return ResponseVO.success(this.sysCompanyService.getSysCompanyTree());
    }

    /**
     * 获取公司+部门合起的tree
     *
     * @return {@link ResponseVO<List<SysCompanyDepartmentVo>>}
     */
    @ApiOperation("获取公司+部门合起的tree")
    @GetMapping(value = "getSysCompanyDepartmentTree", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<List<SysCompanyDepartmentVo>> getSysCompanyDepartmentTree(){
        return ResponseVO.success(this.sysCompanyService.getSysCompanyDepartmentTree(false));
    }
    /**
     * 获取公司+部门合起的tree,带有diableb的
     *
     * @return {@link ResponseVO<List<SysCompanyDepartmentVo>>}
     */
    @ApiOperation("获取公司+部门合起的tree")
    @GetMapping(value = "getSysCompanyDepartmentTreeDisaled", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<List<SysCompanyDepartmentVo>> getSysCompanyDepartmentTreeDisaled(){
        return ResponseVO.success(this.sysCompanyService.getSysCompanyDepartmentTree(true));
    }

}
