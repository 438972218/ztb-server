package com.xdcplus.biz.generator.impl;

import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.biz.mapper.PaidVendorMapper;
import com.xdcplus.biz.common.pojo.entity.PaidVendor;
import com.xdcplus.biz.common.pojo.dto.PaidVendorDTO;
import com.xdcplus.biz.common.pojo.dto.PaidVendorFilterDTO;
import com.xdcplus.biz.common.pojo.vo.PaidVendorVO;
import com.xdcplus.biz.common.pojo.query.PaidVendorQuery;
import com.xdcplus.biz.generator.PaidVendorBaseService;
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
 * 竞价供应商(PaidVendor)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-08-16 14:02:05
 */
public class PaidVendorBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<PaidVendor, PaidVendorVO, PaidVendor, PaidVendorMapper> implements PaidVendorBaseService<S, T, E> {

    @Autowired
    protected PaidVendorMapper paidVendorMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean savePaidVendor(PaidVendorDTO paidVendorDTO) {

        PaidVendor paidVendor = paidVendorMapper.selectById(paidVendorDTO.getId());
        if (ObjectUtil.isNotNull(paidVendor)) {
            log.error("savePaidVendor() The PaidVendor already exists");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        paidVendor = new PaidVendor();
        BeanUtil.copyProperties(paidVendorDTO, paidVendor);
        paidVendor.setCreatedTime(DateUtil.current());
        paidVendor.setDeleted(0);

        return this.save(paidVendor);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updatePaidVendor(PaidVendorDTO paidVendorDTO) {

        PaidVendor paidVendor = this.getById(paidVendorDTO.getId());
        if (ObjectUtil.isNull(paidVendor)) {
            log.error("updatePaidVendor() The PaidVendor does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(paidVendorDTO, paidVendor);
        paidVendor.setUpdatedUser(paidVendorDTO.getUpdatedUser());
        paidVendor.setUpdatedTime(DateUtil.current());

        return this.updateById(paidVendor);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<PaidVendor> paidVendorList) {

        paidVendorList.forEach(paidVendor -> {
            PaidVendor paidVendorParam = new PaidVendor();
            paidVendorParam.setId(paidVendor.getId());
            if (ObjectUtil.isNotNull(paidVendor.getId())) {
                paidVendor.setId(paidVendor.getId());
                paidVendor.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<PaidVendor> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(PaidVendor::getId, paidVendor.getId());
                update(paidVendor, lambdaUpdate);
            } else {
                paidVendor.setCreatedTime(DateUtil.current());
                paidVendor.setDeleted(0);
                save(paidVendor);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<PaidVendorDTO> paidVendorDTOList) {

        List<PaidVendor> paidVendorList = BeanUtils.copyProperties(paidVendorDTOList, PaidVendor.class);
        return saveOrUpdateBatch(paidVendorList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deletePaidVendorLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        PaidVendor paidVendor = this.getById(id);

        if (ObjectUtil.isNull(paidVendor)) {
            log.error("deletePaidVendor() The PaidVendor does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }
        paidVendor.setDeleted(1);

        return this.updateById(paidVendor);
    }

    private List<PaidVendor> queryPaidVendorList(PaidVendorFilterDTO paidVendorFilterDTO) {
        paidVendorFilterDTO.setDeleted(0);
        PaidVendorQuery paidVendorQuery = BeanUtil.copyProperties(paidVendorFilterDTO, PaidVendorQuery.class);

        return paidVendorMapper.queryPaidVendor(paidVendorQuery);
    }

    @Override
    public List<PaidVendorVO> queryPaidVendorVOList(PaidVendorFilterDTO paidVendorFilterDTO) {
        paidVendorFilterDTO.setDeleted(0);
        return this.objectConversion(queryPaidVendorList(paidVendorFilterDTO));
    }

    @Override
    public PageVO<PaidVendorVO> queryPaidVendor(PaidVendorFilterDTO paidVendorFilterDTO) {
        paidVendorFilterDTO.setDeleted(0);
        PageVO<PaidVendorVO> pageVO = new PageVO<>();

        if (paidVendorFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(paidVendorFilterDTO.getCurrentPage(), paidVendorFilterDTO.getPageSize(),
                    paidVendorFilterDTO.getOrderType(), paidVendorFilterDTO.getOrderField());
        }

        List<PaidVendor> paidVendorList = queryPaidVendorList(paidVendorFilterDTO);

        PageInfo<PaidVendor> pageInfo = new PageInfo<>(paidVendorList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(paidVendorList));

        return pageVO;
    }

    @Override
    public PaidVendorVO queryPaidVendorById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
