package com.xdcplus.vendor.mapper;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.vendor.common.pojo.entity.BidVendorDetail;
import com.xdcplus.vendor.common.pojo.query.BidVendorDetailQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商内容明细（国内报价、国外报价）(BidVendorDetail)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-08-27 11:28:22
 */
public interface BidVendorDetailMapper extends IBaseMapper<BidVendorDetail> {

    /**
     * 查询供应商内容明细（国内报价、国外报价）(BidVendorDetail)
     *
     * @param bidVendorDetailQuery 供应商内容明细（国内报价、国外报价）(BidVendorDetail)查询
     * @return {@link List<BidVendorDetail>}
     */
    List<BidVendorDetail> queryBidVendorDetail(BidVendorDetailQuery bidVendorDetailQuery);

}
