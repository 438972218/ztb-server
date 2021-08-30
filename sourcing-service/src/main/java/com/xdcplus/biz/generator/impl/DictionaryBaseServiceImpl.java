package com.xdcplus.biz.generator.impl;

import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.biz.mapper.DictionaryMapper;
import com.xdcplus.biz.common.pojo.entity.Dictionary;
import com.xdcplus.biz.common.pojo.dto.DictionaryDTO;
import com.xdcplus.biz.common.pojo.dto.DictionaryFilterDTO;
import com.xdcplus.biz.common.pojo.vo.DictionaryVO;
import com.xdcplus.biz.common.pojo.query.DictionaryQuery;
import com.xdcplus.biz.generator.DictionaryBaseService;
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
 * 字典管理(Dictionary)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-08-12 15:20:51
 */
public class DictionaryBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<Dictionary, DictionaryVO, Dictionary, DictionaryMapper> implements DictionaryBaseService<S, T, E> {

    @Autowired
    protected DictionaryMapper dictionaryMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveDictionary(DictionaryDTO dictionaryDTO) {

        Dictionary dictionary = dictionaryMapper.selectById(dictionaryDTO.getId());
        if (ObjectUtil.isNotNull(dictionary)) {
            log.error("saveDictionary() The Dictionary already exists");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        dictionary = new Dictionary();
        BeanUtil.copyProperties(dictionaryDTO, dictionary);
        dictionary.setCreatedTime(DateUtil.current());
        dictionary.setDeleted(0);

        return this.save(dictionary);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateDictionary(DictionaryDTO dictionaryDTO) {

        Dictionary dictionary = this.getById(dictionaryDTO.getId());
        if (ObjectUtil.isNull(dictionary)) {
            log.error("updateDictionary() The Dictionary does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(dictionaryDTO, dictionary);
        dictionary.setUpdatedUser(dictionaryDTO.getUpdatedUser());
        dictionary.setUpdatedTime(DateUtil.current());

        return this.updateById(dictionary);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<Dictionary> dictionaryList) {

        dictionaryList.forEach(dictionary -> {
            Dictionary dictionaryParam = new Dictionary();
            dictionaryParam.setId(dictionary.getId());
            if (ObjectUtil.isNotNull(dictionary.getId())) {
                dictionary.setId(dictionary.getId());
                dictionary.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<Dictionary> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(Dictionary::getId, dictionary.getId());
                update(dictionary, lambdaUpdate);
            } else {
                dictionary.setCreatedTime(DateUtil.current());
                dictionary.setDeleted(0);
                save(dictionary);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<DictionaryDTO> dictionaryDTOList) {

        List<Dictionary> dictionaryList = BeanUtils.copyProperties(dictionaryDTOList, Dictionary.class);
        return saveOrUpdateBatch(dictionaryList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteDictionaryLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        Dictionary dictionary = this.getById(id);

        if (ObjectUtil.isNull(dictionary)) {
            log.error("deleteDictionary() The Dictionary does not exist or has been deleted");
            throw new ZtbWebException(ResponseEnum.ERROR);
        }
        dictionary.setDeleted(1);

        return this.updateById(dictionary);
    }

    private List<Dictionary> queryDictionaryList(DictionaryFilterDTO dictionaryFilterDTO) {
        dictionaryFilterDTO.setDeleted(0);
        DictionaryQuery dictionaryQuery = BeanUtil.copyProperties(dictionaryFilterDTO, DictionaryQuery.class);

        return dictionaryMapper.queryDictionary(dictionaryQuery);
    }

    @Override
    public List<DictionaryVO> queryDictionaryVOList(DictionaryFilterDTO dictionaryFilterDTO) {
        dictionaryFilterDTO.setDeleted(0);
        return this.objectConversion(queryDictionaryList(dictionaryFilterDTO));
    }

    @Override
    public PageVO<DictionaryVO> queryDictionary(DictionaryFilterDTO dictionaryFilterDTO) {
        dictionaryFilterDTO.setDeleted(0);
        PageVO<DictionaryVO> pageVO = new PageVO<>();

        if (dictionaryFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(dictionaryFilterDTO.getCurrentPage(), dictionaryFilterDTO.getPageSize(),
                    dictionaryFilterDTO.getOrderType(), dictionaryFilterDTO.getOrderField());
        }

        List<Dictionary> dictionaryList = queryDictionaryList(dictionaryFilterDTO);

        PageInfo<Dictionary> pageInfo = new PageInfo<>(dictionaryList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(dictionaryList));

        return pageVO;
    }

    @Override
    public DictionaryVO queryDictionaryById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
