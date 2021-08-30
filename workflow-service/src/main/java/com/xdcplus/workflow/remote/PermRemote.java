package com.xdcplus.workflow.remote;

import com.xdcplus.workflow.remote.fallback.PermRemoteFallbackFactory;
import com.xdcplus.ztb.common.remote.domain.perm.dto.SysDepartmentDTO;
import com.xdcplus.ztb.common.remote.domain.perm.dto.SysEmployeeDTO;
import com.xdcplus.ztb.common.remote.domain.perm.dto.SysUserInfoDTO;
import com.xdcplus.ztb.common.remote.domain.perm.vo.SysDepartmentVO;
import com.xdcplus.ztb.common.remote.domain.perm.vo.SysUserInfoVO;
import com.xdcplus.ztb.common.tool.constants.ServiceConstant;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.ztb.common.tool.validator.ValidList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 权限收放调用
 *
 * @author Rong.Jia
 * @date 2021/08/03 16:21:01
 */
@Component
@RequestMapping("/api")
@FeignClient(value = ServiceConstant.PERM_SERVICE, fallbackFactory = PermRemoteFallbackFactory.class)
public interface PermRemote {

    /**
     * 获取总经理用户信息
     *
     * @return
     */
    @GetMapping(value = "getGeneralManagerSysUser", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<SysUserInfoVO> getGeneralManagerSysUser();

    /**
     * 获取用户的上级用户员工信息的
     *
     * @param sysUserInfoDTO
     * @return
     */
    @PostMapping(value = "getSysUserMangerByUserIdOrUserName", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<SysUserInfoVO> getSysUserMangerByUserIdOrUserName(@RequestBody SysUserInfoDTO sysUserInfoDTO);

    /**
     * 获取部门列表数据
     *
     * @return
     */
    @GetMapping(value = "getSysDepartmentList", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<List<SysDepartmentVO>> getSysDepartmentList();

    /**
     * 根据部门ID获取部门负责人的用户信息
     *
     * @param departmentId
     * @return
     */
    @GetMapping(value = "getSysUserManagerByDepartmentId/{departmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<SysUserInfoVO> getSysUserManagerByDepartmentId(@PathVariable("departmentId") Long departmentId);

    /**
     * 根据用户账号获取用户信息
     *
     * @param userName
     * @return
     */
    @GetMapping(value = "queryByUserName/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<SysUserInfoVO> queryByUserName(@PathVariable("userName") String userName);

    /**
     * 插入部门
     * ldap同步使用的
     *
     * @param sysDepartmentDtoList 部门dto
     * @return {@link ResponseVO}
     */
    @PostMapping(value = "batchSyncInsertDepartment", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO batchSyncInsert(@RequestBody @Valid ValidList<SysDepartmentDTO> sysDepartmentDtoList);

    /**
     * 批量新增员工信息
     *
     * @param sysEmployeeDtoList sys员工dto列表
     * @return {@link ResponseVO}
     */
    @PostMapping(value = "batchSyncInsertSysEmployee", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO batchSyncInsertSysEmployee(@RequestBody @Valid ValidList<SysEmployeeDTO> sysEmployeeDtoList);


}
