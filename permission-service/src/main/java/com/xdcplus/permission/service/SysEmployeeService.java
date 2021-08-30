package com.xdcplus.permission.service;

import com.xdcplus.permission.common.pojo.dto.sysemployee.GetSysEmployeeByNoBindUser;
import com.xdcplus.permission.common.pojo.dto.sysemployee.QueryByCompanyIdOrDepartIdPageDto;
import com.xdcplus.permission.common.pojo.dto.sysemployee.SysEmployeeDto;
import com.xdcplus.permission.common.pojo.dto.sysemployee.SysEmployeeFilterDto;
import com.xdcplus.permission.common.pojo.vo.ldap.TableColumnVo;
import com.xdcplus.permission.common.pojo.vo.sysemployee.GetSysEmployeeByNoBindUserVO;
import com.xdcplus.permission.common.pojo.vo.sysemployee.SysEmployeeVo;
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
     * @return {@link PageVO<SysEmployeeVo>}
     */
    PageVO<SysEmployeeVo> getSysEmployeePageByCondition(SysEmployeeFilterDto sysEmployeeFilterDto);

    /**
     * 根据员工信息id查询 员工信息
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


    /**
     * 根据用户名，查询员工信息
     *
     * @param userName 用户名
     * @return {@link SysEmployeeVo}
     */
    SysEmployeeVo getEmployeeVoByUserName(String userName);

    /**
     * 查询未绑定用户的员工信息
     *
     * @return {@link SysEmployeeVo}
     */
    PageVO<GetSysEmployeeByNoBindUserVO> getSysEmployeeByBindUser(GetSysEmployeeByNoBindUser getSysEmployeeByNoBindUser);



}
