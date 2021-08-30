package com.xdcplus.vendorperm.dao;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.vendorperm.common.pojo.entity.SysPosition;
import com.xdcplus.vendorperm.common.pojo.query.sysposition.SysPositionFilterQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 岗位表(SysPosition)表数据库访问层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:08
 */
public interface SysPositionDao extends IBaseMapper<SysPosition> {


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysPositionFilterQuery 实例对象
     * @return 对象列表
     */
    List<SysPosition> getSysPositionPageByCondition(@Param("sysPositionFilterQuery") SysPositionFilterQuery sysPositionFilterQuery);


    SysPosition getSysPositionByShortNameAndNoId(@Param("shortName") String shortName, @Param("id") Long id);

    /**
     * 根据职位id获取绑定该职位的岗位
     *
     * @param jobId 工作id
     * @return {@link SysPosition}
     */
    List<SysPosition> getByJobId(@Param("jobId") Long jobId);

}

