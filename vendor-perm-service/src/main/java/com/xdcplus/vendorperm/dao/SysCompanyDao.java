package com.xdcplus.vendorperm.dao;

import com.xdcplus.vendorperm.common.pojo.entity.SysCompany;
import com.xdcplus.vendorperm.common.pojo.query.syscompany.SysCompanyFilterQuery;
import com.xdcplus.vendorperm.common.pojo.vo.ldap.TableColumnVo;
import com.xdcplus.vendorperm.common.pojo.vo.syscompany.SysCompanyDepartmentVo;
import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分部表(公司表)(SysCompany)表数据库访问层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:07
 */
public interface SysCompanyDao extends IBaseMapper<SysCompany> {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysCompanyFilterQuery 实例对象
     * @return 对象列表
     */
    List<SysCompany> getSysCompanyByCondition(@Param("sysCompanyFilterQuery")SysCompanyFilterQuery sysCompanyFilterQuery);

    /**
     * 获取公司部门树
     *
     * @return {@link List<SysCompanyDepartmentVo>}
     */
    List<SysCompanyDepartmentVo> getSysCompanyDepartmentTree();

    /**
     * 根据parentCode更新parentId
     *
     * @return int
     */
    int updateParentIdByParentCode();


    /**
     * 获取表格列
     *
     * @param tableName 表名
     * @return {@link List<TableColumnVo>}
     */
    List<TableColumnVo> getTableColumns(@Param("tableName")String tableName);

    /**
     * 根据公司名称及id获取公司信息
     *
     * @param shortName 短名称
     * @param id        id
     * @return {@link SysCompany}
     */
    SysCompany getSysCompanyByShortNameAndNoId(@Param("shortName") String shortName, @Param("id") Long id);



    /**
     * 根据公司code及id获取公司信息
     *
     * @param shortName 短名称
     * @param id        id
     * @return {@link SysCompany}
     */
    SysCompany getSysCompanyByCodeAndNoId(@Param("code") String shortName, @Param("id") Long id);
}

