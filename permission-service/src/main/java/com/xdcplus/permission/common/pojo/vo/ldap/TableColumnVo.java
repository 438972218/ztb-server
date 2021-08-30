package com.xdcplus.permission.common.pojo.vo.ldap;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分部表(公司表)Vo")
public class TableColumnVo {
    /**
     * 列名
     */
    @ApiModelProperty("列名")
    private String  columnName;
    /**
     * 列注释
     */
    @ApiModelProperty("列注释")
    private String  columnComment;
}
