package com.xdcplus.biz.generator.impl;

import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.biz.mapper.BidDetailTemplateMapper;
import com.xdcplus.biz.common.pojo.entity.BidDetailTemplate;
import com.xdcplus.biz.common.pojo.dto.BidDetailTemplateDTO;
import com.xdcplus.biz.common.pojo.dto.BidDetailTemplateFilterDTO;
import com.xdcplus.biz.common.pojo.vo.BidDetailTemplateVO;
import com.xdcplus.biz.common.pojo.query.BidDetailTemplateQuery;
import com.xdcplus.biz.generator.BidDetailTemplateBaseService;
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
 * 内容模板(BidDetailTemplate)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:23:17
 */
public class BidDetailTemplateBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<BidDetailTemplate, BidDetailTemplateVO, BidDetailTemplate, BidDetailTemplateMapper> implements BidDetailTemplateBaseService<S, T, E> {

    @Autowired
    protected BidDetailTemplateMapper bidDetailTemplateMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveBidDetailTemplate(BidDetailTemplateDTO bidDetailTemplateDTO) {

        BidDetailTemplate bidDetailTemplate = bidDetailTemplateMapper.selectById(bidDetailTemplateDTO.getId());
        if (ObjectUtil.isNotNull(bidDetailTemplate)) {
            log.error("saveBidDetailTemplate() The BidDetailTemplate already exists");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        bidDetailTemplate = new BidDetailTemplate();
        BeanUtil.copyProperties(bidDetailTemplateDTO, bidDetailTemplate);
        bidDetailTemplate.setCreatedTime(DateUtil.current());
        bidDetailTemplate.setDeleted(0);

        return this.save(bidDetailTemplate);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateBidDetailTemplate(BidDetailTemplateDTO bidDetailTemplateDTO) {

        BidDetailTemplate bidDetailTemplate = this.getById(bidDetailTemplateDTO.getId());
        if (ObjectUtil.isNull(bidDetailTemplate)) {
            log.error("updateBidDetailTemplate() The BidDetailTemplate does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(bidDetailTemplateDTO, bidDetailTemplate);
        bidDetailTemplate.setUpdatedUser(bidDetailTemplateDTO.getUpdatedUser());
        bidDetailTemplate.setUpdatedTime(DateUtil.current());

        return this.updateById(bidDetailTemplate);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<BidDetailTemplate> bidDetailTemplateList) {

        bidDetailTemplateList.forEach(bidDetailTemplate -> {
            BidDetailTemplate bidDetailTemplateParam = new BidDetailTemplate();
            bidDetailTemplateParam.setId(bidDetailTemplate.getId());
            if (ObjectUtil.isNotNull(bidDetailTemplate.getId())) {
                bidDetailTemplate.setId(bidDetailTemplate.getId());
                bidDetailTemplate.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<BidDetailTemplate> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(BidDetailTemplate::getId, bidDetailTemplate.getId());
                update(bidDetailTemplate, lambdaUpdate);
            } else {
                bidDetailTemplate.setCreatedTime(DateUtil.current());
                bidDetailTemplate.setDeleted(0);
                save(bidDetailTemplate);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<BidDetailTemplateDTO> bidDetailTemplateDTOList) {

        List<BidDetailTemplate> bidDetailTemplateList = BeanUtils.copyProperties(bidDetailTemplateDTOList, BidDetailTemplate.class);
        return saveOrUpdateBatch(bidDetailTemplateList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteBidDetailTemplateLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        BidDetailTemplate bidDetailTemplate = this.getById(id);

        if (ObjectUtil.isNull(bidDetailTemplate)) {
            log.error("deleteBidDetailTemplate() The BidDetailTemplate does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }
        bidDetailTemplate.setDeleted(1);

        return this.updateById(bidDetailTemplate);
    }

    private List<BidDetailTemplate> queryBidDetailTemplateList(BidDetailTemplateFilterDTO bidDetailTemplateFilterDTO) {
        bidDetailTemplateFilterDTO.setDeleted(0);
        BidDetailTemplateQuery bidDetailTemplateQuery = BeanUtil.copyProperties(bidDetailTemplateFilterDTO, BidDetailTemplateQuery.class);

        return bidDetailTemplateMapper.queryBidDetailTemplate(bidDetailTemplateQuery);
    }

    @Override
    public List<BidDetailTemplateVO> queryBidDetailTemplateVOList(BidDetailTemplateFilterDTO bidDetailTemplateFilterDTO) {
        bidDetailTemplateFilterDTO.setDeleted(0);
        return this.objectConversion(queryBidDetailTemplateList(bidDetailTemplateFilterDTO));
    }

    @Override
    public PageVO<BidDetailTemplateVO> queryBidDetailTemplate(BidDetailTemplateFilterDTO bidDetailTemplateFilterDTO) {
        bidDetailTemplateFilterDTO.setDeleted(0);
        PageVO<BidDetailTemplateVO> pageVO = new PageVO<>();

        if (bidDetailTemplateFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(bidDetailTemplateFilterDTO.getCurrentPage(), bidDetailTemplateFilterDTO.getPageSize(),
                    bidDetailTemplateFilterDTO.getOrderType(), bidDetailTemplateFilterDTO.getOrderField());
        }

        List<BidDetailTemplate> bidDetailTemplateList = queryBidDetailTemplateList(bidDetailTemplateFilterDTO);

        PageInfo<BidDetailTemplate> pageInfo = new PageInfo<>(bidDetailTemplateList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(bidDetailTemplateList));

        return pageVO;
    }

    @Override
    public BidDetailTemplateVO queryBidDetailTemplateById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
