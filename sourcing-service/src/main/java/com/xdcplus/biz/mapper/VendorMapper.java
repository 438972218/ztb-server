package com.xdcplus.biz.mapper;

import com.xdcplus.biz.common.pojo.vo.VendorVO;
import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.biz.common.pojo.entity.Vendor;
import com.xdcplus.biz.common.pojo.query.VendorQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商(Vendor)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-08-12 15:51:14
 */
public interface VendorMapper extends IBaseMapper<Vendor> {

    /**
     * 查询供应商(Vendor)
     *
     * @param vendorQuery 供应商(Vendor)查询
     * @return {@link List<Vendor>}
     */
    List<Vendor> queryVendor(VendorQuery vendorQuery);

    /**
     * 查询供应商(Vendor)
     *
     * @param id
     * @return {@link List<Vendor>}
     */
    VendorVO showVendorByUserId(Long id);

}
