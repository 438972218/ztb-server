package com.xdcplus.biz.mapper;

import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import com.xdcplus.biz.common.pojo.entity.PaidVendor;
import com.xdcplus.biz.common.pojo.query.PaidVendorQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 竞价供应商(PaidVendor)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-08-30 13:37:46
 */
public interface PaidVendorMapper extends IBaseMapper<PaidVendor> {

    /**
     * 查询竞价供应商(PaidVendor)
     *
     * @param paidVendorQuery 竞价供应商(PaidVendor)查询
     * @return {@link List<PaidVendor>}
     */
    List<PaidVendor> queryPaidVendor(PaidVendorQuery paidVendorQuery);

}
