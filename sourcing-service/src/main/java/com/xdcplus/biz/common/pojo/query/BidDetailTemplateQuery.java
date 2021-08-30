package com.xdcplus.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 内容模板(BidDetailTemplate)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-08-30 09:07:52
 */
@Data
@SuppressWarnings("serial")
public class BidDetailTemplateQuery implements Serializable {
    private static final long serialVersionUID = -72331608274639189L;

    private Long id;

    private String classify;

    private String bidType;

    private String type;

    private String name;

    private String explaination;

    private Double quantity;

    private String unit;

    private String var1;

    private String var2;

    private String var3;

    private String var4;

    private String var5;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}
