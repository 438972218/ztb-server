package com.xdcplus.workflow.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 邮件模板信息
 * </p>
 *
 * @author Rong.Jia
 * @since 2021-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_mail_template")
public class MailTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 模板名
     */
    private String name;

    /**
     * 模板类型(-1->注册模板, 1->节点通知模板, 2-> 签名模板)
     */
    private Byte type;

    /**
     * 模板
     */
    private String template;

    /**
     *  默认
     */
    private String defaultValue;

    /**
     * 添加时间
     */
    private Long createdTime;

    /**
     * 修改时间
     */
    private Long updatedTime;

    /**
     * 添加人
     */
    private String createdUser;

    /**
     * 修改人
     */
    private String updatedUser;

    /**
     * 描述
     */
    private String description;


}
