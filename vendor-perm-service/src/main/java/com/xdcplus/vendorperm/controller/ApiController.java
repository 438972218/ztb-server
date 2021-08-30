package com.xdcplus.vendorperm.controller;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.xdcplus.vendorperm.common.pojo.dto.sysdepartment.SysDepartmentDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysemployee.SysEmployeeDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysuser.GetSysUserByUserIdOrUserNameDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysuser.GetUserByUserIdOrUserNameDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysuser.RegisterUserDto;
import com.xdcplus.vendorperm.common.pojo.vo.ldap.TableColumnVo;
import com.xdcplus.vendorperm.common.pojo.vo.sysdepartment.SysDepartmentVo;
import com.xdcplus.vendorperm.common.pojo.vo.sysuser.GetSysUserByUserIdOrUserNameVo;
import com.xdcplus.vendorperm.common.pojo.vo.sysuser.SysUserVo;
import com.xdcplus.vendorperm.common.pojo.vo.sysuser.UserPermVO;
import com.xdcplus.vendorperm.service.SysDepartmentService;
import com.xdcplus.vendorperm.service.SysEmployeeService;
import com.xdcplus.vendorperm.service.SysRoleService;
import com.xdcplus.vendorperm.service.SysUserService;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.mp.utils.AuthUtils;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.ztb.common.tool.validator.ValidList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("api")
@Api(value = "对外接口", tags = "对外接口")
public class ApiController extends AbstractController {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysRoleService sysRoleService;
    /**
     * 服务对象
     */
    @Resource
    private SysDepartmentService sysDepartmentService;
    /**
     * 服务对象
     */
    @Resource
    private SysEmployeeService sysEmployeeService;
    /**
     * 获取用户通过用户id或用户的名字
     * 使用：权限验证框架调用使用的，
     * 使用的业务场景：目的是登录的时候，把获取到，放到redis里面
     * @return {@link UserPermVO}
     */
    @ApiOperation("获取用户通过用户id或用户名，权限验证框架调用使用的，目的是登录的时候，把获取到，放到redis里面")
    @PostMapping(value = "getUserPermByUserIdOrUserName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<UserPermVO> getUserPermByUserIdOrUserName(@RequestBody GetUserByUserIdOrUserNameDto getUserByUserIdOrUserNameDto){
        if(getUserByUserIdOrUserNameDto== null || (ObjectUtil.isEmpty(getUserByUserIdOrUserNameDto.getId()) && ObjectUtil.isEmpty(getUserByUserIdOrUserNameDto.getUserName()))){
            throw new ZtbWebException(ResponseEnum.PARAMETER_ERROR);
        }
        return ResponseVO.success(sysUserService.getUserByUserIdOrUserName(getUserByUserIdOrUserNameDto.getId(), getUserByUserIdOrUserNameDto.getUserName()));
    }
    @ApiOperation("获取总经理用户信息")
    @GetMapping(value = "getGeneralManagerSysUser", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<SysUserVo> getGeneralManagerSysUser(){
        return ResponseVO.success(this.sysUserService.getGeneralManagerSysUser());
    }

    /**
     * ldap同步使用的
     *
     * @param sysEmployeeDtoList sys员工dto列表
     * @return {@link ResponseVO}
     */
    @ApiOperation("批量新增员工信息")
    @PostMapping(value = "batchSyncInsertSysEmployee", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO batchSyncInsertSysEmployee(@RequestBody @Valid ValidList<SysEmployeeDto> sysEmployeeDtoList){
        log.info("sysEmployeeDtoList:{}"+ JSON.toJSONString(sysEmployeeDtoList));
        sysEmployeeService.batchSyncInsert(sysEmployeeDtoList, AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }

    @ApiOperation("获取员工表的字段信息")
    @GetMapping(value = "getEmployeeTableColumns", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<List<TableColumnVo>> getEmployeeTableColumns(){
        return ResponseVO.success(this.sysEmployeeService.getEmployeeTableColumns());
    }
    /**
     * 插入部门
     *ldap同步使用的
     * @param sysDepartmentDtoList 部门dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("批量新增部门")
    @PostMapping(value = "batchSyncInsertDepartment", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO batchSyncInsert(@RequestBody  @Valid ValidList<SysDepartmentDto> sysDepartmentDtoList){
        log.info("batchSyncInsert:{}",JSON.toJSONString(sysDepartmentDtoList));
        sysDepartmentService.batchSyncInsert(sysDepartmentDtoList,AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }
    /**
     * 获取用户的上级用户员工信息的
     *
     * @param getSysUserByUserIdOrUserNameDto 获取用户的上级用户员工信息的
     * @return {@link GetSysUserByUserIdOrUserNameVo}
     */
    @ApiOperation("获取用户的上级用户员工信息的")
    @PostMapping(value = "getSysUserMangerByUserIdOrUserName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<SysUserVo> getSysUserMangerByUserIdOrUserName(@RequestBody GetSysUserByUserIdOrUserNameDto getSysUserByUserIdOrUserNameDto) {
        return ResponseVO.success(this.sysUserService.getSysUserByUserIdOrUserName(getSysUserByUserIdOrUserNameDto));
    }
    @ApiOperation("获取部门列表数据")
    @GetMapping(value = "getSysDepartmentList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<SysDepartmentVo>> getSysDepartmentList(){
        return ResponseVO.success(sysDepartmentService.getSysDepartmentByCondition());
    }
    @ApiOperation("根据用户账号获取用户信息")
    @GetMapping(value = "queryByUserName/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<SysUserVo> queryByUserName(@PathVariable("userName")
                                                 @NotNull(message = "userName不能为空")String userName) {
        return ResponseVO.success(this.sysUserService.queryByUserName(userName));
    }

    @ApiOperation("根据部门ID获取部门负责人的用户信息")
    @GetMapping(value = "getSysUserManagerByDepartmentId/{departmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<SysUserVo> getSysUserManagerByDepartmentId(@PathVariable("departmentId")
                                                 @NotNull(message = "部门ID不能为空")Long departmentId) {
        return ResponseVO.success(this.sysUserService.getSysUserManagerByDepartmentId(departmentId));
    }

    /**
     * 根据用户id获取角色标识
     *
     * @param userName 用户名
     * @return {@link String} 角色信息vo
     */
    @ApiOperation("根据主键查询")
    @GetMapping(value = "getSysRoleMarkByUserName/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<String> getSysRoleMarkByUserName(@PathVariable("userName")
                                                      @NotNull(message = "userName不能为空")String userName) {
        return ResponseVO.success(this.sysRoleService.getSysRoleMarkByUserId(userName));
    }

    @ApiOperation("注册用户")
    @PostMapping(value = "registerUser", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<SysUserVo> registerUser(@Validated @RequestBody RegisterUserDto registerUserDto){
        log.info("insert:{}", registerUserDto.toString());
        return ResponseVO.success(sysUserService.registerUser(registerUserDto));
    }
}
