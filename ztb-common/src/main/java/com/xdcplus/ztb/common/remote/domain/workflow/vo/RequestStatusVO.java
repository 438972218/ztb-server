package com.xdcplus.ztb.common.remote.domain.workflow.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 请求状态签证官
 *
 * @author Fish.Fei
 * @date 2021/08/05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class RequestStatusVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -60412008760522733L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

}
