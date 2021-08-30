package com.xdcplus.vendor.service;

import com.xdcplus.vendor.generator.VendorUserBaseService;
import com.xdcplus.vendor.common.pojo.entity.VendorUser;
import com.xdcplus.vendor.common.pojo.vo.VendorUserVO;


/**
 * (VendorUser)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-17 11:29:48
 */
public interface VendorUserService extends VendorUserBaseService<VendorUser, VendorUserVO, VendorUser> {

    /**
     * 查询用户供应商通过用户id
     *
     * @param userId 用户id
     * @return {@link VendorUserVO}
     */
    VendorUserVO findVendorUserByUserId(Long userId);

}
