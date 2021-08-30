package com.xdcplus.workflow.common.pojo.dto;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 讨论组过滤器DTO
 *
 * @author Rong.Jia
 * @date 2021/08/19 10:33:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("讨论组过滤查询参数对照对象")
public class DiscussGroupFilterDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 7915734023474402878L;

    /**
     * 表单ID
     */
    @ApiModelProperty("表单ID")
    private Long requestId;












}
