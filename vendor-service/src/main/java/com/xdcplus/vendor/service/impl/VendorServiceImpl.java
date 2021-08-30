package com.xdcplus.vendor.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.xdcplus.vendor.common.pojo.dto.VendorDTO;
import com.xdcplus.vendor.common.pojo.dto.VendorUserDTO;
import com.xdcplus.vendor.common.pojo.query.VendorQuery;
import com.xdcplus.vendor.generator.impl.VendorBaseServiceImpl;
import com.xdcplus.vendor.mapper.VendorMapper;
import com.xdcplus.vendor.common.pojo.entity.Vendor;
import com.xdcplus.vendor.common.pojo.vo.VendorVO;
import com.xdcplus.vendor.remote.service.PermService;
import com.xdcplus.vendor.service.VendorService;
import com.xdcplus.vendor.service.VendorUserService;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.remote.domain.perm.vo.SysUserInfoVO;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 供应商(Vendor)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-08-23 10:14:53
 */
@Slf4j
@Service("vendorService")
public class VendorServiceImpl extends VendorBaseServiceImpl<Vendor, VendorVO, Vendor, VendorMapper> implements VendorService {

    @Autowired
    PermService permService;
    @Autowired
    VendorUserService vendorUserService;

    @Override
    public Boolean registerVendor(VendorDTO vendorDTO) {
        if(ObjectUtil.isEmpty(vendorDTO.getRegisterUserDTO())){
            log.error("loginVendor() registerUserDTO select faild");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        List<Vendor> vendors = findVendorVOByNameAndBusinessLicense(vendorDTO.getName(),vendorDTO.getBusinessLicense());
        Vendor vendor;
        if(CollectionUtil.isEmpty(vendors)){
            vendor = new Vendor();
            BeanUtil.copyProperties(vendorDTO, vendor);
            vendor.setCreatedTime(DateUtil.current());
            vendor.setDeleted(0);
            boolean result =this.save(vendor);
            if(!result){
                log.error("loginVendor() vendor save faild");
                throw new ZtbWebException(ResponseEnum.ERROR);
            }
        }else{
            vendor=vendors.get(NumberConstant.ZERO);
        }

        if(ObjectUtil.isEmpty(vendor)){
            log.error("loginVendor() vendor save faild");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }
        SysUserInfoVO sysUserInfoVO = permService.registerUser(vendorDTO.getRegisterUserDTO());

        VendorUserDTO vendorUserDTO=new VendorUserDTO();
        vendorUserDTO.setUserId(sysUserInfoVO.getId());
        vendorUserDTO.setVendorId(vendor.getId());

        return vendorUserService.saveVendorUser(vendorUserDTO);
    }

    @Override
    public List<Vendor> findVendorVOByNameAndBusinessLicense(String name, String businessLicense) {
        VendorQuery vendorQuery=new VendorQuery();
        vendorQuery.setName(name);
        vendorQuery.setBusinessLicense(businessLicense);
        List<Vendor> vendors = vendorMapper.queryVendor(vendorQuery);

        return vendors;
    }

}
