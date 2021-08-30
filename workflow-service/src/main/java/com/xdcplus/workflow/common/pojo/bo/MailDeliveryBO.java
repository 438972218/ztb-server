package com.xdcplus.workflow.common.pojo.bo;

import com.xdcplus.workflow.common.pojo.entity.MailDelivery;
import com.xdcplus.workflow.common.pojo.entity.MailTemplate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 邮件交付BO
 *
 * @author Rong.Jia
 * @date 2021/08/10 15:26:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MailDeliveryBO extends MailDelivery implements Serializable {

    private static final long serialVersionUID = 2628596616656228803L;

    /**
     * 邮件模板信息
     */
    private MailTemplate template;


}
