package com.xdcplus.biz.generator.impl;

import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.biz.mapper.BidSheetMapper;
import com.xdcplus.biz.common.pojo.entity.BidSheet;
import com.xdcplus.biz.common.pojo.dto.BidSheetDTO;
import com.xdcplus.biz.common.pojo.dto.BidSheetFilterDTO;
import com.xdcplus.biz.common.pojo.vo.BidSheetVO;
import com.xdcplus.biz.common.pojo.query.BidSheetQuery;
import com.xdcplus.biz.generator.BidSheetBaseService;
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
 * bid招标单(BidSheet)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:24:02
 */
public class BidSheetBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<BidSheet, BidSheetVO, BidSheet, BidSheetMapper> implements BidSheetBaseService<S, T, E> {

    @Autowired
    protected BidSheetMapper bidSheetMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveBidSheet(BidSheetDTO bidSheetDTO) {

        BidSheet bidSheet = bidSheetMapper.selectById(bidSheetDTO.getId());
        if (ObjectUtil.isNotNull(bidSheet)) {
            log.error("saveBidSheet() The BidSheet already exists");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        bidSheet = new BidSheet();
        BeanUtil.copyProperties(bidSheetDTO, bidSheet);
        bidSheet.setCreatedTime(DateUtil.current());
        bidSheet.setDeleted(0);

        return this.save(bidSheet);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateBidSheet(BidSheetDTO bidSheetDTO) {

        BidSheet bidSheet = this.getById(bidSheetDTO.getId());
        if (ObjectUtil.isNull(bidSheet)) {
            log.error("updateBidSheet() The BidSheet does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(bidSheetDTO, bidSheet);
        bidSheet.setUpdatedUser(bidSheetDTO.getUpdatedUser());
        bidSheet.setUpdatedTime(DateUtil.current());

        return this.updateById(bidSheet);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<BidSheet> bidSheetList) {

        bidSheetList.forEach(bidSheet -> {
            BidSheet bidSheetParam = new BidSheet();
            bidSheetParam.setId(bidSheet.getId());
            if (ObjectUtil.isNotNull(bidSheet.getId())) {
                bidSheet.setId(bidSheet.getId());
                bidSheet.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<BidSheet> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(BidSheet::getId, bidSheet.getId());
                update(bidSheet, lambdaUpdate);
            } else {
                bidSheet.setCreatedTime(DateUtil.current());
                bidSheet.setDeleted(0);
                save(bidSheet);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<BidSheetDTO> bidSheetDTOList) {

        List<BidSheet> bidSheetList = BeanUtils.copyProperties(bidSheetDTOList, BidSheet.class);
        return saveOrUpdateBatch(bidSheetList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteBidSheetLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        BidSheet bidSheet = this.getById(id);

        if (ObjectUtil.isNull(bidSheet)) {
            log.error("deleteBidSheet() The BidSheet does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }
        bidSheet.setDeleted(1);

        return this.updateById(bidSheet);
    }

    protected List<BidSheet> queryBidSheetList(BidSheetFilterDTO bidSheetFilterDTO) {
        bidSheetFilterDTO.setDeleted(0);
        BidSheetQuery bidSheetQuery = BeanUtil.copyProperties(bidSheetFilterDTO, BidSheetQuery.class);

        return bidSheetMapper.queryBidSheet(bidSheetQuery);
    }

    @Override
    public List<BidSheetVO> queryBidSheetVOList(BidSheetFilterDTO bidSheetFilterDTO) {
        bidSheetFilterDTO.setDeleted(0);
        return this.objectConversion(queryBidSheetList(bidSheetFilterDTO));
    }

    @Override
    public PageVO<BidSheetVO> queryBidSheet(BidSheetFilterDTO bidSheetFilterDTO) {
        bidSheetFilterDTO.setDeleted(0);
        PageVO<BidSheetVO> pageVO = new PageVO<>();

        if (bidSheetFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(bidSheetFilterDTO.getCurrentPage(), bidSheetFilterDTO.getPageSize(),
                    bidSheetFilterDTO.getOrderType(), bidSheetFilterDTO.getOrderField());
        }

        List<BidSheet> bidSheetList = queryBidSheetList(bidSheetFilterDTO);

        PageInfo<BidSheet> pageInfo = new PageInfo<>(bidSheetList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(bidSheetList));

        return pageVO;
    }

    @Override
    public BidSheetVO queryBidSheetById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
