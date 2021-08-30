package com.xdcplus.spring.boot.autoconfigure;

import cn.hutool.core.lang.Assert;
import com.xdcplus.ztb.common.mail.EmailProperties;
import com.xdcplus.ztb.common.mail.EmailTemplate;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 电子邮件工厂bean
 *
 * @author Rong.Jia
 * @date 2021/07/26 13:53:17
 */
public class EmailFactoryBean implements FactoryBean<EmailTemplate>, InitializingBean, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(EmailFactoryBean.class);

    private ApplicationContext applicationContext;
    private EmailProperties emailProperties;
    private EmailTemplate emailTemplate;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public EmailFactoryBean(EmailProperties emailProperties) {
        this.emailProperties = emailProperties;
    }

    @Override
    public EmailTemplate getObject() {
        return emailTemplate;
    }

    @Override
    public Class<?> getObjectType() {
        return EmailTemplate.class;
    }

    @Override
    public boolean isSingleton() {
        return Boolean.TRUE;
    }

    @Override
    public void afterPropertiesSet() {

        Assert.notBlank(emailProperties.getHost(),
                String.format(ResponseEnum.THE_PROPERTY_CANNOT_BE_EMPTY.getMessage(), "host"));

        Assert.notNull(emailProperties.getPort(),
                String.format(ResponseEnum.THE_PROPERTY_CANNOT_BE_EMPTY.getMessage(), "port"));

        if (!emailProperties.getAvoidAuthEnable()) {
            Assert.notBlank(emailProperties.getUsername(),
                    String.format(ResponseEnum.THE_PROPERTY_CANNOT_BE_EMPTY.getMessage(), "username"));

            Assert.notBlank(emailProperties.getPassword(),
                    String.format(ResponseEnum.THE_PROPERTY_CANNOT_BE_EMPTY.getMessage(), "password"));
        }
        Assert.notBlank(emailProperties.getFrom().getEmail(),
                String.format(ResponseEnum.THE_PROPERTY_CANNOT_BE_EMPTY.getMessage(), "email"));

        emailTemplate = new EmailTemplate(emailProperties);
    }


}
