package com.xdcplus.vendorperm.dao;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.vendorperm.common.pojo.entity.SysEmployee;
import com.xdcplus.vendorperm.common.pojo.query.sysemployee.SysEmployeeFilterQuery;
import com.xdcplus.vendorperm.common.pojo.vo.sysemployee.SysEmployeeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工信息表(SysEmployee)表数据库访问层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:09
 */
public interface SysEmployeeDao extends IBaseMapper<SysEmployee> {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysEmployeeFilterQuery 实例对象
     * @return 对象列表
     */
    List<SysEmployee> getSysEmployeeByCondition(@Param("sysEmployeeFilterQuery") SysEmployeeFilterQuery sysEmployeeFilterQuery);

    /**
     * 更新主管员工id，根据主管员工的code的
     *
     * @return int
     */
    int updateManagerIdByEmployeeNo();

    /**
     * 根据departCode更新DepartId字段
     *
     * @return int
     */
    int updateDepartIdByDepartCode();

    SysEmployee getSysEmployeeByEmployeeNo(@Param("employeeNo")String employeeNo);


    SysEmployee getSysEmployeeByEmployeeNoAndNoId(@Param("employeeNo") String employeeNo, @Param("id") Long id);

    /**
     * 根据岗位id获取员工信息
     *
     * @param positionId 岗位id
     * @return {@link List<SysEmployeeVo>}
     */
    List<SysEmployee> getSysEmployeeListByPositionId( @Param("positionId") Long positionId);
    List<SysEmployee> getSysEmployeeListByDepartmentId( @Param("departmentId") Long departmentId);

    /**
     * 获取总经理员工信息
     *
     * @return {@link SysEmployee}
     */
    SysEmployee getGeneralManagerEmployee();


    List<SysEmployee> getEmployeeByCompanyId(@Param("companyId") Long companyId);
    List<SysEmployee> getEmployeeByDepartmentId(@Param("departmentId") Long departmentId);


}

