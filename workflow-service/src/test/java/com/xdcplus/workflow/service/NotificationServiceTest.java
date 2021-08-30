package com.xdcplus.workflow.service;

import com.xdcplus.workflow.WorkflowServiceApplicationTests;
import com.xdcplus.workflow.common.pojo.bean.MailBean;
import com.xdcplus.workflow.common.pojo.dto.ExtraMailNotificationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 通知服务测试类
 *
 * @author Rong.Jia
 * @date 2021/08/11 13:34:37
 */
class NotificationServiceTest extends WorkflowServiceApplicationTests {

    @Autowired
    private NotificationHistoryService notificationHistoryService;

    @Autowired
    private NotificationService notificationService;

    @Test
    void findNotificationHistory() {




    }

    @Test
    void register() {

        ExtraMailNotificationDTO extraMailNotificationDTO = new ExtraMailNotificationDTO();
        extraMailNotificationDTO.setPoint("1");
        extraMailNotificationDTO.setRequestId(132132132L);
        extraMailNotificationDTO.setFlowId(132132132L);

        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("name", "嵇浩");
        objectMap.put("homepage", "https://esourcing.suntech-power.com");
        objectMap.put("userName", "jj");
        objectMap.put("password", "123456");

        MailBean mailBean = new MailBean();
        mailBean.setMail("martin.ji@xdcplus.com");
        mailBean.setName("嵇浩");

        extraMailNotificationDTO.setExtra(objectMap);
        extraMailNotificationDTO.setTo(Collections.singletonList(mailBean));

        String notification = notificationService.emailNodeNotification(extraMailNotificationDTO);
        System.out.println(notification);


    }























}