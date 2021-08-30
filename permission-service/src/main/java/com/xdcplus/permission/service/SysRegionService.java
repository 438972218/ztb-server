package com.xdcplus.permission.service;

import com.xdcplus.permission.common.pojo.dto.sysregion.SysRegionDto;
import com.xdcplus.permission.common.pojo.dto.sysregion.SysRegionFilterDto;
import com.xdcplus.permission.common.pojo.entity.SysRole;
import com.xdcplus.permission.common.pojo.vo.sysregion.SysRegionVo;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;

import java.util.List;

/**
 * 行政区域(SysRegion)表服务接口
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:06
 */
public interface SysRegionService {

    /**
     * 获取行政区域分页通过条件
     *
     * @param sysRegionFilterDto sys地区dto
     * @return {@link <PageInfo<SysRegionVo>>}
     */
    PageVO<SysRegionVo> getSysRegionPageByCondition(SysRegionFilterDto sysRegionFilterDto);


    /**
     * 根据条件查询行政区域信息
     *
     * @param sysRegionFilterDto sys地区过滤器dto
     * @return {@link List<SysRegionVo>}
     */
    List<SysRegionVo> getSysRegionByCondition(SysRegionFilterDto sysRegionFilterDto);


    /**
     * 获取行政区域树
     *
     * @return {@link List<SysRegionVo>}
     */
    List<SysRegionVo> getRegionTree();

    /**
     * 通过主键id查询
     *
     * @param id id
     * @return {@link SysRole}
     */
    SysRegionVo queryById(Long id);


    /**
     * 插入
     *
     * @param sysRegionDto 行政区域dto
     */
    void insert(SysRegionDto sysRegionDto,String loginUser);


    /**
     * 更新通过id
     *
     * @param sysRegionDto 行政区域dto
     */
    void updateById(SysRegionDto sysRegionDto,String loginUser);


    /**
     * 通过主键id删除
     *
     * @param id id
     */
    void deleteById(Long id,String loginUser);

}
