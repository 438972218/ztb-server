package com.xdcplus.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 招标单内容明细（报价须知、国内报价、国外报价）(BidDetail)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-08-27 11:26:48
 */
@Data
@SuppressWarnings("serial")
public class BidDetailQuery implements Serializable {
    private static final long serialVersionUID = 822095510843761719L;

    private Long id;

    private Long bidSheetId;

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
