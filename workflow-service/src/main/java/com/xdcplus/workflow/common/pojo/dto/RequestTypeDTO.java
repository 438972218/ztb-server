package com.xdcplus.workflow.common.pojo.dto;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 表单类型DTO
 *
 * @author Rong.Jia
 * @date 2021/08/05 14:23:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("表单类型参数对照对象")
public class RequestTypeDTO extends BaseBO implements Serializable {

    private static final long serialVersionUID = -3835174272893911690L;

    /**
     * 表单类型
     */
    @NotBlank(message = "表单类型 不能为空")
    @ApiModelProperty(value = "表单类型", required = true)
    private String requestType;

















}
