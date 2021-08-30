package com.xdcplus.biz.service;

import com.xdcplus.biz.common.pojo.dto.VendorDTO;
import com.xdcplus.biz.generator.VendorBaseService;
import com.xdcplus.biz.common.pojo.entity.Vendor;
import com.xdcplus.biz.common.pojo.vo.VendorVO;


/**
 * 供应商(Vendor)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-12 15:51:15
 */
public interface VendorService extends VendorBaseService<Vendor, VendorVO, Vendor> {

//    /**
//     * 修改供应商以及detail
//     *
//     * @param vendorDTO 供应商(vendorDTO)主键
//     * @return {@link Boolean}
//     */
//    Boolean updateVendorWithDetail(VendorDTO vendorDTO);
//
//    /**
//     * 供应商详情
//     *
//     * @param vendorId 供应商(vendorDTO)主键
//     * @return {@link VendorVO}
//     */
//    VendorVO showVendorById(Long vendorId);

    /**
     * 根据userId查询供应商
     *
     * @param userId
     * @return {@link VendorVO}
     */
    VendorVO showVendorByUserId(Long userId);

}
