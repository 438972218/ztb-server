package com.xdcplus.workflow.service;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.xdcplus.workflow.WorkflowServiceApplicationTests;
import com.xdcplus.workflow.common.pojo.bean.MailBean;
import com.xdcplus.workflow.common.pojo.dto.MailDeliveryDTO;
import com.xdcplus.workflow.common.pojo.dto.MailDeliveryFilterDTO;
import com.xdcplus.workflow.common.pojo.vo.MailDeliveryVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 邮件交付服务测试类
 *
 * @author Rong.Jia
 * @date 2021/08/10 14:57:48
 */
class MailDeliveryServiceTest extends WorkflowServiceApplicationTests {

    @Autowired
    private MailDeliveryService mailDeliveryService;

    @Test
    void syncMailDelivery() {

        MailBean from = new MailBean();
        from.setMail("rong.jia@xdcplus.com");
        from.setName("贾荣");

        MailBean to = new MailBean();
        to.setName("嵇浩");
        to.setMail("martin.ji@xdcplus.com");


        MailDeliveryDTO mailDeliveryDTO = new MailDeliveryDTO();
        mailDeliveryDTO.setTo(CollectionUtil.newArrayList(to));
        mailDeliveryDTO.setMailSubject("邮件测试");
        mailDeliveryDTO.setMailTemplateId(1L);
        mailDeliveryDTO.setSendingPoint("123213232");

        System.out.println(mailDeliveryService.syncMailDelivery(mailDeliveryDTO));


    }

    @Test
    void deleteMailDelivery() {
    }

    @Test
    void findMailDelivery() {

        MailDeliveryFilterDTO deliveryFilterDTO = new MailDeliveryFilterDTO();
        deliveryFilterDTO.setCurrentPage(1);
        deliveryFilterDTO.setPageSize(20);

        System.out.println(JSON.toJSONString(mailDeliveryService.findMailDelivery(deliveryFilterDTO)));

    }

    @Test
    void findMailDelivery1() {

        MailDeliveryVO mailDeliveryVO = mailDeliveryService.findMailDelivery("123213232");
        System.out.println(JSON.toJSONString(mailDeliveryVO));

    }

















}