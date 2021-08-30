package com.xdcplus.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 供应商内容明细（国内报价、国外报价）(BidVendorDetail)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-08-27 11:27:30
 */
@Data
@SuppressWarnings("serial")
public class BidVendorDetailQuery implements Serializable {
    private static final long serialVersionUID = -82552383087839303L;

    private Long id;

    private Long bidSheetId;

    private Long bidVendorId;

    private String bidType;

    private String type;

    private String status;

    private Integer round;

    private String name;

    private String explaination;

    private Double quantity;

    private String unit;

    private String var1;

    private String var2;

    private String var3;

    private String var4;

    private String var5;

    private Double price;

    private Double totalPrice;

    private String etd;

    private String eta;

    private String shippingSchedule;

    private String shippingCompany;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}
