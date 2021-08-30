package com.xdcplus.biz.mapper;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.biz.common.pojo.entity.Item;
import com.xdcplus.biz.common.pojo.query.ItemQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 品类管理(Item)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-08-18 13:47:05
 */
public interface ItemMapper extends IBaseMapper<Item> {

    /**
     * 查询品类管理(Item)
     *
     * @param itemQuery 品类管理(Item)查询
     * @return {@link List<Item>}
     */
    List<Item> queryItem(ItemQuery itemQuery);

}
