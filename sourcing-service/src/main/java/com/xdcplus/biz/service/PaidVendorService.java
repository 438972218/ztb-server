package com.xdcplus.biz.service;

import com.xdcplus.biz.common.pojo.dto.PaidVendorDTO;
import com.xdcplus.biz.generator.PaidVendorBaseService;
import com.xdcplus.biz.common.pojo.entity.PaidVendor;
import com.xdcplus.biz.common.pojo.vo.PaidVendorVO;


/**
 * 竞价供应商(PaidVendor)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-08-16 14:02:06
 */
public interface PaidVendorService extends PaidVendorBaseService<PaidVendor, PaidVendorVO, PaidVendor> {

    /**
     * 供应商修改供应商状态(PaidVendor)
     *
     * @param paidVendorDTO 招标供应商(PaidVendorDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updatePaidVendorStatus(PaidVendorDTO paidVendorDTO);

}
