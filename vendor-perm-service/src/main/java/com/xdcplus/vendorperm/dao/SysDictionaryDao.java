package com.xdcplus.vendorperm.dao;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.vendorperm.common.pojo.entity.SysDictionary;
import com.xdcplus.vendorperm.common.pojo.query.sysdictionary.SysDictionaryFilterQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字典表(SysDictionary)表数据库访问层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:11
 */
public interface SysDictionaryDao extends IBaseMapper<SysDictionary> {


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysDictionaryFilterQuery 实例对象
     * @return 对象列表
     */
    List<SysDictionary> getSysDictionaryPageByCondition(@Param("sysDictionaryFilterQuery") SysDictionaryFilterQuery sysDictionaryFilterQuery);


}

