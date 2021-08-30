package com.xdcplus.biz.generator.impl;

import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.biz.mapper.VendorMapper;
import com.xdcplus.biz.common.pojo.entity.Vendor;
import com.xdcplus.biz.common.pojo.dto.VendorDTO;
import com.xdcplus.biz.common.pojo.dto.VendorFilterDTO;
import com.xdcplus.biz.common.pojo.vo.VendorVO;
import com.xdcplus.biz.common.pojo.query.VendorQuery;
import com.xdcplus.biz.generator.VendorBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.ztb.common.tool.utils.BeanUtils;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.utils.PageableUtils;
import com.xdcplus.ztb.common.tool.utils.PropertyUtils;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 供应商(Vendor)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-08-12 15:51:14
 */
public class VendorBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<Vendor, VendorVO, Vendor, VendorMapper> implements VendorBaseService<S, T, E> {

    @Autowired
    protected VendorMapper vendorMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendor(VendorDTO vendorDTO) {

        Vendor vendor = vendorMapper.selectById(vendorDTO.getId());
        if (ObjectUtil.isNotNull(vendor)) {
            log.error("saveVendor() The Vendor already exists");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        vendor = new Vendor();
        BeanUtil.copyProperties(vendorDTO, vendor);
        vendor.setCreatedTime(DateUtil.current());
        vendor.setDeleted(0);

        return this.save(vendor);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendor(VendorDTO vendorDTO) {

        Vendor vendor = this.getById(vendorDTO.getId());
        if (ObjectUtil.isNull(vendor)) {
            log.error("updateVendor() The Vendor does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorDTO, vendor);
        vendor.setUpdatedUser(vendorDTO.getUpdatedUser());
        vendor.setUpdatedTime(DateUtil.current());

        return this.updateById(vendor);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<Vendor> vendorList) {

        vendorList.forEach(vendor -> {
            Vendor vendorParam = new Vendor();
            vendorParam.setId(vendor.getId());
            if (ObjectUtil.isNotNull(vendor.getId())) {
                vendor.setId(vendor.getId());
                vendor.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<Vendor> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(Vendor::getId, vendor.getId());
                update(vendor, lambdaUpdate);
            } else {
                vendor.setCreatedTime(DateUtil.current());
                vendor.setDeleted(0);
                save(vendor);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorDTO> vendorDTOList) {

        List<Vendor> vendorList = BeanUtils.copyProperties(vendorDTOList, Vendor.class);
        return saveOrUpdateBatch(vendorList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        Vendor vendor = this.getById(id);

        if (ObjectUtil.isNull(vendor)) {
            log.error("deleteVendor() The Vendor does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }
        vendor.setDeleted(1);

        return this.updateById(vendor);
    }

    private List<Vendor> queryVendorList(VendorFilterDTO vendorFilterDTO) {
        vendorFilterDTO.setDeleted(0);
        VendorQuery vendorQuery = BeanUtil.copyProperties(vendorFilterDTO, VendorQuery.class);

        return vendorMapper.queryVendor(vendorQuery);
    }

    @Override
    public List<VendorVO> queryVendorVOList(VendorFilterDTO vendorFilterDTO) {
        vendorFilterDTO.setDeleted(0);
        return this.objectConversion(queryVendorList(vendorFilterDTO));
    }

    @Override
    public PageVO<VendorVO> queryVendor(VendorFilterDTO vendorFilterDTO) {
        vendorFilterDTO.setDeleted(0);
        PageVO<VendorVO> pageVO = new PageVO<>();

        if (vendorFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorFilterDTO.getCurrentPage(), vendorFilterDTO.getPageSize(),
                    vendorFilterDTO.getOrderType(), vendorFilterDTO.getOrderField());
        }

        List<Vendor> vendorList = queryVendorList(vendorFilterDTO);

        PageInfo<Vendor> pageInfo = new PageInfo<>(vendorList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorList));

        return pageVO;
    }

    @Override
    public VendorVO queryVendorById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
