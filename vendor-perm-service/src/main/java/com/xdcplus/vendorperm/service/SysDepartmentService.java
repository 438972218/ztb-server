package com.xdcplus.vendorperm.service;

import com.xdcplus.vendorperm.common.pojo.dto.sysdepartment.SysDepartmentDto;
import com.xdcplus.vendorperm.common.pojo.dto.sysdepartment.SysDepartmentFilterDto;
import com.xdcplus.vendorperm.common.pojo.vo.ldap.TableColumnVo;
import com.xdcplus.vendorperm.common.pojo.vo.sysdepartment.SysDepartmentVo;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;

import java.util.List;

/**
 * 部门表(SysDepartment)表服务接口
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:10
 */
public interface SysDepartmentService {

    /**
     * 获取部门分页通过条件
     *
     * @param sysDepartmentFilterDto 部门
     * @return {@link <PageInfo<SysDepartmentVo>>}
     */
    PageVO<SysDepartmentVo> getSysDepartmentPageByCondition(SysDepartmentFilterDto sysDepartmentFilterDto);
    List<SysDepartmentVo> getSysDepartmentByCondition();

    /**
     * 查询通过id
     *
     * @param id id
     * @return {@link SysDepartmentDto} 部门dto
     */
    SysDepartmentVo queryById(Long id);

    /**
     * 获取部门表格列
     *
     * @return {@link List<TableColumnVo>}
     */
    List<TableColumnVo> getDepartmentTableColumns();
    /**
     * 插入
     *
     * @param sysDepartmentDto 部门dto
     */
    void insert(SysDepartmentDto sysDepartmentDto,String loginUser);

    /**
     * 批量插入
     *
     * @param sysDepartmentDtoList 部门dto
     */
    void batchSyncInsert(List<SysDepartmentDto> sysDepartmentDtoList,String loginUser);


    /**
     * 更新通过id
     *
     * @param sysDepartmentDto 部门dto
     */
    void updateById(SysDepartmentDto sysDepartmentDto,String loginUser);


    /**
     * 删除通过id
     *
     * @param id id
     */
    void deleteById(Long id,String loginUser);


    /**
     * 根据所属公司查询部门列表
     *
     * @param companyId 公司标识
     * @return {@link List<SysDepartmentVo>}
     */
    List<SysDepartmentVo> getByCompanyId(Long companyId);

    /**
     * 获取部门树
     *
     */
    List<SysDepartmentVo> getDepartmentTree();
}
