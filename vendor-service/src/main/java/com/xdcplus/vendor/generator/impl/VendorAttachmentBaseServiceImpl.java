package com.xdcplus.vendor.generator.impl;

import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.vendor.mapper.VendorAttachmentMapper;
import com.xdcplus.vendor.common.pojo.entity.VendorAttachment;
import com.xdcplus.vendor.common.pojo.dto.VendorAttachmentDTO;
import com.xdcplus.vendor.common.pojo.dto.VendorAttachmentFilterDTO;
import com.xdcplus.vendor.common.pojo.vo.VendorAttachmentVO;
import com.xdcplus.vendor.common.pojo.query.VendorAttachmentQuery;
import com.xdcplus.vendor.generator.VendorAttachmentBaseService;
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
 * 招标附件(VendorAttachment)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-08-23 10:14:55
 */
public class VendorAttachmentBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<VendorAttachment, VendorAttachmentVO, VendorAttachment, VendorAttachmentMapper> implements VendorAttachmentBaseService<S, T, E> {

    @Autowired
    protected VendorAttachmentMapper vendorAttachmentMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendorAttachment(VendorAttachmentDTO vendorAttachmentDTO) {

        VendorAttachment vendorAttachment = vendorAttachmentMapper.selectById(vendorAttachmentDTO.getId());
        if (ObjectUtil.isNotNull(vendorAttachment)) {
            log.error("saveVendorAttachment() The VendorAttachment already exists");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        vendorAttachment = new VendorAttachment();
        BeanUtil.copyProperties(vendorAttachmentDTO, vendorAttachment);
        vendorAttachment.setCreatedTime(DateUtil.current());
        vendorAttachment.setDeleted(0);

        return this.save(vendorAttachment);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendorAttachment(VendorAttachmentDTO vendorAttachmentDTO) {

        VendorAttachment vendorAttachment = this.getById(vendorAttachmentDTO.getId());
        if (ObjectUtil.isNull(vendorAttachment)) {
            log.error("updateVendorAttachment() The VendorAttachment does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorAttachmentDTO, vendorAttachment);
        vendorAttachment.setUpdatedUser(vendorAttachmentDTO.getUpdatedUser());
        vendorAttachment.setUpdatedTime(DateUtil.current());

        return this.updateById(vendorAttachment);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<VendorAttachment> vendorAttachmentList) {

        vendorAttachmentList.forEach(vendorAttachment -> {
            VendorAttachment vendorAttachmentParam = new VendorAttachment();
            vendorAttachmentParam.setId(vendorAttachment.getId());
            if (ObjectUtil.isNotNull(vendorAttachment.getId())) {
                vendorAttachment.setId(vendorAttachment.getId());
                vendorAttachment.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<VendorAttachment> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(VendorAttachment::getId, vendorAttachment.getId());
                update(vendorAttachment, lambdaUpdate);
            } else {
                vendorAttachment.setCreatedTime(DateUtil.current());
                vendorAttachment.setDeleted(0);
                save(vendorAttachment);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorAttachmentDTO> vendorAttachmentDTOList) {

        List<VendorAttachment> vendorAttachmentList = BeanUtils.copyProperties(vendorAttachmentDTOList, VendorAttachment.class);
        return saveOrUpdateBatch(vendorAttachmentList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorAttachmentLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        VendorAttachment vendorAttachment = this.getById(id);

        if (ObjectUtil.isNull(vendorAttachment)) {
            log.error("deleteVendorAttachment() The VendorAttachment does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }
        vendorAttachment.setDeleted(1);

        return this.updateById(vendorAttachment);
    }

    private List<VendorAttachment> queryVendorAttachmentList(VendorAttachmentFilterDTO vendorAttachmentFilterDTO) {
        vendorAttachmentFilterDTO.setDeleted(0);
        VendorAttachmentQuery vendorAttachmentQuery = BeanUtil.copyProperties(vendorAttachmentFilterDTO, VendorAttachmentQuery.class);

        return vendorAttachmentMapper.queryVendorAttachment(vendorAttachmentQuery);
    }

    @Override
    public List<VendorAttachmentVO> queryVendorAttachmentVOList(VendorAttachmentFilterDTO vendorAttachmentFilterDTO) {
        vendorAttachmentFilterDTO.setDeleted(0);
        return this.objectConversion(queryVendorAttachmentList(vendorAttachmentFilterDTO));
    }

    @Override
    public PageVO<VendorAttachmentVO> queryVendorAttachment(VendorAttachmentFilterDTO vendorAttachmentFilterDTO) {
        vendorAttachmentFilterDTO.setDeleted(0);
        PageVO<VendorAttachmentVO> pageVO = new PageVO<>();

        if (vendorAttachmentFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorAttachmentFilterDTO.getCurrentPage(), vendorAttachmentFilterDTO.getPageSize(),
                    vendorAttachmentFilterDTO.getOrderType(), vendorAttachmentFilterDTO.getOrderField());
        }

        List<VendorAttachment> vendorAttachmentList = queryVendorAttachmentList(vendorAttachmentFilterDTO);

        PageInfo<VendorAttachment> pageInfo = new PageInfo<>(vendorAttachmentList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorAttachmentList));

        return pageVO;
    }

    @Override
    public VendorAttachmentVO queryVendorAttachmentById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
