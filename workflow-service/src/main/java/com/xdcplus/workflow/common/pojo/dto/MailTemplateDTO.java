package com.xdcplus.workflow.common.pojo.dto;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 邮件模板DTO
 *
 * @author Rong.Jia
 * @date 2021/08/10 10:28:52
 */
@ApiModel("邮件模板参数对照对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class MailTemplateDTO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 6046424973197711199L;

    /**
     * 模板名
     */
    @NotBlank(message = "模板名 不能为空")
    @ApiModelProperty(value = "模板名", required = true)
    private String name;

    /**
     * 模板信息
     */
    @NotBlank(message = "模板信息 不能为空")
    @ApiModelProperty(value = "模板信息", required = true)
    private String template;

    /**
     *  模板类型(-1->注册模板, 1->节点通知模板, 2->签名模板)
     */
    @NotBlank(message = "模板类型 不能为空")
    @ApiModelProperty(value = "模板类型", required = true)
    private Byte type;















}
