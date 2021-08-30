package com.xdcplus.permission.common.pojo.vo.sysdictionary;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典表(SysDictionary)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:11
 */
@Data
@ApiModel("字典Vo")
public class SysDictionaryVo implements Serializable {
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
    /**
     * 字典类别
     */
    @ApiModelProperty("字典类别")
    private String dictionaryClass;
    /**
     * 字典的意思及描述
     */
    @ApiModelProperty("字典的意思及描述")
    private String meaning;
    /**
     * 字典的数值
     */
    @ApiModelProperty("字典的数值")
    private Integer numerical;
    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createdUser;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Long createdTime;
    /**
     * 更新人
     */
    @ApiModelProperty("更新人")
    private String updatedUser;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Long updatedTime;



}
