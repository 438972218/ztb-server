package com.xdcplus.permission.dao;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.permission.common.pojo.entity.SysRegion;
import com.xdcplus.permission.common.pojo.query.sysregion.SysRegionFilterQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 行政区域表(SysRegion)表数据库访问层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:06
 */
public interface SysRegionDao extends IBaseMapper<SysRegion> {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysRegionFilterQuery 实例对象
     * @return 对象列表
     */
    List<SysRegion> getSysRegionByCondition(@Param("sysRegionFilterQuery") SysRegionFilterQuery sysRegionFilterQuery);

    SysRegion getSysRegionByNameAndNoId(@Param("name") String roleName, @Param("id") Long id,@Param("parentId") Long parentId);

    List<SysRegion> getChildrenRegionById(@Param("id") Long id);

    Integer updateSysRegionChildren(@Param("sysRegionList")List<SysRegion> sysRegionList);
}

