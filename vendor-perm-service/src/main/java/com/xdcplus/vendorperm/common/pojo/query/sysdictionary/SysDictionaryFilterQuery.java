package com.xdcplus.vendorperm.common.pojo.query.sysdictionary;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
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
@ApiModel("过滤查询 参数对照对象")
public class SysDictionaryFilterQuery extends PageDTO implements Serializable {
    private static final long serialVersionUID = -72739753643702191L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 字典中文名称
     */
    private String dictionaryChinese;
    /**
     * 字典类别
     */
    private String dictionaryClass;
    /**
     * 字典的意思及描述
     */
    private String meaning;
    /**
     * 字典的数值
     */
    private Integer numerical;
    /**
     * 创建人
     */
    private String createdUser;
    /**
     * 创建时间
     */
    private Long createdTime;
    /**
     * 更新人
     */
    private String updatedUser;
    /**
     * 更新时间
     */
    private Long updatedTime;


}
