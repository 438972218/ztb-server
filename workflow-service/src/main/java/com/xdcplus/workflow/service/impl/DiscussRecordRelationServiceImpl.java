package com.xdcplus.workflow.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdcplus.workflow.common.pojo.entity.DiscussRecordRelation;
import com.xdcplus.workflow.mapper.DiscussRecordRelationMapper;
import com.xdcplus.workflow.service.DiscussRecordRelationService;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 讨论记录关联信息服务实现类
 *
 * @author Rong.Jia
 * @since 2021-08-18
 */
@Slf4j
@Service
public class DiscussRecordRelationServiceImpl extends ServiceImpl<DiscussRecordRelationMapper, DiscussRecordRelation> implements DiscussRecordRelationService {

    @Autowired
    private DiscussRecordRelationMapper discussRecordRelationMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveRelation(Long recordId, List<String> toUsers) {

        if (CollectionUtil.isNotEmpty(toUsers)) {
            List<DiscussRecordRelation> recordRelationList = toUsers.stream().map(a -> {
                DiscussRecordRelation discussRecordRelation = new DiscussRecordRelation();
                discussRecordRelation.setToUser(a);
                discussRecordRelation.setRecordId(recordId);
                return discussRecordRelation;
            }).collect(Collectors.toList());

            this.saveBatch(recordRelationList);
        }
    }

    @Override
    public List<DiscussRecordRelation> findRelation(Long recordId) {

        Assert.notNull(recordId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return discussRecordRelationMapper.findRelation(recordId);
    }

}
