package com.xdcplus.vendor.service.impl;

import cn.hutool.core.lang.Assert;
import com.xdcplus.vendor.generator.impl.VendorUserBaseServiceImpl;
import com.xdcplus.vendor.mapper.VendorUserMapper;
import com.xdcplus.vendor.common.pojo.entity.VendorUser;
import com.xdcplus.vendor.common.pojo.vo.VendorUserVO;
import com.xdcplus.vendor.service.VendorUserService;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * (VendorUser)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-08-17 11:29:49
 */
@Slf4j
@Service("vendorUserService")
public class VendorUserServiceImpl extends VendorUserBaseServiceImpl<VendorUser, VendorUserVO, VendorUser, VendorUserMapper> implements VendorUserService {

    @Autowired
    private VendorUserMapper vendorUserMapper;

    @Override
    public VendorUserVO findVendorUserByUserId(Long userId) {

        Assert.notNull(userId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(vendorUserMapper.findVendorUserByUserId(userId));
    }
}
