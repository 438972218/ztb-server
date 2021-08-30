package com.xdcplus.workflow.common.pojo.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 邮件模板VO
 *
 * @author Rong.Jia
 * @date 2021/08/10 10:27:41
 */
@ApiModel("邮件模板信息 对照对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class MailTemplateVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = -6856167388888371993L;

    /**
     * 模板名
     */
    @ApiModelProperty("模板名")
    private String name;

    /**
     * 模板类型(-1->注册模板, 1->节点通知模板)
     */
    @ApiModelProperty("模板类型, 详见字典")
    private Byte type;

    /**
     * 模板内容
     */
    @ApiModelProperty("模板内容")
    private String content;


















}
