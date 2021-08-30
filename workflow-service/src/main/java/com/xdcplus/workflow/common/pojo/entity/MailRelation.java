package com.xdcplus.workflow.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 邮件发送点-签名-模板关系
 *
 * @author Rong.Jia
 * @date 2021/08/10 15:21:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_mail_relation")
public class MailRelation implements Serializable {

    private static final long serialVersionUID = -8695127101690979861L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 发送点ID
     */
    private Long deliveryId;

    /**
     * 模板ID
     */
    private Long templateId;
















}
