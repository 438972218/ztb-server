package com.xdcplus.vendorperm.service;

import com.xdcplus.vendorperm.common.pojo.dto.sysemployee.QueryByCompanyIdOrDepartIdPageDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysemployee.SysEmployeeDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysemployee.SysEmployeeFilterDto;
import com.xdcplus.vendorperm.common.pojo.vo.ldap.TableColumnVo;
import com.xdcplus.vendorperm.common.pojo.vo.sysemployee.SysEmployeeVo;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;

import java.util.List;

/**
 * 员工信息(SysEmployee)表服务接口
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:09
 */
public interface SysEmployeeService {


    /**
     * 获取员工信息分页通过条件
     *
     * @param sysEmployeeFilterDto 员工信息dto
     */
    PageVO<SysEmployeeVo> getSysEmployeePageByCondition(SysEmployeeFilterDto sysEmployeeFilterDto);

    /**
     * 查询通过id
     *
     * @param id id
     * @return {@link SysEmployeeVo} 员工信息Vo
     */
    SysEmployeeVo queryById(Long id);

    PageVO<SysEmployeeVo> queryByCompanyIdOrDepartId(QueryByCompanyIdOrDepartIdPageDto queryByCompanyIdOrDepartIdPageDto);

    /**
     * 插入
     *
     * @param sysEmployeeDto 员工信息dto
     */
    void insert(SysEmployeeDto sysEmployeeDto,String loginUser);
    /**
     * 批量插入
     *
     * @param sysEmployeeDtoList 员工信息dto
     */
    void batchSyncInsert(List<SysEmployeeDto> sysEmployeeDtoList,String loginUser);

    List<TableColumnVo> getEmployeeTableColumns();


    /**
     * 更新通过id
     *
     * @param sysEmployeeDto 员工信息dto
     */
    void updateById(SysEmployeeDto sysEmployeeDto,String loginUser);


    /**
     * 通过主键id删除
     *
     * @param id id
     */
    void deleteById(Long id,String loginUser);

    /**
     * 根据岗位id获取员工信息
     *
     * @param positionId 岗位id
     * @return {@link List<SysEmployeeVo>}
     */
    List<SysEmployeeVo> getSysEmployeeListByPosition(Long positionId);

    /**
     * 获取部门id获取员工列表
     *
     * @param departmentId 部门id
     * @return {@link List<SysEmployeeVo>}
     */
    List<SysEmployeeVo> getSysEmployeeListByDepartmentId(Long departmentId);

    /**
     * 获取总经理用户信息
     *
     * @return {@link List<SysEmployeeVo>}
     */
    SysEmployeeVo getGeneralManagerEmployee();




}
