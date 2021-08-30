package com.xdcplus.vendor.generator.impl;

import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.vendor.mapper.BidVendorDetailMapper;
import com.xdcplus.vendor.common.pojo.entity.BidVendorDetail;
import com.xdcplus.vendor.common.pojo.dto.BidVendorDetailDTO;
import com.xdcplus.vendor.common.pojo.dto.BidVendorDetailFilterDTO;
import com.xdcplus.vendor.common.pojo.vo.BidVendorDetailVO;
import com.xdcplus.vendor.common.pojo.query.BidVendorDetailQuery;
import com.xdcplus.vendor.generator.BidVendorDetailBaseService;
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
 * 供应商内容明细（国内报价、国外报价）(BidVendorDetail)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-08-26 09:41:38
 */
public class BidVendorDetailBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<BidVendorDetail, BidVendorDetailVO, BidVendorDetail, BidVendorDetailMapper> implements BidVendorDetailBaseService<S, T, E> {

    @Autowired
    protected BidVendorDetailMapper bidVendorDetailMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveBidVendorDetail(BidVendorDetailDTO bidVendorDetailDTO) {

        BidVendorDetail bidVendorDetail = bidVendorDetailMapper.selectById(bidVendorDetailDTO.getId());
        if (ObjectUtil.isNotNull(bidVendorDetail)) {
            log.error("saveBidVendorDetail() The BidVendorDetail already exists");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        bidVendorDetail = new BidVendorDetail();
        BeanUtil.copyProperties(bidVendorDetailDTO, bidVendorDetail);
        bidVendorDetail.setCreatedTime(DateUtil.current());
        bidVendorDetail.setDeleted(0);

        return this.save(bidVendorDetail);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateBidVendorDetail(BidVendorDetailDTO bidVendorDetailDTO) {

        BidVendorDetail bidVendorDetail = this.getById(bidVendorDetailDTO.getId());
        if (ObjectUtil.isNull(bidVendorDetail)) {
            log.error("updateBidVendorDetail() The BidVendorDetail does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(bidVendorDetailDTO, bidVendorDetail);
        bidVendorDetail.setUpdatedUser(bidVendorDetailDTO.getUpdatedUser());
        bidVendorDetail.setUpdatedTime(DateUtil.current());

        return this.updateById(bidVendorDetail);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<BidVendorDetail> bidVendorDetailList) {

        bidVendorDetailList.forEach(bidVendorDetail -> {
            BidVendorDetail bidVendorDetailParam = new BidVendorDetail();
            bidVendorDetailParam.setId(bidVendorDetail.getId());
            if (ObjectUtil.isNotNull(bidVendorDetail.getId())) {
                bidVendorDetail.setId(bidVendorDetail.getId());
                bidVendorDetail.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<BidVendorDetail> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(BidVendorDetail::getId, bidVendorDetail.getId());
                update(bidVendorDetail, lambdaUpdate);
            } else {
                bidVendorDetail.setCreatedTime(DateUtil.current());
                bidVendorDetail.setDeleted(0);
                save(bidVendorDetail);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<BidVendorDetailDTO> bidVendorDetailDTOList) {

        List<BidVendorDetail> bidVendorDetailList = BeanUtils.copyProperties(bidVendorDetailDTOList, BidVendorDetail.class);
        return saveOrUpdateBatch(bidVendorDetailList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteBidVendorDetailLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        BidVendorDetail bidVendorDetail = this.getById(id);

        if (ObjectUtil.isNull(bidVendorDetail)) {
            log.error("deleteBidVendorDetail() The BidVendorDetail does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }
        bidVendorDetail.setDeleted(1);

        return this.updateById(bidVendorDetail);
    }

    private List<BidVendorDetail> queryBidVendorDetailList(BidVendorDetailFilterDTO bidVendorDetailFilterDTO) {
        bidVendorDetailFilterDTO.setDeleted(0);
        BidVendorDetailQuery bidVendorDetailQuery = BeanUtil.copyProperties(bidVendorDetailFilterDTO, BidVendorDetailQuery.class);

        return bidVendorDetailMapper.queryBidVendorDetail(bidVendorDetailQuery);
    }

    @Override
    public List<BidVendorDetailVO> queryBidVendorDetailVOList(BidVendorDetailFilterDTO bidVendorDetailFilterDTO) {
        bidVendorDetailFilterDTO.setDeleted(0);
        return this.objectConversion(queryBidVendorDetailList(bidVendorDetailFilterDTO));
    }

    @Override
    public PageVO<BidVendorDetailVO> queryBidVendorDetail(BidVendorDetailFilterDTO bidVendorDetailFilterDTO) {
        bidVendorDetailFilterDTO.setDeleted(0);
        PageVO<BidVendorDetailVO> pageVO = new PageVO<>();

        if (bidVendorDetailFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(bidVendorDetailFilterDTO.getCurrentPage(), bidVendorDetailFilterDTO.getPageSize(),
                    bidVendorDetailFilterDTO.getOrderType(), bidVendorDetailFilterDTO.getOrderField());
        }

        List<BidVendorDetail> bidVendorDetailList = queryBidVendorDetailList(bidVendorDetailFilterDTO);

        PageInfo<BidVendorDetail> pageInfo = new PageInfo<>(bidVendorDetailList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(bidVendorDetailList));

        return pageVO;
    }

    @Override
    public BidVendorDetailVO queryBidVendorDetailById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
