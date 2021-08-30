package com.xdcplus.vendor.service;

import com.xdcplus.vendor.common.pojo.dto.BidSheetFilterDTO;
import com.xdcplus.vendor.generator.BidSheetBaseService;
import com.xdcplus.vendor.common.pojo.entity.BidSheet;
import com.xdcplus.vendor.common.pojo.vo.BidSheetVO;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;


/**
 * bid招标单(BidSheet)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:37:40
 */
public interface BidSheetService extends BidSheetBaseService<BidSheet, BidSheetVO, BidSheet> {

    /**
     * 查询招标单(供应商)(BidSheet)
     *
     * @param bidSheetFilterDTO 过程状态过滤DTO
     * @return {@link PageVO <  BidSheetVO  >} 状态信息
     */
    PageVO<BidSheetVO> queryBidSheetByVendor(BidSheetFilterDTO bidSheetFilterDTO);

    /**
     * 查询招标单(供应商)(BidSheet)
     *
     * @param bidSheetFilterDTO 过程状态过滤DTO
     * @return {@link PageVO <  BidSheetVO  >} 状态信息
     */
    PageVO<BidSheetVO> queryBidSheetByVendor1(BidSheetFilterDTO bidSheetFilterDTO);
}
