package com.xdcplus.vendor.service;

import com.xdcplus.vendor.common.pojo.dto.BidVendorDetailDTO;
import com.xdcplus.vendor.generator.BidVendorDetailBaseService;
import com.xdcplus.vendor.common.pojo.entity.BidVendorDetail;
import com.xdcplus.vendor.common.pojo.vo.BidVendorDetailVO;

import java.util.List;


/**
 * 供应商内容明细（国内报价、国外报价）(BidVendorDetail)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-26 09:41:39
 */
public interface BidVendorDetailService extends BidVendorDetailBaseService<BidVendorDetail, BidVendorDetailVO, BidVendorDetail> {

    void updateBidVendorDetailByOperation(Long bidVendorId,String operation);

    /**
     * 批量更新报价细节dto并更新供应商总价
     *
     * @param bidVendorDetailDTOS 投标供应商详细dto
     */
    void batchUpdateBidDetailDTO(List<BidVendorDetailDTO> bidVendorDetailDTOS);
}
