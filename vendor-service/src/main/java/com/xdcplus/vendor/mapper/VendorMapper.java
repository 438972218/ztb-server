package com.xdcplus.vendor.mapper;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.vendor.common.pojo.entity.Vendor;
import com.xdcplus.vendor.common.pojo.query.VendorQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商(Vendor)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-08-23 10:14:52
 */
public interface VendorMapper extends IBaseMapper<Vendor> {

    /**
     * 查询供应商(Vendor)
     *
     * @param vendorQuery 供应商(Vendor)查询
     * @return {@link List<Vendor>}
     */
    List<Vendor> queryVendor(VendorQuery vendorQuery);

}
