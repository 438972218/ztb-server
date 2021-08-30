package com.xdcplus.permission.service;

import com.xdcplus.permission.common.pojo.dto.sysposition.GetSysPositionByCompanyOrDepartFilterDto;
import com.xdcplus.permission.common.pojo.dto.sysposition.SysPositionDto;
import com.xdcplus.permission.common.pojo.dto.sysposition.SysPositionFilterDto;
import com.xdcplus.permission.common.pojo.vo.sysposition.SysPositionVo;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;

import java.util.List;

/**
 * 岗位表(SysPosition)表服务接口
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:08
 */
public interface SysPositionService {


    /**
     * 获取岗位分页通过条件
     *
     * @param sysPositionFilterDto 岗位Dto
     * @return {@link <PageInfo<SysPositionVo>>}
     */
    PageVO<SysPositionVo> getSysPositionPageByCondition(SysPositionFilterDto sysPositionFilterDto);

    /**
     * 根据公司及部门查询所属的所有岗位
     *
     * @param getSysPositionByCompanyOrDepartFilterDto 根据公司及部门查询所属的所有岗位dto
     * @return {@link List<SysPositionVo>}
     */
    List<SysPositionVo> getSysPositionByCompanyOrDepart(GetSysPositionByCompanyOrDepartFilterDto getSysPositionByCompanyOrDepartFilterDto);

    /**
     * 根据条件查询岗位列表
     *
     * @return {@link List<SysPositionVo>}
     */
    List<SysPositionVo> getSysPositionByCondition();
    /**
     * 查询通过id
     *
     * @param id id
     * @return {@link SysPositionVo}
     */
    SysPositionVo queryById(Long id);


    /**
     * 插入
     *
     * @param sysPositionDto 岗位Dto
     */
    void insert(SysPositionDto sysPositionDto,String loginUser);


    /**
     * 更新通过id
     *
     * @param sysPositionDto 岗位Dto
     */
    void updateById(SysPositionDto sysPositionDto,String loginUser);


    /**
     * 通过主键id删除
     *
     * @param id id
     */
    void deleteById(Long id,String loginUser);


    /**
     * 根据职位id查询岗位
     *
     * @param jobId 工作id
     * @return {@link SysPositionVo}
     */
    List<SysPositionVo> getByJobId(Long jobId);


}
