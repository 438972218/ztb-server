package com.xdcplus.vendor.service;

import com.xdcplus.vendor.common.pojo.dto.BidVendorDTO;
import com.xdcplus.vendor.generator.BidVendorBaseService;
import com.xdcplus.vendor.common.pojo.entity.BidVendor;
import com.xdcplus.vendor.common.pojo.vo.BidVendorVO;


/**
 * 招标投标方(BidVendor)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:42:09
 */
public interface BidVendorService extends BidVendorBaseService<BidVendor, BidVendorVO, BidVendor> {

    /**
     * 供应商修改招标供应商状态(BidVendor)
     *
     * @param bidVendorDTO 招标供应商(BidVendorDTO)
     */
    void updateBidVendorStatusByVendorUserId(BidVendorDTO bidVendorDTO);

    /**
     * 更新报价供应商检查时间由供应商用户id
     *
     * @param bidVendorDTO 投标供应商dto
     * @return {@link Boolean}
     */
    void updateBidVendorCheckTimeByVendorUserId(BidVendorDTO bidVendorDTO);
}
