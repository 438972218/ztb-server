package com.xdcplus.permission.common.pojo.dto.sysdictionary;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 字典表(SysDictionary)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("字典分页请求过滤查询 参数对照对象")
public class SysDictionaryFilterDto extends PageDTO implements Serializable {
    private static final long serialVersionUID = -72739753643702191L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;
    /**
     * 字典中文名称
     */
    @ApiModelProperty("字典中文名称")
    private String dictionaryChinese;


}
