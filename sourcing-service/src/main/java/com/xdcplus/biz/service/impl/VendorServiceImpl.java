package com.xdcplus.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.xdcplus.biz.common.pojo.dto.VendorDTO;
import com.xdcplus.biz.generator.impl.VendorBaseServiceImpl;
import com.xdcplus.biz.mapper.VendorMapper;
import com.xdcplus.biz.common.pojo.entity.Vendor;
import com.xdcplus.biz.common.pojo.vo.VendorVO;
import com.xdcplus.biz.service.VendorService;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 供应商(Vendor)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-08-12 15:51:15
 */
@Slf4j
@Service("vendorService")
public class VendorServiceImpl extends VendorBaseServiceImpl<Vendor, VendorVO, Vendor, VendorMapper> implements VendorService {

//    @Autowired
//    private VendorCertificateService vendorCertificateService;
//
//    @Override
//    public Boolean updateVendorWithDetail(VendorDTO vendorDTO) {
//        Vendor vendor = this.getById(vendorDTO.getId());
//        if (ObjectUtil.isNull(vendor)) {
//            log.error("updateVendorQuestion() The VendorQuestion does not exist or has been deleted");
//            throw new ZtbWebException(ResponseEnum.ERROR);
//        }
//        BeanUtil.copyProperties(vendorDTO, vendor);
//        vendor.setUpdatedUser(vendorDTO.getUpdatedUser());
//        vendor.setUpdatedTime(DateUtil.current());
//        Boolean result = this.updateById(vendor);
//        vendorCertificateService.deleteVendorCertificateByVendorId(vendorDTO.getId());
//
//        if (CollectionUtil.isEmpty(vendorDTO.getVendorCertificateDTOS())) {
//            return result;
//        }
//
//        List<VendorCertificateDTO> vendorCertificateDTOS = vendorDTO.getVendorCertificateDTOS();
//
//        vendorCertificateDTOS.forEach(vendorCertificateDTO -> {
//            vendorCertificateDTO.setVendorId(vendorDTO.getId());
//            vendorCertificateService.saveVendorCertificate(vendorCertificateDTO);
//        });
//
//        return result;
//    }
//
//    @Override
//    public VendorVO showVendorById(Long vendorId) {
//        Assert.notNull(vendorId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
//        VendorVO vendorVO = this.objectConversion(this.getById(vendorId));
//
//        VendorCertificateFilterDTO vendorCertificateFilterDTO = new VendorCertificateFilterDTO();
//        vendorCertificateFilterDTO.setVendorId(vendorId);
//        vendorVO.setVendorCertificateVOS(vendorCertificateService.queryVendorCertificateVOList(vendorCertificateFilterDTO));
//
//        return vendorVO;
//    }

    @Override
    public VendorVO showVendorByUserId(Long userId) {
        return vendorMapper.showVendorByUserId(userId);
    }


}
