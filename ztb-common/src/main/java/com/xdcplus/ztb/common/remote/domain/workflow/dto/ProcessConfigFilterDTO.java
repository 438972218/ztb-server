package com.xdcplus.ztb.common.remote.domain.workflow.dto;

import cn.hutool.core.lang.Validator;
import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 流程配置过滤器dto
 *
 * @author Fish.Fei
 * @date 2021/08/12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("过程配置过滤查询 对照对象")
public class ProcessConfigFilterDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 8203074873347721234L;

    /**
     *  流程ID
     */
    @NotNull(message = "流程ID 不能为空")
    @ApiModelProperty(value = "流程ID", required = true)
    private Long processId;

    /**
     *  流程配置版本
     */
    @ApiModelProperty(value = "流程配置版本")
    private String version;

    /**
     * 是否过滤归档
     */
    @ApiModelProperty(value = "是否过滤归档，默认false")
    private Boolean whetherArchive;

    public Boolean getWhetherArchive() {
        return Validator.isNull(whetherArchive) ? Boolean.FALSE : whetherArchive;
    }
}
