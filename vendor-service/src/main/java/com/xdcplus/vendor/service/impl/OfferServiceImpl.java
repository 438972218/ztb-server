package com.xdcplus.vendor.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.vendor.common.pojo.dto.OfferDTO;
import com.xdcplus.vendor.common.pojo.dto.OfferFilterDTO;
import com.xdcplus.vendor.common.pojo.entity.Offer;
import com.xdcplus.vendor.common.pojo.query.OfferQuery;
import com.xdcplus.vendor.common.pojo.vo.OfferVO;
import com.xdcplus.vendor.common.pojo.vo.VendorUserVO;
import com.xdcplus.vendor.mapper.OfferMapper;
import com.xdcplus.vendor.remote.service.FeignService;
import com.xdcplus.vendor.service.OfferService;
import com.xdcplus.vendor.service.VendorUserService;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.ztb.common.remote.domain.perm.vo.SysUserInfoVO;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.utils.PageableUtils;
import com.xdcplus.ztb.common.tool.utils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 出价记录 服务实现类
 *
 * @author Rong.Jia
 * @since 2021-08-17
 */
@Slf4j
@Service
public class OfferServiceImpl extends BaseServiceImpl<Offer, OfferVO, Offer, OfferMapper> implements OfferService {

    @Autowired
    private OfferMapper offerMapper;

    @Autowired
    private FeignService feignService;

    @Autowired
    private VendorUserService vendorUserService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public OfferVO saveOffer(OfferDTO offerDTO) {

        Offer offer = new Offer();
        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreNullValue(Boolean.TRUE);
        BeanUtil.copyProperties(offerDTO, offer, copyOptions);
        offer.setCreatedTime(DateUtil.current());
        offer.setOfferTime(DateUtil.current());

        SysUserInfoVO sysUserInfoVO = feignService.findUserInfoByUserName(offerDTO.getOfferUser());
        if (ObjectUtil.isNotNull(sysUserInfoVO)) {
            VendorUserVO vendorUserVO = vendorUserService.findVendorUserByUserId(sysUserInfoVO.getId());
            if (ObjectUtil.isNotNull(vendorUserVO)) {
                offer.setVendorId(vendorUserVO.getVendorId());
            }
        }

        this.save(offer);

        return this.objectConversion(offer);
    }

    @Override
    public OfferVO findOne(Long offerId) {

        Assert.notNull(offerId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(offerId));
    }

    @Override
    public Integer getRanking(Long offerId, Boolean positive) {

        Assert.notNull(offerId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        Offer offer = this.getById(offerId);
        if (ObjectUtil.isNotNull(offer)) {
            List<Offer> offerList = offerMapper.findOfferByOfferGoods(offer.getOfferGoods());

            if (CollectionUtil.isNotEmpty(offerList)) {
                AtomicInteger index = new AtomicInteger(NumberConstant.ZERO);
                offerList.stream().map(a -> Convert.toDouble(a.getMoney()))
                        .collect(Collectors.toSet()).stream()
                        .sorted(positive ? Comparator.naturalOrder() : Comparator.reverseOrder())
                        .filter(s -> {
                    index.getAndIncrement();
                    return Validator.equal(s, Convert.toDouble(offer.getMoney()));
                }).findFirst();

                return index.get();
            }
        }

        return NumberConstant.ONE;
    }

    @Override
    public List<OfferVO> findOfferByOfferGoods(String offerGoods, Boolean positive) {

        if (StrUtil.isNotBlank(offerGoods)) {

            List<Offer> offerList = offerMapper.findOfferByOfferGoods(offerGoods);
            if (CollectionUtil.isNotEmpty(offerList)) {
                List<Double> rankingList = offerList.stream().map(a -> Convert.toDouble(a.getMoney()))
                        .collect(Collectors.toSet()).stream()
                        .sorted(positive ? Comparator.naturalOrder() : Comparator.reverseOrder())
                        .collect(Collectors.toList());

                List<OfferVO> offerVOList = new ArrayList<>();
                for (int i = 1; i <= rankingList.size(); i++) {
                    for (Offer offer : offerList) {
                        if (Validator.equal(rankingList.get(i), Convert.toDouble(offer.getMoney()))) {
                            OfferVO offerVO = this.objectConversion(offer);
                            if (ObjectUtil.isNotNull(offerVO)) {
                                offerVO.setRanking(i);
                                offerVOList.add(offerVO);
                            }
                        }
                    }
                }

                return offerVOList;
            }
        }

        return null;
    }

    @Override
    public PageVO<OfferVO> findOffer(OfferFilterDTO filterDTO) {

        PageVO<OfferVO> pageVO = new PageVO<>();

        if (filterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(filterDTO);
        }

        OfferQuery offerQuery = BeanUtil.copyProperties(filterDTO, OfferQuery.class);
        List<Offer> offerList = offerMapper.findOffer(offerQuery);
        PageInfo<Offer> pageInfo = new PageInfo<>(offerList);

        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(pageInfo.getList()));

        return pageVO;
    }


}
