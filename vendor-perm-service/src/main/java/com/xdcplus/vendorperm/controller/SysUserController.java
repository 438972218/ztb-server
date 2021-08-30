package com.xdcplus.vendorperm.controller;

import com.github.pagehelper.PageInfo;
import com.xdcplus.vendorperm.common.pojo.dto.sysuser.RegisterUserDto;
import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.vendorperm.common.validator.groupvlidator.InsertGroupValidator;
import com.xdcplus.vendorperm.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.vendorperm.common.pojo.dto.sysuser.SysUserDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysuser.SysUserFilterDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysuser.UpdatePasswordByIdDto;
import com.xdcplus.vendorperm.common.pojo.vo.sysuser.SysUserVo;
import com.xdcplus.vendorperm.service.SysUserService;
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
 * 用户信息表(SysUser)表控制层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:10
 */
@Slf4j
@Validated
@RestController
@RequestMapping("sysUser")
@Api(value = "用户信息管理", tags = "用户信息管理")
public class SysUserController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;


    /**
     * 获取用户信息分页数据
     *
     * @param sysUserFilterDto 用户信息dto
     * @return {@link ResponseVO<PageInfo<SysUserVo>>}
     */
    @ApiOperation("获取用户信息分页数据")
    @PostMapping(value = "getSysUserServicePageByCondition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<SysUserVo>> getSysUserServicePageByCondition(@RequestBody @Validated() SysUserFilterDto sysUserFilterDto){
        return ResponseVO.success(sysUserService.getSysUserServicePageByCondition(sysUserFilterDto));
    }


    /**
     * 查询通过id
     *
     * @param id id
     * @return {@link SysUserVo} 用户信息vo
     */
    @ApiOperation("根据主键查询")
    @GetMapping(value = "queryById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<SysUserVo> queryById(@PathVariable("id")
                                   @NotNull(message = "ID不能为空")Long id) {
        return ResponseVO.success(this.sysUserService.queryById(id));
    }

    /**
     * 查询通过userName
     *
     * @param userName userName
     * @return {@link SysUserVo} 用户信息vo
     */
    @ApiOperation("根据主键查询")
    @GetMapping(value = "queryByUserName/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<SysUserVo> queryByUserName(@PathVariable("userName")
                                           @NotNull(message = "userName不能为空")String userName) {
        return ResponseVO.success(this.sysUserService.queryByUserName(userName));
    }

    /**
     * 插入
     *
     * @param sysUserDto 用户信息dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("新增用户信息")
    @PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO insert(@Validated(InsertGroupValidator.class) @RequestBody SysUserDto sysUserDto){
        log.info("insert:{}", sysUserDto.toString());
        sysUserService.insert(sysUserDto, AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }

    @ApiOperation("注册用户")
    @PostMapping(value = "registerUser", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO registerUser(@Validated @RequestBody RegisterUserDto registerUserDto){
        log.info("insert:{}", registerUserDto.toString());
        sysUserService.registerUser(registerUserDto);
        return ResponseVO.success();
    }


    /**
     * 更新通过id
     *
     * @param sysUserDto 用户信息dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("更新用户信息根据主键id")
    @PutMapping(value = "updateById", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO updateById(@RequestBody @Validated(UpdateGroupValidator.class)SysUserDto sysUserDto){
        log.info("updateById:{}", sysUserDto.toString());
        sysUserService.updateById(sysUserDto,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }

    @ApiOperation("更新密码")
    @PutMapping(value = "updatePasswordById", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO updatePasswordById( @Validated @RequestBody UpdatePasswordByIdDto updatePasswordByIdDto){
        log.info("updatePasswordById:{}", updatePasswordByIdDto.toString());
        sysUserService.updatePasswordById(updatePasswordByIdDto,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }

    @ApiOperation("忘记密码")
    @PutMapping(value = "forgetPassword", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO forgetPassword( @Validated @RequestBody UpdatePasswordByIdDto updatePasswordByIdDto){
        log.info("updatePasswordById:{}", updatePasswordByIdDto.toString());
        sysUserService.updatePasswordById(updatePasswordByIdDto,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }


    /**
     * 删除通过id
     *
     * @param id id
     * @return {@link ResponseVO}
     */
    @ApiOperation("删除用户信息根据主键id")
    @DeleteMapping(value = "deleteById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO deleteById(@PathVariable("id")
                          @NotNull(message = "ID不能为空") Long id){
        log.info("deleteById:{}", id);
        sysUserService.deleteById(id,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }

}
