package com.xdcplus.biz.mapper;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.biz.common.pojo.entity.Dictionary;
import com.xdcplus.biz.common.pojo.query.DictionaryQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字典管理(Dictionary)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-08-12 15:20:51
 */
public interface DictionaryMapper extends IBaseMapper<Dictionary> {

    /**
     * 查询字典管理(Dictionary)
     *
     * @param dictionaryQuery 字典管理(Dictionary)查询
     * @return {@link List<Dictionary>}
     */
    List<Dictionary> queryDictionary(DictionaryQuery dictionaryQuery);

}
