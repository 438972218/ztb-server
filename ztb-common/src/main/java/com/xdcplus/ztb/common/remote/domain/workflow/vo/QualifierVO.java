package com.xdcplus.ztb.common.remote.domain.workflow.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 限定符签证官
 *
 * @author Fish.Fei
 * @date 2021/08/05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("限定规则信息 对照对象")
public class QualifierVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 4079655144529110374L;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 算法脚本
     */
    @ApiModelProperty("算法脚本")
    private String script;

}