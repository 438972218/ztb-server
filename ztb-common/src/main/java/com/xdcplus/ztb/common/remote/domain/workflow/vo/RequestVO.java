package com.xdcplus.ztb.common.remote.domain.workflow.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 请求签证官
 *
 * @author Fish.Fei
 * @date 2021/08/05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class RequestVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -60412008760522731L;

    /**
     * 单号
     */
    @ApiModelProperty("单号")
    private String oddNumber;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     *  表单状态
     */
    @ApiModelProperty("表单状态")
    private ProcessStatusVO status;

    /**
     * 流程信息
     */
    @ApiModelProperty("流程信息")
    private ProcessVO process;

    /**
     * 父级表单信息
     */
    @ApiModelProperty("父级表单信息")
    private List<RequestVO> parent;

    /**
     * 流程配置版本号
     */
    @ApiModelProperty("流程配置版本号")
    private String configVersion;

    /**
     * 表单所属类型
     */
    @ApiModelProperty("表单所属类型")
    private RequestTypeVO requestType;

}
