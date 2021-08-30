package com.xdcplus.ztb.common.remote.domain.workflow.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 过程签证官
 *
 * @author Fish.Fei
 * @date 2021/08/05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("流程信息 对照对象")
public class ProcessVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 5765680751939328640L;

    /**
     * 流程名
     */
    @ApiModelProperty(value = "流程名")
    private String name;

}
