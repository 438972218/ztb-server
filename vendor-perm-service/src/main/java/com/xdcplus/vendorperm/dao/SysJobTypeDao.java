package com.xdcplus.vendorperm.dao;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.vendorperm.common.pojo.entity.SysJobType;
import com.xdcplus.vendorperm.common.pojo.query.sysjobtype.SysJobTypeFilterQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 职务类别表(SysJobType)表数据库访问层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:11
 */
public interface SysJobTypeDao extends IBaseMapper<SysJobType> {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysJobTypeFilterQuery 实例对象
     * @return 对象列表
     */
    List<SysJobType> getSysJobTypePageByCondition(@Param("sysJobTypeFilterQuery") SysJobTypeFilterQuery sysJobTypeFilterQuery);

    SysJobType getSysJobTypeByShortNameAndNoId(@Param("shortName") String shortName, @Param("id") Long id);
}

