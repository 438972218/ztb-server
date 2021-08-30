package com.xdcplus.biz.generator.impl;

import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.biz.mapper.BidDetailMapper;
import com.xdcplus.biz.common.pojo.entity.BidDetail;
import com.xdcplus.biz.common.pojo.dto.BidDetailDTO;
import com.xdcplus.biz.common.pojo.dto.BidDetailFilterDTO;
import com.xdcplus.biz.common.pojo.vo.BidDetailVO;
import com.xdcplus.biz.common.pojo.query.BidDetailQuery;
import com.xdcplus.biz.generator.BidDetailBaseService;
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
 * 招标单内容明细（报价须知、国内报价、国外报价）(BidDetail)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:23:15
 */
public class BidDetailBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<BidDetail, BidDetailVO, BidDetail, BidDetailMapper> implements BidDetailBaseService<S, T, E> {

    @Autowired
    protected BidDetailMapper bidDetailMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveBidDetail(BidDetailDTO bidDetailDTO) {

        BidDetail bidDetail = bidDetailMapper.selectById(bidDetailDTO.getId());
        if (ObjectUtil.isNotNull(bidDetail)) {
            log.error("saveBidDetail() The BidDetail already exists");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        bidDetail = new BidDetail();
        BeanUtil.copyProperties(bidDetailDTO, bidDetail);
        bidDetail.setCreatedTime(DateUtil.current());
        bidDetail.setDeleted(0);

        return this.save(bidDetail);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateBidDetail(BidDetailDTO bidDetailDTO) {

        BidDetail bidDetail = this.getById(bidDetailDTO.getId());
        if (ObjectUtil.isNull(bidDetail)) {
            log.error("updateBidDetail() The BidDetail does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(bidDetailDTO, bidDetail);
        bidDetail.setUpdatedUser(bidDetailDTO.getUpdatedUser());
        bidDetail.setUpdatedTime(DateUtil.current());

        return this.updateById(bidDetail);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<BidDetail> bidDetailList) {

        bidDetailList.forEach(bidDetail -> {
            BidDetail bidDetailParam = new BidDetail();
            bidDetailParam.setId(bidDetail.getId());
            if (ObjectUtil.isNotNull(bidDetail.getId())) {
                bidDetail.setId(bidDetail.getId());
                bidDetail.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<BidDetail> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(BidDetail::getId, bidDetail.getId());
                update(bidDetail, lambdaUpdate);
            } else {
                bidDetail.setCreatedTime(DateUtil.current());
                bidDetail.setDeleted(0);
                save(bidDetail);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<BidDetailDTO> bidDetailDTOList) {

        List<BidDetail> bidDetailList = BeanUtils.copyProperties(bidDetailDTOList, BidDetail.class);
        return saveOrUpdateBatch(bidDetailList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteBidDetailLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        BidDetail bidDetail = this.getById(id);

        if (ObjectUtil.isNull(bidDetail)) {
            log.error("deleteBidDetail() The BidDetail does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }
        bidDetail.setDeleted(1);

        return this.updateById(bidDetail);
    }

    private List<BidDetail> queryBidDetailList(BidDetailFilterDTO bidDetailFilterDTO) {
        bidDetailFilterDTO.setDeleted(0);
        BidDetailQuery bidDetailQuery = BeanUtil.copyProperties(bidDetailFilterDTO, BidDetailQuery.class);

        return bidDetailMapper.queryBidDetail(bidDetailQuery);
    }

    @Override
    public List<BidDetailVO> queryBidDetailVOList(BidDetailFilterDTO bidDetailFilterDTO) {
        bidDetailFilterDTO.setDeleted(0);
        return this.objectConversion(queryBidDetailList(bidDetailFilterDTO));
    }

    @Override
    public PageVO<BidDetailVO> queryBidDetail(BidDetailFilterDTO bidDetailFilterDTO) {
        bidDetailFilterDTO.setDeleted(0);
        PageVO<BidDetailVO> pageVO = new PageVO<>();

        if (bidDetailFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(bidDetailFilterDTO.getCurrentPage(), bidDetailFilterDTO.getPageSize(),
                    bidDetailFilterDTO.getOrderType(), bidDetailFilterDTO.getOrderField());
        }

        List<BidDetail> bidDetailList = queryBidDetailList(bidDetailFilterDTO);

        PageInfo<BidDetail> pageInfo = new PageInfo<>(bidDetailList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(bidDetailList));

        return pageVO;
    }

    @Override
    public BidDetailVO queryBidDetailById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
