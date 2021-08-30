package com.xdcplus.vendorperm.dao;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.vendorperm.common.pojo.entity.SysDepartment;
import com.xdcplus.vendorperm.common.pojo.query.sysdepartment.SysDepartmentFilterQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门表(SysDepartment)表数据库访问层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:10
 */
public interface SysDepartmentDao extends IBaseMapper<SysDepartment>{


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysDepartmentFilterQuery 实例对象
     * @return 对象列表
     */
    List<SysDepartment> getSysDepartmentPageByCondition(@Param("sysDepartmentFilterQuery") SysDepartmentFilterQuery sysDepartmentFilterQuery);

    /**
     * 通过父代码更新父id
     *
     * @return int
     */
    int updateParentIdByParentCode();

    /**
     * 更新公司id基于公司代码
     *
     * @return int
     */
    int updateCompanyIdByCompanyCode();

    /**
     * 获取部门通过代码
     *
     * @param code 代码
     * @return {@link SysDepartment}
     */
    SysDepartment getDepartmentByCode(@Param("code") String code);

    /**
     * 获取sys部门通过代码不包括id
     *
     * @param code 代码
     * @param id   id
     * @return {@link SysDepartment}
     */
    SysDepartment getSysDepartmentByCodeAndNoId(@Param("code") String code, @Param("id") Long id);

    /**
     *
     *根据部门的所属公司，获取部门列表
     * @param companyId 公司
     * @return {@link List<SysDepartment>}
     */
    List<SysDepartment> getByCompanyId(@Param("companyId") Long companyId);
}

