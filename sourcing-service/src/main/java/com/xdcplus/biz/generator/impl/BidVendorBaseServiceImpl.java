package com.xdcplus.biz.generator.impl;

import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.biz.mapper.BidVendorMapper;
import com.xdcplus.biz.common.pojo.entity.BidVendor;
import com.xdcplus.biz.common.pojo.dto.BidVendorDTO;
import com.xdcplus.biz.common.pojo.dto.BidVendorFilterDTO;
import com.xdcplus.biz.common.pojo.vo.BidVendorVO;
import com.xdcplus.biz.common.pojo.query.BidVendorQuery;
import com.xdcplus.biz.generator.BidVendorBaseService;
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
 * 招标投标方(BidVendor)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:24:03
 */
public class BidVendorBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<BidVendor, BidVendorVO, BidVendor, BidVendorMapper> implements BidVendorBaseService<S, T, E> {

    @Autowired
    protected BidVendorMapper bidVendorMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveBidVendor(BidVendorDTO bidVendorDTO) {

        BidVendor bidVendor = bidVendorMapper.selectById(bidVendorDTO.getId());
        if (ObjectUtil.isNotNull(bidVendor)) {
            log.error("saveBidVendor() The BidVendor already exists");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        bidVendor = new BidVendor();
        BeanUtil.copyProperties(bidVendorDTO, bidVendor);
        bidVendor.setCreatedTime(DateUtil.current());
        bidVendor.setDeleted(0);

        return this.save(bidVendor);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateBidVendor(BidVendorDTO bidVendorDTO) {

        BidVendor bidVendor = this.getById(bidVendorDTO.getId());
        if (ObjectUtil.isNull(bidVendor)) {
            log.error("updateBidVendor() The BidVendor does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(bidVendorDTO, bidVendor);
        bidVendor.setUpdatedUser(bidVendorDTO.getUpdatedUser());
        bidVendor.setUpdatedTime(DateUtil.current());

        return this.updateById(bidVendor);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<BidVendor> bidVendorList) {

        bidVendorList.forEach(bidVendor -> {
            BidVendor bidVendorParam = new BidVendor();
            bidVendorParam.setId(bidVendor.getId());
            if (ObjectUtil.isNotNull(bidVendor.getId())) {
                bidVendor.setId(bidVendor.getId());
                bidVendor.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<BidVendor> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(BidVendor::getId, bidVendor.getId());
                update(bidVendor, lambdaUpdate);
            } else {
                bidVendor.setCreatedTime(DateUtil.current());
                bidVendor.setDeleted(0);
                save(bidVendor);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<BidVendorDTO> bidVendorDTOList) {

        List<BidVendor> bidVendorList = BeanUtils.copyProperties(bidVendorDTOList, BidVendor.class);
        return saveOrUpdateBatch(bidVendorList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteBidVendorLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        BidVendor bidVendor = this.getById(id);

        if (ObjectUtil.isNull(bidVendor)) {
            log.error("deleteBidVendor() The BidVendor does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }
        bidVendor.setDeleted(1);

        return this.updateById(bidVendor);
    }

    private List<BidVendor> queryBidVendorList(BidVendorFilterDTO bidVendorFilterDTO) {
        bidVendorFilterDTO.setDeleted(0);
        BidVendorQuery bidVendorQuery = BeanUtil.copyProperties(bidVendorFilterDTO, BidVendorQuery.class);

        return bidVendorMapper.queryBidVendor(bidVendorQuery);
    }

    @Override
    public List<BidVendorVO> queryBidVendorVOList(BidVendorFilterDTO bidVendorFilterDTO) {
        bidVendorFilterDTO.setDeleted(0);
        return this.objectConversion(queryBidVendorList(bidVendorFilterDTO));
    }

    @Override
    public PageVO<BidVendorVO> queryBidVendor(BidVendorFilterDTO bidVendorFilterDTO) {
        bidVendorFilterDTO.setDeleted(0);
        PageVO<BidVendorVO> pageVO = new PageVO<>();

        if (bidVendorFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(bidVendorFilterDTO.getCurrentPage(), bidVendorFilterDTO.getPageSize(),
                    bidVendorFilterDTO.getOrderType(), bidVendorFilterDTO.getOrderField());
        }

        List<BidVendor> bidVendorList = queryBidVendorList(bidVendorFilterDTO);

        PageInfo<BidVendor> pageInfo = new PageInfo<>(bidVendorList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(bidVendorList));

        return pageVO;
    }

    @Override
    public BidVendorVO queryBidVendorById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
