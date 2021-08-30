package com.xdcplus.vendor.mapper;

import com.xdcplus.vendor.common.pojo.entity.Offer;
import com.xdcplus.vendor.common.pojo.query.OfferQuery;
import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 出价记录 Mapper 接口
 *
 * @author Rong.Jia
 * @since 2021-08-17
 */
public interface OfferMapper extends IBaseMapper<Offer> {

    /**
     * 根据物品标识查询出价记录
     *
     * @param offerGoods 物品标识
     * @return {@link List <Offer>} 出价记录
     */
    List<Offer> findOfferByOfferGoods(@Param("offerGoods") String offerGoods);

    /**
     * 查询出价记录
     *
     * @param offerQuery 查询对象
     * @return {@link List<Offer>} 出价记录
     */
    List<Offer> findOffer(OfferQuery offerQuery);


}
