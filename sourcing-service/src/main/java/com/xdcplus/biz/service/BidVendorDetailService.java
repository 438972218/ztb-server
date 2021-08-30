package com.xdcplus.biz.service;

import com.xdcplus.biz.common.pojo.dto.BidSheetDTO;
import com.xdcplus.biz.common.pojo.dto.BidVendorDTO;
import com.xdcplus.biz.generator.BidVendorDetailBaseService;
import com.xdcplus.biz.common.pojo.entity.BidVendorDetail;
import com.xdcplus.biz.common.pojo.vo.BidVendorDetailVO;


/**
 * 供应商内容明细（国内报价、国外报价）(BidVendorDetail)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:23:42
 */
public interface BidVendorDetailService extends BidVendorDetailBaseService<BidVendorDetail, BidVendorDetailVO, BidVendorDetail> {

//    void batchUpdateBidDetailDTO(BidVendorDTO bidVendorDTO);

}
