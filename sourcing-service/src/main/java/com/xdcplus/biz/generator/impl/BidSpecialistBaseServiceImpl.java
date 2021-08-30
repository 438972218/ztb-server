package com.xdcplus.biz.generator.impl;

import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.biz.mapper.BidSpecialistMapper;
import com.xdcplus.biz.common.pojo.entity.BidSpecialist;
import com.xdcplus.biz.common.pojo.dto.BidSpecialistDTO;
import com.xdcplus.biz.common.pojo.dto.BidSpecialistFilterDTO;
import com.xdcplus.biz.common.pojo.vo.BidSpecialistVO;
import com.xdcplus.biz.common.pojo.query.BidSpecialistQuery;
import com.xdcplus.biz.generator.BidSpecialistBaseService;
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
 * 专家(BidSpecialist)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-08-24 16:22:58
 */
public class BidSpecialistBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<BidSpecialist, BidSpecialistVO, BidSpecialist, BidSpecialistMapper> implements BidSpecialistBaseService<S, T, E> {

    @Autowired
    protected BidSpecialistMapper bidSpecialistMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveBidSpecialist(BidSpecialistDTO bidSpecialistDTO) {

        BidSpecialist bidSpecialist = bidSpecialistMapper.selectById(bidSpecialistDTO.getId());
        if (ObjectUtil.isNotNull(bidSpecialist)) {
            log.error("saveBidSpecialist() The BidSpecialist already exists");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        bidSpecialist = new BidSpecialist();
        BeanUtil.copyProperties(bidSpecialistDTO, bidSpecialist);
        bidSpecialist.setCreatedTime(DateUtil.current());
        bidSpecialist.setDeleted(0);

        return this.save(bidSpecialist);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateBidSpecialist(BidSpecialistDTO bidSpecialistDTO) {

        BidSpecialist bidSpecialist = this.getById(bidSpecialistDTO.getId());
        if (ObjectUtil.isNull(bidSpecialist)) {
            log.error("updateBidSpecialist() The BidSpecialist does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(bidSpecialistDTO, bidSpecialist);
        bidSpecialist.setUpdatedUser(bidSpecialistDTO.getUpdatedUser());
        bidSpecialist.setUpdatedTime(DateUtil.current());

        return this.updateById(bidSpecialist);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<BidSpecialist> bidSpecialistList) {

        bidSpecialistList.forEach(bidSpecialist -> {
            BidSpecialist bidSpecialistParam = new BidSpecialist();
            bidSpecialistParam.setId(bidSpecialist.getId());
            if (ObjectUtil.isNotNull(bidSpecialist.getId())) {
                bidSpecialist.setId(bidSpecialist.getId());
                bidSpecialist.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<BidSpecialist> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(BidSpecialist::getId, bidSpecialist.getId());
                update(bidSpecialist, lambdaUpdate);
            } else {
                bidSpecialist.setCreatedTime(DateUtil.current());
                bidSpecialist.setDeleted(0);
                save(bidSpecialist);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<BidSpecialistDTO> bidSpecialistDTOList) {

        List<BidSpecialist> bidSpecialistList = BeanUtils.copyProperties(bidSpecialistDTOList, BidSpecialist.class);
        return saveOrUpdateBatch(bidSpecialistList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteBidSpecialistLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        BidSpecialist bidSpecialist = this.getById(id);

        if (ObjectUtil.isNull(bidSpecialist)) {
            log.error("deleteBidSpecialist() The BidSpecialist does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }
        bidSpecialist.setDeleted(1);

        return this.updateById(bidSpecialist);
    }

    private List<BidSpecialist> queryBidSpecialistList(BidSpecialistFilterDTO bidSpecialistFilterDTO) {
        bidSpecialistFilterDTO.setDeleted(0);
        BidSpecialistQuery bidSpecialistQuery = BeanUtil.copyProperties(bidSpecialistFilterDTO, BidSpecialistQuery.class);

        return bidSpecialistMapper.queryBidSpecialist(bidSpecialistQuery);
    }

    @Override
    public List<BidSpecialistVO> queryBidSpecialistVOList(BidSpecialistFilterDTO bidSpecialistFilterDTO) {
        bidSpecialistFilterDTO.setDeleted(0);
        return this.objectConversion(queryBidSpecialistList(bidSpecialistFilterDTO));
    }

    @Override
    public PageVO<BidSpecialistVO> queryBidSpecialist(BidSpecialistFilterDTO bidSpecialistFilterDTO) {
        bidSpecialistFilterDTO.setDeleted(0);
        PageVO<BidSpecialistVO> pageVO = new PageVO<>();

        if (bidSpecialistFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(bidSpecialistFilterDTO.getCurrentPage(), bidSpecialistFilterDTO.getPageSize(),
                    bidSpecialistFilterDTO.getOrderType(), bidSpecialistFilterDTO.getOrderField());
        }

        List<BidSpecialist> bidSpecialistList = queryBidSpecialistList(bidSpecialistFilterDTO);

        PageInfo<BidSpecialist> pageInfo = new PageInfo<>(bidSpecialistList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(bidSpecialistList));

        return pageVO;
    }

    @Override
    public BidSpecialistVO queryBidSpecialistById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
