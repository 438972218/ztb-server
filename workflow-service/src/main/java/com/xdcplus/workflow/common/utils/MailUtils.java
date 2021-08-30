package com.xdcplus.workflow.common.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONValidator;
import com.alibaba.fastjson.TypeReference;
import com.xdcplus.workflow.common.pojo.bean.MailBean;
import com.xdcplus.ztb.common.mail.domain.EmailTo;
import com.xdcplus.ztb.common.tool.utils.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 邮件工具类
 *
 * @author Rong.Jia
 * @date 2021/08/11 11:59:36
 */
public class MailUtils {

    /**
     * 收件人
     *
     * @param json      json
     * @param reference 格式
     * @return {@link T}
     */
    public static <T> T recipient(String json, TypeReference<T> reference) {

        if (StrUtil.isNotBlank(json)) {
            if (JSONValidator.from(json).validate()) {
                return JSONObject.parseObject(json, reference);
            }
        }
        return null;
    }

    /**
     * 收件人
     *
     * @param mails 邮件
     * @return {@link String} 收件人
     */
    public static String recipient(List<MailBean> mails) {

        if (CollectionUtil.isNotEmpty(mails)) {

            mails = mails.stream().filter(a -> StrUtil.isNotBlank(a.getMail()))
                    .collect(Collectors.toList());

            if (CollectionUtil.isNotEmpty(mails)) {
                return JSON.toJSONString(mails);
            }
        }

        return null;
    }

    /**
     * 收件人
     *
     * @param mail 邮件
     * @return {@link String} 收件人
     */
    public static String recipient(MailBean mail) {

        if (ObjectUtil.isNotNull(mail) && StrUtil.isNotBlank(mail.getMail())) {
            return JSON.toJSONString(mail);
        }

        return null;

    }

    /**
     * 收件人
     *
     * @param mailBeans 邮件bean
     * @return {@link List <EmailTo>}
     */
    public static List<EmailTo> sendRecipient(List<MailBean> mailBeans) {

        if (CollectionUtil.isNotEmpty(mailBeans)) {
            return BeanUtils.copyProperties(mailBeans, EmailTo.class);
        }

        return null;
    }


}
