package com.xdcplus.workflow.common.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 讨论回复DTO
 *
 * @author Rong.Jia
 * @date 2021/08/18 17:19:35
 */
@ApiModel("讨论回复 参数对照对象")
@Data
public class ReplyDiscussDTO implements Serializable {

    private static final long serialVersionUID = 8062879341830658942L;

    /**
     * 讨论组ID
     */
    @NotNull(message = "讨论组ID 不能为空")
    @ApiModelProperty(value = "讨论组ID", required = true)
    private Long groupId;

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
