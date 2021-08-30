package com.xdcplus.workflow.common.pojo.dto;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 表单类型过滤器DTO
 *
 * @author Rong.Jia
 * @date 2021/08/05 14:39:08
 */
@ApiModel("表单类型过滤查询 参数对照对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class RequestTypeFilterDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = -3570672975929948108L;

    /**
     * 类型名, 可模糊查询
     */
    @ApiModelProperty("类型名， 可模糊查询")
    private String typeName;









}
