package com.xdcplus.permission.common.pojo.dto.sysdictionary;

import com.xdcplus.permission.common.validator.groupvlidator.InsertGroupValidator;
import com.xdcplus.permission.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.ztb.common.tool.validator.groupvlidator.IdGroupValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 字典表(SysDictionary)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("字典请求参数对照对象")
public class SysDictionaryDto implements Serializable {
    private static final long serialVersionUID = -72739753643702191L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @NotNull(groups = {IdGroupValidator.class, UpdateGroupValidator.class}, message = "id不能为空")
    private Long id;
    /**
     * 字典中文名称
     */
    @ApiModelProperty("字典中文名称")
    @NotBlank(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "字典中文名称不能为空")
    private String dictionaryChinese;
    /**
     * 字典类别
     */
    @ApiModelProperty("字典类别")
    @NotBlank(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "字典类别不能为空")
    private String dictionaryClass;
    /**
     * 字典的意思及描述
     */
    @ApiModelProperty("字典的意思及描述")
    @NotBlank(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "字典的描述不能为空")
    private String meaning;
    /**
     * 字典的数值
     */
    @ApiModelProperty("字典的数值")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "字典的数值不能为空")
    private Integer numerical;


}
