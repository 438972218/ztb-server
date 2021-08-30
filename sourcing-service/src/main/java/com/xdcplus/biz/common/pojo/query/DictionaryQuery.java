package com.xdcplus.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典管理(Dictionary)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-08-12 15:20:51
 */
@Data
@SuppressWarnings("serial")
public class DictionaryQuery implements Serializable {
    private static final long serialVersionUID = -95185988860117667L;

    private Long id;

    private String dictionaryChinese;

    private String dictionaryClass;

    private String meaning;

    private Integer numerical;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}
