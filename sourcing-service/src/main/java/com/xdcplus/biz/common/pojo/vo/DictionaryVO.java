package com.xdcplus.biz.common.pojo.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 字典管理(Dictionary)表VO类
 *
 * @author Fish.Fei
 * @since 2021-08-12 15:20:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
@SuppressWarnings("serial")
public class DictionaryVO extends BaseBO implements Serializable {
    private static final long serialVersionUID = -45228206489636938L;

    @ApiModelProperty("字典名称")
    private String dictionaryChinese;

    @ApiModelProperty("字典类名")
    private String dictionaryClass;

    @ApiModelProperty("下拉中文名")
    private String meaning;

    @ApiModelProperty("下拉ID")
    private Integer numerical;

}
