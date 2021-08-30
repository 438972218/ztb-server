package com.xdcplus.workflow.service;

import com.xdcplus.workflow.common.pojo.bean.MailNotificationBean;
import com.xdcplus.workflow.common.pojo.dto.ExtraMailNotificationDTO;

/**
 * 通知服务
 *
 * @author Rong.Jia
 * @date 2021/08/25
 */
public interface NotificationService {

    /**
     * 邮件通知
     *
     * @param notificationDTO 通知DTO
     * @return 邮件唯一标识
     */
    String notification(MailNotificationBean notificationDTO);

    /**
     * 新项目成员的电子邮件通知
     *
     * @param extraMailNotificationDTO 电子邮件通知的新项目成员DTO
     * @return {@link String}
     */
    String emailNodeNotification(ExtraMailNotificationDTO extraMailNotificationDTO);




}
