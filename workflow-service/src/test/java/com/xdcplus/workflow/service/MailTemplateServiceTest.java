package com.xdcplus.workflow.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.alibaba.fastjson.JSON;
import com.xdcplus.workflow.WorkflowServiceApplicationTests;
import com.xdcplus.ztb.common.mail.EmailTemplate;
import com.xdcplus.ztb.common.mail.domain.EmailTo;
import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 邮件模板服务测试类
 *
 * @author Rong.Jia
 * @date 2021/08/10 11:10:58
 */
class MailTemplateServiceTest extends WorkflowServiceApplicationTests {

    @Autowired
    private MailTemplateService mailTemplateService;

    @Autowired
    private EmailTemplate emailTemplate;

    @Test
    void saveTemplate() {




    }

    @Test
    void updateTemplate() {
    }

    @Test
    void deleteTemplate() {
    }

    @Test
    void findTemplates() {


        PageDTO pageDTO = new PageDTO();
        pageDTO.setCurrentPage(1);
        pageDTO.setPageSize(20);

        System.out.println(JSON.toJSONString(mailTemplateService.findTemplates(pageDTO)));

    }

    @Test
    void findOne() {

        System.out.println(JSON.toJSONString(mailTemplateService.findOne(1L)));

    }

    @Test
    void readTemplate() {

        ClassPathResource classPathResource = new ClassPathResource("classpath:/templates/email/register.txt");
        System.out.println(classPathResource.readUtf8Str());

    }

    @Test
    void sendEmail() {

        String username = "嵇浩";
        String system_address = "http://localhost:8080";
        String account = "jihao";
        String password = "123456";
        String telephone = "1101";

        ClassPathResource classPathResource = new ClassPathResource("classpath:/templates/email/register.txt");
        String template = classPathResource.readUtf8Str();
        template = StrUtil.replace(template, "username", username);
        template = StrUtil.replace(template, "system_address", system_address);
        template = StrUtil.replace(template, "account", account);
        template = StrUtil.replace(template, "password", password);
        template = StrUtil.replace(template, "telephone", telephone);

        EmailTo emailTo = new EmailTo();
        emailTo.setMail("martin.ji@xdcplus.com");
        emailTo.setName("嵇浩");

        System.out.println(emailTemplate.sendHtml("注册邮件测试", template, emailTo));

    }




}