package com.xdcplus.ztb.common.remote.domain.workflow.dto;

import cn.hutool.core.collection.CollectionUtil;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * 请求dto
 *
 * @author Fish.Fei
 * @date 2021/08/05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class RequestDTO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -91167096272246572L;

    @ApiModelProperty("id")
    private Long requestId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("单号规则ID")
    private Long ruleId;

    @ApiModelProperty("流程ID")
    private Long processId;

    @ApiModelProperty("流程配置版本号")
    private String configVersion;

    @ApiModelProperty(value = "父级表单")
    private Set<Long> parentIds;

    /**
     * 流转条件
     */
    @ApiModelProperty(value = "流转条件")
    private Circulation circulation;

    /**
     * 策略条件
     */
    @ApiModelProperty(value = "策略条件")
    private FormFlowStrategyDTO strategy;

    public Set<Long> getParentIds() {

        if (CollectionUtil.isEmpty(parentIds)) {
            parentIds = CollectionUtil.newHashSet();
            parentIds.add(NumberConstant.ZERO.longValue());
        }

        return parentIds;
    }


}
