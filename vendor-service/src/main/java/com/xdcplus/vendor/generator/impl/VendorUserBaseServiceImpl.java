package com.xdcplus.vendor.generator.impl;

import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.vendor.mapper.VendorUserMapper;
import com.xdcplus.vendor.common.pojo.entity.VendorUser;
import com.xdcplus.vendor.common.pojo.dto.VendorUserDTO;
import com.xdcplus.vendor.common.pojo.dto.VendorUserFilterDTO;
import com.xdcplus.vendor.common.pojo.vo.VendorUserVO;
import com.xdcplus.vendor.common.pojo.query.VendorUserQuery;
import com.xdcplus.vendor.generator.VendorUserBaseService;
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
 * 供应商用户(VendorUser)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-08-23 17:48:28
 */
public class VendorUserBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<VendorUser, VendorUserVO, VendorUser, VendorUserMapper> implements VendorUserBaseService<S, T, E> {

    @Autowired
    protected VendorUserMapper vendorUserMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendorUser(VendorUserDTO vendorUserDTO) {

        VendorUser vendorUser = vendorUserMapper.selectById(vendorUserDTO.getId());
        if (ObjectUtil.isNotNull(vendorUser)) {
            log.error("saveVendorUser() The VendorUser already exists");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        vendorUser = new VendorUser();
        BeanUtil.copyProperties(vendorUserDTO, vendorUser);

        return this.save(vendorUser);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendorUser(VendorUserDTO vendorUserDTO) {

        VendorUser vendorUser = this.getById(vendorUserDTO.getId());
        if (ObjectUtil.isNull(vendorUser)) {
            log.error("updateVendorUser() The VendorUser does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorUserDTO, vendorUser);

        return this.updateById(vendorUser);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<VendorUser> vendorUserList) {

        vendorUserList.forEach(vendorUser -> {
            VendorUser vendorUserParam = new VendorUser();
            vendorUserParam.setId(vendorUser.getId());
            if (ObjectUtil.isNotNull(vendorUser.getId())) {
                vendorUser.setId(vendorUser.getId());
                LambdaUpdateWrapper<VendorUser> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(VendorUser::getId, vendorUser.getId());
                update(vendorUser, lambdaUpdate);
            } else {
                save(vendorUser);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorUserDTO> vendorUserDTOList) {

        List<VendorUser> vendorUserList = BeanUtils.copyProperties(vendorUserDTOList, VendorUser.class);
        return saveOrUpdateBatch(vendorUserList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorUserLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        VendorUser vendorUser = this.getById(id);

        if (ObjectUtil.isNull(vendorUser)) {
            log.error("deleteVendorUser() The VendorUser does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        return this.updateById(vendorUser);
    }

    private List<VendorUser> queryVendorUserList(VendorUserFilterDTO vendorUserFilterDTO) {
        VendorUserQuery vendorUserQuery = BeanUtil.copyProperties(vendorUserFilterDTO, VendorUserQuery.class);

        return vendorUserMapper.queryVendorUser(vendorUserQuery);
    }

    @Override
    public List<VendorUserVO> queryVendorUserVOList(VendorUserFilterDTO vendorUserFilterDTO) {
        return this.objectConversion(queryVendorUserList(vendorUserFilterDTO));
    }

    @Override
    public PageVO<VendorUserVO> queryVendorUser(VendorUserFilterDTO vendorUserFilterDTO) {
        PageVO<VendorUserVO> pageVO = new PageVO<>();

        if (vendorUserFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorUserFilterDTO.getCurrentPage(), vendorUserFilterDTO.getPageSize(),
                    vendorUserFilterDTO.getOrderType(), vendorUserFilterDTO.getOrderField());
        }

        List<VendorUser> vendorUserList = queryVendorUserList(vendorUserFilterDTO);

        PageInfo<VendorUser> pageInfo = new PageInfo<>(vendorUserList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorUserList));

        return pageVO;
    }

    @Override
    public VendorUserVO queryVendorUserById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
