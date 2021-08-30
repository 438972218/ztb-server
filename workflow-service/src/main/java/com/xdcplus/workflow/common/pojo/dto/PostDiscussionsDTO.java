package com.xdcplus.workflow.common.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 发起讨论DTO
 *
 * @author Rong.Jia
 * @date 2021/08/19 09:46:20
 */
@Data
@ApiModel("发起讨论 参数对照对象")
public class PostDiscussionsDTO implements Serializable {

    private static final long serialVersionUID = -7818745264696806025L;

    /**
     * 表单ID
     */
    @NotNull(message = "表单ID 不能为空")
    @ApiModelProperty(value = "表单ID", required = true)
    private Long requestId;

    /**
     * 主题
     */
    @ApiModelProperty(value = "主题")
    private String subject;

    /**
     * 内容
     */
    @NotBlank(message = "内容 不能为空")
    @ApiModelProperty(value = "内容", required = true)
    private String content;

    /**
     * 收信人
     */
    @NotNull(message = "收信人 不能为空")
    @ApiModelProperty(value = "收信人", required = true)
    private List<@Valid String> toUsers;









}
