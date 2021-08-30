package com.xdcplus.permission.dao;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.permission.common.pojo.entity.SysJob;
import com.xdcplus.permission.common.pojo.query.sysjob.SysJobFilterQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 职务表(SysJob)表数据库访问层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:04
 */
public interface SysJobDao  extends IBaseMapper<SysJob> {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysJobFilterQuery 实例对象
     * @return 对象列表
     */
    List<SysJob> getSysJobByCondition(@Param("sysJobFilterQuery") SysJobFilterQuery sysJobFilterQuery);


    SysJob getSysJobByShortNameAndNoId(@Param("shortName") String shortName, @Param("id") Long id);

    /**
     * 根据职位类别id获取职位
     *
     * @param jobTypeId 作业类型id
     * @return {@link List<SysJob>}
     */
    List<SysJob> getByJobTypeId(@Param("jobTypeId") Long jobTypeId);


}

