package com.xdcplus.workflow.common.pojo.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 表单类型VO
 *
 * @author Rong.Jia
 * @date 2021/08/05 14:23:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("表单类型 信息对照对象")
public class RequestTypeVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = -3835174272893911690L;

    /**
     * 表单类型
     */
    @ApiModelProperty("表单类型")
    private String requestType;

















}
