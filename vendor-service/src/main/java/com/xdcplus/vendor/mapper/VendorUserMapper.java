package com.xdcplus.vendor.mapper;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.vendor.common.pojo.entity.VendorUser;
import com.xdcplus.vendor.common.pojo.query.VendorUserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (VendorUser)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-08-17 11:29:48
 */
public interface VendorUserMapper extends IBaseMapper<VendorUser> {

    /**
     * 查询(VendorUser)
     *
     * @param vendorUserQuery (VendorUser)查询
     * @return {@link List<VendorUser>}
     */
    List<VendorUser> queryVendorUser(VendorUserQuery vendorUserQuery);

    /**
     * 查询用户供应商通过用户id
     *
     * @param userId 用户id
     * @return {@link VendorUser}
     */
    VendorUser findVendorUserByUserId(@Param("userId") Long userId);













}
