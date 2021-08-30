package com.xdcplus.biz.generator.impl;

import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.biz.mapper.BidAttachmentMapper;
import com.xdcplus.biz.common.pojo.entity.BidAttachment;
import com.xdcplus.biz.common.pojo.dto.BidAttachmentDTO;
import com.xdcplus.biz.common.pojo.dto.BidAttachmentFilterDTO;
import com.xdcplus.biz.common.pojo.vo.BidAttachmentVO;
import com.xdcplus.biz.common.pojo.query.BidAttachmentQuery;
import com.xdcplus.biz.generator.BidAttachmentBaseService;
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
 * 招标附件(BidAttachment)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:23:13
 */
public class BidAttachmentBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<BidAttachment, BidAttachmentVO, BidAttachment, BidAttachmentMapper> implements BidAttachmentBaseService<S, T, E> {

    @Autowired
    protected BidAttachmentMapper bidAttachmentMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveBidAttachment(BidAttachmentDTO bidAttachmentDTO) {

        BidAttachment bidAttachment = bidAttachmentMapper.selectById(bidAttachmentDTO.getId());
        if (ObjectUtil.isNotNull(bidAttachment)) {
            log.error("saveBidAttachment() The BidAttachment already exists");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        bidAttachment = new BidAttachment();
        BeanUtil.copyProperties(bidAttachmentDTO, bidAttachment);
        bidAttachment.setCreatedTime(DateUtil.current());
        bidAttachment.setDeleted(0);

        return this.save(bidAttachment);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateBidAttachment(BidAttachmentDTO bidAttachmentDTO) {

        BidAttachment bidAttachment = this.getById(bidAttachmentDTO.getId());
        if (ObjectUtil.isNull(bidAttachment)) {
            log.error("updateBidAttachment() The BidAttachment does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(bidAttachmentDTO, bidAttachment);
        bidAttachment.setUpdatedUser(bidAttachmentDTO.getUpdatedUser());
        bidAttachment.setUpdatedTime(DateUtil.current());

        return this.updateById(bidAttachment);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<BidAttachment> bidAttachmentList) {

        bidAttachmentList.forEach(bidAttachment -> {
            BidAttachment bidAttachmentParam = new BidAttachment();
            bidAttachmentParam.setId(bidAttachment.getId());
            if (ObjectUtil.isNotNull(bidAttachment.getId())) {
                bidAttachment.setId(bidAttachment.getId());
                bidAttachment.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<BidAttachment> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(BidAttachment::getId, bidAttachment.getId());
                update(bidAttachment, lambdaUpdate);
            } else {
                bidAttachment.setCreatedTime(DateUtil.current());
                bidAttachment.setDeleted(0);
                save(bidAttachment);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<BidAttachmentDTO> bidAttachmentDTOList) {

        List<BidAttachment> bidAttachmentList = BeanUtils.copyProperties(bidAttachmentDTOList, BidAttachment.class);
        return saveOrUpdateBatch(bidAttachmentList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteBidAttachmentLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        BidAttachment bidAttachment = this.getById(id);

        if (ObjectUtil.isNull(bidAttachment)) {
            log.error("deleteBidAttachment() The BidAttachment does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }
        bidAttachment.setDeleted(1);

        return this.updateById(bidAttachment);
    }

    private List<BidAttachment> queryBidAttachmentList(BidAttachmentFilterDTO bidAttachmentFilterDTO) {
        bidAttachmentFilterDTO.setDeleted(0);
        BidAttachmentQuery bidAttachmentQuery = BeanUtil.copyProperties(bidAttachmentFilterDTO, BidAttachmentQuery.class);

        return bidAttachmentMapper.queryBidAttachment(bidAttachmentQuery);
    }

    @Override
    public List<BidAttachmentVO> queryBidAttachmentVOList(BidAttachmentFilterDTO bidAttachmentFilterDTO) {
        bidAttachmentFilterDTO.setDeleted(0);
        return this.objectConversion(queryBidAttachmentList(bidAttachmentFilterDTO));
    }

    @Override
    public PageVO<BidAttachmentVO> queryBidAttachment(BidAttachmentFilterDTO bidAttachmentFilterDTO) {
        bidAttachmentFilterDTO.setDeleted(0);
        PageVO<BidAttachmentVO> pageVO = new PageVO<>();

        if (bidAttachmentFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(bidAttachmentFilterDTO.getCurrentPage(), bidAttachmentFilterDTO.getPageSize(),
                    bidAttachmentFilterDTO.getOrderType(), bidAttachmentFilterDTO.getOrderField());
        }

        List<BidAttachment> bidAttachmentList = queryBidAttachmentList(bidAttachmentFilterDTO);

        PageInfo<BidAttachment> pageInfo = new PageInfo<>(bidAttachmentList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(bidAttachmentList));

        return pageVO;
    }

    @Override
    public BidAttachmentVO queryBidAttachmentById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
