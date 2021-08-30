package com.xdcplus.permission.common.pojo.dto.sysemployee;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("参数对照对象")
public class QueryByCompanyIdOrDepartIdPageDto extends PageDTO implements Serializable {
    @ApiModelProperty("类型：type 1:公司,type 2:部门")
    private Integer type;
    @ApiModelProperty("公司或者部门 id")
    private Long id;
}
