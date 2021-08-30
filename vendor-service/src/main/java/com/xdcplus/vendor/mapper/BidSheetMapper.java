package com.xdcplus.vendor.mapper;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.vendor.common.pojo.entity.BidSheet;
import com.xdcplus.vendor.common.pojo.query.BidSheetQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * bid招标单(BidSheet)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-08-26 16:40:12
 */
public interface BidSheetMapper extends IBaseMapper<BidSheet> {

    /**
     * 查询bid招标单(BidSheet)
     *
     * @param bidSheetQuery bid招标单(BidSheet)查询
     * @return {@link List<BidSheet>}
     */
    List<BidSheet> queryBidSheet(BidSheetQuery bidSheetQuery);

    /**
     * 查询bid招标单(BidSheet)
     *
     * @param bidSheetQuery bid招标单(BidSheet)查询
     * @return {@link List<BidSheet>}
     */
    List<BidSheet> queryBidSheetByVendor(BidSheetQuery bidSheetQuery);

}
