package com.xdcplus.permission.controller;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.xdcplus.permission.common.pojo.dto.sysdepartment.SysDepartmentDto;
import com.xdcplus.permission.common.pojo.dto.sysemployee.SysEmployeeDto;
import com.xdcplus.permission.common.pojo.dto.sysuser.GetSysUserByUserIdOrUserNameDto;
import com.xdcplus.permission.common.pojo.dto.sysuser.GetUserByUserIdOrUserNameDto;
import com.xdcplus.permission.common.pojo.dto.sysuser.RegisterUserDto;
import com.xdcplus.permission.common.pojo.entity.SysUser;
import com.xdcplus.permission.common.pojo.vo.ldap.TableColumnVo;
import com.xdcplus.permission.common.pojo.vo.syscompany.SysCompanyVo;
import com.xdcplus.permission.common.pojo.vo.sysdepartment.SysDepartmentVo;
import com.xdcplus.permission.common.pojo.vo.sysemployee.SysEmployeeVo;
import com.xdcplus.permission.common.pojo.vo.sysrole.SysRoleVo;
import com.xdcplus.permission.common.pojo.vo.sysuser.*;
import com.xdcplus.permission.service.*;
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

/**
 * api控制器
 *
 * @author Bullion.Yan
 * @date 2021/08/26
 */
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
    @Resource
    private SysCompanyService sysCompanyService;
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
    @ApiOperation("查询所有用户的用户信息，及用户及关联的部门及岗位信息")
    @GetMapping(value = "findAllUserAndDepartAndPostion", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<List<GetAllUserAndDepartAndPostionVO>> findAllUserAndDepartAndPostion(){
        return ResponseVO.success(sysUserService.findAllUserAndDepartAndPostion());
    }


    @ApiOperation("根据用户名查询员工信息")
    @PostMapping(value = "getEmployeeVoByUserName/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<SysEmployeeVo> getEmployeeVoByUserName(@PathVariable("userName")
                                                      @NotNull(message = "userName不能为空")String userName){
        return ResponseVO.success(sysEmployeeService.getEmployeeVoByUserName(userName));
    }

    /**
     * 根据用户名，查询部门经理的员工信息、用户信息
     *
     * @param userName 用户名
     * @return {@link ResponseVO<SysEmployeeVo>}
     */
    @ApiOperation("根据用户名，查询部门经理的员工信息和用户信息")
    @GetMapping(value = "getDepartmentManagerEmployeeVoAndSysUserVoByUserName/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
         ResponseVO<GetDepartmentManagerEmployeeVoAndSysUserVoByUserNameVO> getDepartmentManagerEmployeeVoAndSysUserVoByUserName(@PathVariable("userName")
                                                  @NotNull(message = "userName不能为空")String userName){
        return ResponseVO.success(sysUserService.getDepartmentManagerEmployeeVoAndSysUserVoByUserName(userName));
    }
    @ApiOperation("根据用户id查询角色")
    @GetMapping(value = "queryRoleByUserId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<SysRoleVo>> queryByUserId(@PathVariable("userId") @NotNull(message = "userId不能为空") Long userId) {
        return ResponseVO.success(this.sysRoleService.queryByUserId(userId));
    }
    /**
     * 查询分部表(公司)
     *
     * @param id 主键id
     * @return {@link SysCompanyVo} 分部表(公司表)Vo
     */
    @ApiOperation("根据主键查询公司")
    @GetMapping(value = "queryCompanyById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<SysCompanyVo> queryById(@PathVariable("id")
                                              @NotNull(message = "ID不能为空")Long id) {
        return ResponseVO.success(this.sysCompanyService.queryById(id));
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
     * 查询通过id
     *
     * @param id id
     * @return {@link SysUserVo} 用户信息vo
     */
    @ApiOperation("根据主键查询")
    @GetMapping(value = "queryUserById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<SysUserVo> queryUserById(@PathVariable("id")
                                           @NotNull(message = "ID不能为空")Long id) {
        return ResponseVO.success(this.sysUserService.queryById(id));
    }

}
