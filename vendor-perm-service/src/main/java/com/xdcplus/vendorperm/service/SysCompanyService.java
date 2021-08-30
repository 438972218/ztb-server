package com.xdcplus.vendorperm.service;

import com.xdcplus.vendorperm.common.pojo.dto.syscompany.SysCompanyDto;
import com.xdcplus.vendorperm.common.pojo.dto.syscompany.SysCompanyFilterDto;
import com.xdcplus.vendorperm.common.pojo.vo.ldap.TableColumnVo;
import com.xdcplus.vendorperm.common.pojo.vo.syscompany.SysCompanyDepartmentVo;
import com.xdcplus.vendorperm.common.pojo.vo.syscompany.SysCompanyVo;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;

import java.util.List;

/**
 * 分部表(公司表)(SysCompany)表服务接口
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:07
 */
public interface SysCompanyService {

    /**
     * 获取分部(公司)分页通过条件
     *
     * @param sysCompanyFilterDto 分部(公司)dto
     * @return {@link ResponseVO <PageInfo<SysCompanyVo>>}
     */
    PageVO<SysCompanyVo> getSysCompanyPageByCondition(SysCompanyFilterDto sysCompanyFilterDto);

    /**
     * 判断公司是否是最底层的公司，只有最低层的公司，才允许添加部门
     *
     * @param id id
     * @return {@link Integer}
     */
    Integer judgeGroupCompany(Long id);


    /**
     * 查询通过id
     *
     * @param id id
     * @return {@link SysCompanyVo} 分部(公司)Vo
     */
    SysCompanyVo queryById(Long id);

    /**
     * 新增
     *
     * @param sysCompanyDto 分部(公司)dto
     */
    void insert(SysCompanyDto sysCompanyDto,String loginUser);

    /**
     * 批量新增
     *
     * @param sysCompanyDtoList 分部(公司)dto
     */
    void batchSyncInsert(List<SysCompanyDto> sysCompanyDtoList,String loginUser);

    List<TableColumnVo> getTableColumns(String tableName);

    /**
     * 修改数据
     *
     * @param sysCompanyDto 分部(公司)dto
     * @return 实例对象
     */
    void updateById(SysCompanyDto sysCompanyDto,String loginUser);


    /**
     * 通过主键id删除
     *
     * @param id id
     */
    void deleteById(Long id,String loginUser);

    /**
     * 获取公司tree
     *
     * @return {@link List<SysCompanyVo>}
     */
    List<SysCompanyVo> getSysCompanyTree();

    /**
     * 获取公司+部门合起的tree
     *
     * @return {@link List<SysCompanyDepartmentVo>}
     */
    List<SysCompanyDepartmentVo> getSysCompanyDepartmentTree(Boolean isDisabled);



}
