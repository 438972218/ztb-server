package com.xdcplus.biz.mapper;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.biz.common.pojo.entity.BidDetail;
import com.xdcplus.biz.common.pojo.query.BidDetailQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 招标单内容明细（报价须知、国内报价、国外报价）(BidDetail)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-08-27 11:26:48
 */
public interface BidDetailMapper extends IBaseMapper<BidDetail> {

    /**
     * 查询招标单内容明细（报价须知、国内报价、国外报价）(BidDetail)
     *
     * @param bidDetailQuery 招标单内容明细（报价须知、国内报价、国外报价）(BidDetail)查询
     * @return {@link List<BidDetail>}
     */
    List<BidDetail> queryBidDetail(BidDetailQuery bidDetailQuery);

}
