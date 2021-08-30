package com.xdcplus.workflow.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdcplus.workflow.common.pojo.entity.MailRelation;
import com.xdcplus.workflow.mapper.MailRelationMapper;
import com.xdcplus.workflow.service.MailRelationService;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 邮件关系服务实现
 *
 * @author Rong.Jia
 * @date 2021/08/10 15:25:06
 */
@Slf4j
@Service
public class MailRelationServiceImpl extends ServiceImpl<MailRelationMapper, MailRelation> implements MailRelationService {

    @Autowired
    private MailRelationMapper relationMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long syncRelation(Long deliveryId, Long templateId) {

        MailRelation mailRelation = relationMapper.findRelation(deliveryId, templateId);
        if (ObjectUtil.isNull(mailRelation)) {
            mailRelation = new MailRelation();
        }

        mailRelation.setDeliveryId(deliveryId);
        mailRelation.setTemplateId(templateId);

        if (Validator.isNull(mailRelation.getId())) {
            this.save(mailRelation);
        } else {
            this.updateById(mailRelation);
        }

        return mailRelation.getId();
    }

    @Override
    public MailRelation findRelationByDeliveryId(Long deliveryId) {

        Assert.notNull(deliveryId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return relationMapper.findRelation(deliveryId, null);
    }

    @Override
    public MailRelation findRelationByTemplateId(Long templateId) {

        Assert.notNull(templateId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return relationMapper.findRelation(null, templateId);
    }

    @Override
    public MailRelation findOne(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.getById(id);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteRelation(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        if (ObjectUtil.isNotNull(this.getById(id))) {
            return this.removeById(id);
        }

        return Boolean.FALSE;
    }
}
