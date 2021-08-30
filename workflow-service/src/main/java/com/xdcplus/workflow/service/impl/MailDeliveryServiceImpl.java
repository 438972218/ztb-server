package com.xdcplus.workflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.xdcplus.workflow.common.pojo.bean.MailBean;
import com.xdcplus.workflow.common.pojo.bo.MailDeliveryBO;
import com.xdcplus.workflow.common.pojo.dto.*;
import com.xdcplus.workflow.common.pojo.entity.MailDelivery;
import com.xdcplus.workflow.common.pojo.query.MailDeliveryQuery;
import com.xdcplus.workflow.common.pojo.vo.MailDeliveryVO;
import com.xdcplus.workflow.common.pojo.vo.MailTemplateVO;
import com.xdcplus.workflow.common.utils.MailUtils;
import com.xdcplus.workflow.mapper.MailDeliveryMapper;
import com.xdcplus.workflow.service.*;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.utils.PageableUtils;
import com.xdcplus.ztb.common.tool.utils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 邮件发送点信息 服务实现类
 *
 * @author Rong.Jia
 * @since 2021-08-10
 */
@Slf4j
@Service
public class MailDeliveryServiceImpl extends BaseServiceImpl<MailDeliveryBO, MailDeliveryVO, MailDelivery, MailDeliveryMapper> implements MailDeliveryService {

    @Autowired
    private MailDeliveryMapper mailDeliveryMapper;

    @Autowired
    private MailTemplateService mailTemplateService;

    @Autowired
    private MailRelationService mailRelationService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long syncMailDelivery(MailDeliveryDTO mailDeliveryDTO) {

        Assert.notNull(mailTemplateService.findOne(mailDeliveryDTO.getMailTemplateId()),
                ResponseEnum.THE_MAIL_TEMPLATE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());


        MailDelivery mailDelivery = mailDeliveryMapper.findMailDeliveryBySendPoint(mailDeliveryDTO.getSendingPoint());
        if (ObjectUtil.isNull(mailDelivery)) {
            mailDelivery = new MailDelivery();
        }

        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreNullValue(Boolean.TRUE);
        BeanUtil.copyProperties(mailDeliveryDTO, mailDelivery, copyOptions);

        mailDelivery.setMailTo(MailUtils.recipient(mailDeliveryDTO.getTo()));
        mailDelivery.setMailCc(MailUtils.recipient(mailDeliveryDTO.getCc()));
        mailDelivery.setMailBcc(MailUtils.recipient(mailDeliveryDTO.getBcc()));
        mailDelivery.setMailReply(MailUtils.recipient(mailDeliveryDTO.getReply()));

        if (Validator.isNull(mailDelivery.getId())) {
            mailDelivery.setCreatedTime(DateUtil.current());
            this.save(mailDelivery);
        } else {
            mailDelivery.setUpdatedTime(DateUtil.current());
            this.updateById(mailDelivery);
        }

        mailRelationService.syncRelation(mailDelivery.getId(), mailDeliveryDTO.getMailTemplateId());

        return mailDelivery.getId();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteMailDelivery(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        MailDelivery mailDelivery = this.getById(id);
        if (ObjectUtil.isNotNull(mailDelivery)) {
            return this.removeById(id);
        }

        return Boolean.FALSE;
    }

    @Override
    public PageVO<MailDeliveryVO> findMailDelivery(MailDeliveryFilterDTO pageDTO) {

        PageVO<MailDeliveryVO> pageVO = new PageVO<>();

        if (pageDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(pageDTO);
        }

        MailDeliveryQuery query = new MailDeliveryQuery();
        BeanUtil.copyProperties(pageDTO, query);

        List<MailDeliveryBO> deliveryList = mailDeliveryMapper.findMailDelivery(query);
        PageInfo<MailDeliveryBO> pageInfo = new PageInfo<>(deliveryList);

        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(pageInfo.getList()));

        return pageVO;
    }

    @Override
    public MailDeliveryVO findOne(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(mailDeliveryMapper.findOne(id));
    }

    @Override
    public MailDeliveryVO findMailDelivery(String point) {

        MailDeliveryBO mailDeliveryBO = mailDeliveryMapper.findMailDeliveryByPoint(point);
        MailDeliveryVO mailDeliveryVO = this.objectConversion(mailDeliveryBO);
        if (ObjectUtil.isNotNull(mailDeliveryVO)) {
            MailTemplateVO mailTemplateVO = mailDeliveryVO.getMailTemplate();
            if (!Validator.equal(NumberConstant.TWO.byteValue(), mailTemplateVO.getType())) {
                String content = mailTemplateVO.getContent();
                MailTemplateVO templateVO = mailTemplateService.findTemplate(NumberConstant.TWO.byteValue());
                if (ObjectUtil.isNotNull(templateVO)) {
                    content = content + templateVO.getContent();
                    mailTemplateVO.setContent(content);
                    mailDeliveryVO.setMailTemplate(mailTemplateVO);
                }
            }
        }

        return mailDeliveryVO;
    }

    @Override
    public MailDeliveryVO objectConversion(MailDeliveryBO mailDeliveryBO) {

        MailDeliveryVO mailDeliveryVO = super.objectConversion(mailDeliveryBO);
        if (ObjectUtil.isNotNull(mailDeliveryVO)) {
            Optional.ofNullable(mailDeliveryBO.getTemplate())
                    .ifPresent(a -> mailDeliveryVO.setMailTemplate(mailTemplateService.objectConversion(a)));
            mailDeliveryVO.setFrom(MailUtils.recipient(mailDeliveryBO.getMailFrom(), new TypeReference<MailBean>() {
            }));
            mailDeliveryVO.setTo(MailUtils.recipient(mailDeliveryBO.getMailTo(), new TypeReference<List<MailBean>>() {
            }));
            mailDeliveryVO.setCc(MailUtils.recipient(mailDeliveryBO.getMailCc(), new TypeReference<List<MailBean>>() {
            }));
            mailDeliveryVO.setBcc(MailUtils.recipient(mailDeliveryBO.getMailBcc(), new TypeReference<List<MailBean>>() {
            }));
            mailDeliveryVO.setReply(MailUtils.recipient(mailDeliveryBO.getMailReply(), new TypeReference<List<MailBean>>() {
            }));
        }

        return mailDeliveryVO;
    }


}
