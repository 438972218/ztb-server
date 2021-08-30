package com.xdcplus.email;

import com.xdcplus.spring.boot.autoconfigure.EmailFactoryBean;
import com.xdcplus.ztb.common.mail.EmailProperties;
import com.xdcplus.ztb.common.mail.EmailTemplate;
import com.xdcplus.ztb.common.mail.domain.EmailTo;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 电子邮件工具类测试类
 *
 * @author Rong.Jia
 * @date 2021/07/26 13:36:38
 */
public class EmailUtilTest {

    private static EmailTemplate emailTemplate;

    private static List<EmailTo> toList = new ArrayList<>();
    private static List<EmailTo> bccList = new ArrayList<>();
    private static List<EmailTo> ccList = new ArrayList<>();
    private static List<EmailTo> replyList = new ArrayList<>();

    static {

        EmailProperties.From from = new EmailProperties.From();
        from.setEmail("rong.jia@xdcplus.com");
        from.setName("贾荣");

        EmailTo to = new EmailTo();
        to.setMail("rong.jia@xdcplus.com");
        to.setName("贾荣1");
        toList.add(to);

        EmailTo cc = new EmailTo();
        cc.setMail("martin.ji@xdcplus.com");
        cc.setName("嵇浩");
        ccList.add(cc);

        EmailTo bcc = new EmailTo();
        bcc.setMail("fish.fei@xdcplus.com");
        bcc.setName("费戌辉");
        bccList.add(bcc);

        EmailTo reply = new EmailTo();
        reply.setMail("black.hua@xdcplus.com");
        reply.setName("华俊龙");
        replyList.add(reply);

        EmailProperties emailProperties = new EmailProperties();
        emailProperties.setEnabled(Boolean.TRUE);
        emailProperties.setHost("10.20.1.13");
        emailProperties.setPort(25);

        emailProperties.setSslEnable(Boolean.FALSE);
        emailProperties.setDebug(Boolean.FALSE);
        emailProperties.setAvoidAuthEnable(Boolean.TRUE);
        emailProperties.setFrom(from);

        EmailFactoryBean factoryBean = new EmailFactoryBean(emailProperties);
        factoryBean.afterPropertiesSet();
        emailTemplate = factoryBean.getObject();

    }

    @Test
    public void senText() {

        String sendText = emailTemplate.sendText( "对接测试", "This is a test mail ... :-)", toList);

        System.out.println(sendText);

    }

    @Test
    public void sendFile() {

        File file = new File("F:\\我的图片\\1.jpeg");

        String sendFile = emailTemplate.sendFile("对接测试发送文件", "This is a test mail ... :-)", file, toList);

        System.out.println(sendFile);

    }

    @Test
    public void sendHtml() {

        String html = ".... <img src=\"http://www.apache.org/images/feather.gif\"> ....";
        String sendHtml = emailTemplate.sendHtml("对接测试发送HTML", html, toList);
        System.out.println(sendHtml);

    }

    @Test
    public void sendHTML() {

        String html = "<div>\n" +
                "        <p class=\"p-style\" style=\"margin: 20px 0 40px 20px\">尊敬的username用户</p>\n" +
                "        <p class=\"p-style\" style=\"margin: 20px 0 40px 20px\">您已成功注册，并可在\n" +
                "\t\t<a href=\"system_address\">system_address</a> 登录使用</p>\n" +
                "        <p style=\"margin-left: 20px;\">您的用户名：account</p>\n" +
                "        <p class=\"p-style\" style=\"margin: 20px 0 40px 20px\">您的密码：password</p>\n" +
                "        <p style=\"margin-left: 20px;\">需要任何帮助或者说明，请拨打我们的热线支持电话：</p>\n" +
                "        <p style=\"margin-left: 20px;\">telephone</p>\n" +
                "    </div>";

        EmailTo emailTo = new EmailTo();
        emailTo.setCharset("utf-8");
        emailTo.setMail("martin.ji@xdcplus.com");
        emailTo.setName("嵇浩");

        System.out.println(emailTemplate.sendHtml("邮件测试112", html, emailTo));


    }













}
