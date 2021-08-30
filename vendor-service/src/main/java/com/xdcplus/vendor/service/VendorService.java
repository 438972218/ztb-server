package com.xdcplus.vendor.service;

import com.xdcplus.vendor.common.pojo.dto.VendorDTO;
import com.xdcplus.vendor.generator.VendorBaseService;
import com.xdcplus.vendor.common.pojo.entity.Vendor;
import com.xdcplus.vendor.common.pojo.vo.VendorVO;

import java.util.List;


/**
 * 供应商(Vendor)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-23 10:14:53
 */
public interface VendorService extends VendorBaseService<Vendor, VendorVO, Vendor> {

    Boolean registerVendor(VendorDTO vendorDTO);

    List<Vendor> findVendorVOByNameAndBusinessLicense(String name, String businessLicense);

}
