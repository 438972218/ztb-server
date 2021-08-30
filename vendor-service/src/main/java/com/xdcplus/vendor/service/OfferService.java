package com.xdcplus.vendor.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xdcplus.vendor.common.pojo.dto.OfferDTO;
import com.xdcplus.vendor.common.pojo.dto.OfferFilterDTO;
import com.xdcplus.vendor.common.pojo.entity.Offer;
import com.xdcplus.vendor.common.pojo.vo.OfferVO;
import com.xdcplus.ztb.common.mp.service.BaseService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;

import java.util.List;

/**
 * 出价记录 服务类
 * @author Rong.Jia
 * @since 2021-08-17
 */
public interface OfferService extends BaseService<Offer, OfferVO, Offer> {

    /**
     * 保存出价记录
     *
     * @param offerDTO 出价DTO
     * @return {@link OfferVO} 出价信息
     */
    OfferVO saveOffer(OfferDTO offerDTO);

    /**
     * 查询一个
     *
     * @param offerId 出价记录ID
     * @return {@link OfferVO}
     */
    OfferVO findOne(Long offerId);

    /**
     * 获取排名
     *
     * @param offerId 出价记录ID
     * @param positive 正向排名， true: 正向。false: 反向
     * @return {@link Integer} 排名
     */
    Integer getRanking(Long offerId, Boolean positive);

    /**
     * 根据物品标识查询出价记录
     *
     * @param offerGoods 物品标识
     * @param positive 正向排名， true: 正向。false: 反向
     * @return {@link List<OfferVO>} 出价记录
     */
    List<OfferVO> findOfferByOfferGoods(String offerGoods, Boolean positive);

    /**
     * 查询出价记录
     *
     * @param filterDTO 过滤器DTO
     * @return {@link PageVO<OfferVO>} 出价记录
     */
    PageVO<OfferVO> findOffer(OfferFilterDTO filterDTO);






}
