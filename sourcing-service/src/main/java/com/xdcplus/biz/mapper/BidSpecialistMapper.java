package com.xdcplus.biz.mapper;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.biz.common.pojo.entity.BidSpecialist;
import com.xdcplus.biz.common.pojo.query.BidSpecialistQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 专家(BidSpecialist)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-08-24 16:22:57
 */
public interface BidSpecialistMapper extends IBaseMapper<BidSpecialist> {

    /**
     * 查询专家(BidSpecialist)
     *
     * @param bidSpecialistQuery 专家(BidSpecialist)查询
     * @return {@link List<BidSpecialist>}
     */
    List<BidSpecialist> queryBidSpecialist(BidSpecialistQuery bidSpecialistQuery);

}
