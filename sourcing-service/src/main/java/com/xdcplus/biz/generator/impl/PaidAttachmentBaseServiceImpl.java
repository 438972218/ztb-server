package com.xdcplus.biz.generator.impl;

import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.biz.mapper.PaidAttachmentMapper;
import com.xdcplus.biz.common.pojo.entity.PaidAttachment;
import com.xdcplus.biz.common.pojo.dto.PaidAttachmentDTO;
import com.xdcplus.biz.common.pojo.dto.PaidAttachmentFilterDTO;
import com.xdcplus.biz.common.pojo.vo.PaidAttachmentVO;
import com.xdcplus.biz.common.pojo.query.PaidAttachmentQuery;
import com.xdcplus.biz.generator.PaidAttachmentBaseService;
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
 * 竞价单附件(PaidAttachment)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-08-16 14:01:57
 */
public class PaidAttachmentBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<PaidAttachment, PaidAttachmentVO, PaidAttachment, PaidAttachmentMapper> implements PaidAttachmentBaseService<S, T, E> {

    @Autowired
    protected PaidAttachmentMapper paidAttachmentMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean savePaidAttachment(PaidAttachmentDTO paidAttachmentDTO) {

        PaidAttachment paidAttachment = paidAttachmentMapper.selectById(paidAttachmentDTO.getId());
        if (ObjectUtil.isNotNull(paidAttachment)) {
            log.error("savePaidAttachment() The PaidAttachment already exists");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        paidAttachment = new PaidAttachment();
        BeanUtil.copyProperties(paidAttachmentDTO, paidAttachment);
        paidAttachment.setCreatedTime(DateUtil.current());
        paidAttachment.setDeleted(0);

        return this.save(paidAttachment);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updatePaidAttachment(PaidAttachmentDTO paidAttachmentDTO) {

        PaidAttachment paidAttachment = this.getById(paidAttachmentDTO.getId());
        if (ObjectUtil.isNull(paidAttachment)) {
            log.error("updatePaidAttachment() The PaidAttachment does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(paidAttachmentDTO, paidAttachment);
        paidAttachment.setUpdatedUser(paidAttachmentDTO.getUpdatedUser());
        paidAttachment.setUpdatedTime(DateUtil.current());

        return this.updateById(paidAttachment);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<PaidAttachment> paidAttachmentList) {

        paidAttachmentList.forEach(paidAttachment -> {
            PaidAttachment paidAttachmentParam = new PaidAttachment();
            paidAttachmentParam.setId(paidAttachment.getId());
            if (ObjectUtil.isNotNull(paidAttachment.getId())) {
                paidAttachment.setId(paidAttachment.getId());
                paidAttachment.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<PaidAttachment> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(PaidAttachment::getId, paidAttachment.getId());
                update(paidAttachment, lambdaUpdate);
            } else {
                paidAttachment.setCreatedTime(DateUtil.current());
                paidAttachment.setDeleted(0);
                save(paidAttachment);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<PaidAttachmentDTO> paidAttachmentDTOList) {

        List<PaidAttachment> paidAttachmentList = BeanUtils.copyProperties(paidAttachmentDTOList, PaidAttachment.class);
        return saveOrUpdateBatch(paidAttachmentList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deletePaidAttachmentLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        PaidAttachment paidAttachment = this.getById(id);

        if (ObjectUtil.isNull(paidAttachment)) {
            log.error("deletePaidAttachment() The PaidAttachment does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }
        paidAttachment.setDeleted(1);

        return this.updateById(paidAttachment);
    }

    private List<PaidAttachment> queryPaidAttachmentList(PaidAttachmentFilterDTO paidAttachmentFilterDTO) {
        paidAttachmentFilterDTO.setDeleted(0);
        PaidAttachmentQuery paidAttachmentQuery = BeanUtil.copyProperties(paidAttachmentFilterDTO, PaidAttachmentQuery.class);

        return paidAttachmentMapper.queryPaidAttachment(paidAttachmentQuery);
    }

    @Override
    public List<PaidAttachmentVO> queryPaidAttachmentVOList(PaidAttachmentFilterDTO paidAttachmentFilterDTO) {
        paidAttachmentFilterDTO.setDeleted(0);
        return this.objectConversion(queryPaidAttachmentList(paidAttachmentFilterDTO));
    }

    @Override
    public PageVO<PaidAttachmentVO> queryPaidAttachment(PaidAttachmentFilterDTO paidAttachmentFilterDTO) {
        paidAttachmentFilterDTO.setDeleted(0);
        PageVO<PaidAttachmentVO> pageVO = new PageVO<>();

        if (paidAttachmentFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(paidAttachmentFilterDTO.getCurrentPage(), paidAttachmentFilterDTO.getPageSize(),
                    paidAttachmentFilterDTO.getOrderType(), paidAttachmentFilterDTO.getOrderField());
        }

        List<PaidAttachment> paidAttachmentList = queryPaidAttachmentList(paidAttachmentFilterDTO);

        PageInfo<PaidAttachment> pageInfo = new PageInfo<>(paidAttachmentList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(paidAttachmentList));

        return pageVO;
    }

    @Override
    public PaidAttachmentVO queryPaidAttachmentById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
