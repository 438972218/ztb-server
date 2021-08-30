package com.xdcplus.workflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xdcplus.workflow.common.builder.ReplaceAttributeBuilder;
import com.xdcplus.workflow.common.pojo.bean.MailNotificationBean;
import com.xdcplus.workflow.common.pojo.dto.ExtraMailNotificationDTO;
import com.xdcplus.workflow.common.pojo.dto.NotificationHistoryDTO;
import com.xdcplus.workflow.common.pojo.vo.MailDeliveryVO;
import com.xdcplus.workflow.common.utils.MailUtils;
import com.xdcplus.workflow.service.*;
import com.xdcplus.ztb.common.exceptions.MailException;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.mail.EmailTemplate;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 通知服务实现
 *
 * @author Rong.Jia
 * @date 2021/08/25
 */
@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private EmailTemplate emailTemplate;

    @Autowired
    private NotificationRelationService notificationRelationService;

    @Autowired
    private NotificationHistoryService notificationHistoryService;

    @Autowired
    private MailDeliveryService mailDeliveryService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public String notification(MailNotificationBean notificationDTO) {

        MailDeliveryVO mailDeliveryVO = mailDeliveryService.findMailDelivery(notificationDTO.getPoint());

        Assert.notNull(mailDeliveryVO, ResponseEnum.THE_EMAIL_NOTIFICATION_POINT_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED.getMessage());

        if (CollectionUtil.isEmpty(notificationDTO.getCc())) {
            notificationDTO.setCc(mailDeliveryVO.getCc());
        }

        if (CollectionUtil.isEmpty(notificationDTO.getBcc())) {
            notificationDTO.setBcc(mailDeliveryVO.getBcc());
        }

        if (CollectionUtil.isEmpty(notificationDTO.getReply())) {
            notificationDTO.setReply(mailDeliveryVO.getReply());
        }

        if (CollectionUtil.isEmpty(notificationDTO.getTo())) {
            notificationDTO.setTo(mailDeliveryVO.getTo());
        }

        if (StrUtil.isBlank(notificationDTO.getMailSubject())) {
            notificationDTO.setMailSubject(mailDeliveryVO.getMailSubject());
        }

        if (StrUtil.isBlank(notificationDTO.getHtml())) {
            notificationDTO.setHtml(mailDeliveryVO.getMailTemplate().getContent());
        }

        try {
            String messageId = emailTemplate.sendHtml(notificationDTO.getMailSubject(),
                    notificationDTO.getHtml(),
                    MailUtils.sendRecipient(notificationDTO.getTo()),
                    MailUtils.sendRecipient(notificationDTO.getCc()),
                    MailUtils.sendRecipient(notificationDTO.getBcc()),
                    MailUtils.sendRecipient(notificationDTO.getReply()));

            NotificationHistoryDTO notificationHistoryDTO = new NotificationHistoryDTO();
            BeanUtil.copyProperties(notificationDTO, notificationHistoryDTO);
            notificationHistoryDTO.setSubject(notificationDTO.getMailSubject());
            notificationHistoryDTO.setContent(notificationDTO.getHtml());

            Long historyId = notificationHistoryService.saveNotification(notificationHistoryDTO);

            notificationRelationService.saveNotificationRelation(notificationDTO.getRequestId(),
                    notificationDTO.getFlowId(), historyId);

            return messageId;
        }catch (MailException e) {
            log.error("Failed to send an email {}", e.getMessage());
            throw new ZtbWebException(ResponseEnum.FAILED_TO_SEND_AN_EMAIL);
        }
    }

    @Override
    public String emailNodeNotification(ExtraMailNotificationDTO extraMailNotificationDTO) {

        MailDeliveryVO mailDeliveryVO = mailDeliveryService.findMailDelivery(extraMailNotificationDTO.getPoint());

        String html = mailDeliveryVO.getMailTemplate().getContent();
        Map<String, String> extraMap = getReplacementValue(extraMailNotificationDTO.getExtra());
        if (CollectionUtil.isNotEmpty(extraMap)) {
            for (Map.Entry<String, String> entry : extraMap.entrySet()) {
                html = StrUtil.replace(html, entry.getKey(), entry.getValue());
            }
        }

        MailNotificationBean mailNotificationBean = new MailNotificationBean();
        BeanUtil.copyProperties(extraMailNotificationDTO, mailNotificationBean);
        mailNotificationBean.setHtml(html);
        mailNotificationBean.setFlowId(NumberConstant.A_NEGATIVE.longValue());
        mailNotificationBean.setRequestId(NumberConstant.A_NEGATIVE.longValue());

        return this.notification(mailNotificationBean);
    }

    /**
     * 获取替换对应信息
     *
     * @param extra 额外的
     * @return {@link Map}<{@link String}, {@link String}>
     */
    private Map<String, String> getReplacementValue(Object extra) {
        Map<String, String> map = null;
        if (extra instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) extra;
            map = jsonObject.getInnerMap().entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> Convert.toStr(e.getValue())));
        }else if (extra instanceof Map) {
            map = JSONObject.parseObject(JSON.toJSONString(extra), new TypeReference<Map<String, String>>(){});
        }else {
            try {
                map = CollectionUtil.newArrayList(ReflectUtil.getFields(extra.getClass())).stream()
                        .collect(Collectors.toMap(Field::getName, a -> Convert.toStr(ReflectUtil.getFieldValue(extra, a))));
            }catch (Exception ignored) {}
        }

        if (CollectionUtil.isNotEmpty(map)) {
            return map.entrySet().stream().collect(Collectors.toMap(e -> ReplaceAttributeBuilder.builder().addParameter(e.getKey()).build(), e -> e.getValue()));
        }

        return null;
    }

























}
