package com.xdcplus.workflow.common.pojo.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 讨论组VO
 *
 * @author Rong.Jia
 * @date 2021/08/18 17:57:46
 */
@ApiModel("讨论组 信息")
@EqualsAndHashCode(callSuper = true)
@Data
public class DiscussGroupVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = -215408447027339015L;

    /**
     * 讨论主题
     */
    @ApiModelProperty("讨论主题")
    private String subject;

    /**
     *  表单信息
     */
    @ApiModelProperty("表单信息")
    private RequestVO request;








}
