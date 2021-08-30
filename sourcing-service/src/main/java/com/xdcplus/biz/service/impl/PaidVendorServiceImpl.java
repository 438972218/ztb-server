package com.xdcplus.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xdcplus.biz.common.pojo.dto.PaidVendorDTO;
import com.xdcplus.biz.generator.impl.PaidVendorBaseServiceImpl;
import com.xdcplus.biz.mapper.PaidVendorMapper;
import com.xdcplus.biz.common.pojo.entity.PaidVendor;
import com.xdcplus.biz.common.pojo.vo.PaidVendorVO;
import com.xdcplus.biz.service.PaidVendorService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 竞价供应商(PaidVendor)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-08-16 14:02:06
 */
@Slf4j
@Service("paidVendorService")
public class PaidVendorServiceImpl extends PaidVendorBaseServiceImpl<PaidVendor, PaidVendorVO, PaidVendor, PaidVendorMapper> implements PaidVendorService {

    @Override
    public Boolean updatePaidVendorStatus(PaidVendorDTO paidVendorDTO) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("paid_sheet_id", paidVendorDTO.getPaidSheetId());
        updateWrapper.eq("vendor_code", paidVendorDTO.getVendorCode());
        PaidVendor paidVendor = new PaidVendor();
        paidVendor.setVendorStatus(paidVendorDTO.getVendorStatus());
        int result = paidVendorMapper.update(paidVendor, updateWrapper);
        if (result != 0) {
            return true;
        } else {
            return false;
        }
    }

}
