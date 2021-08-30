package com.xdcplus.biz.common.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 品类管理(Item)表查询条件类
 *
 * @author Fish.Fei
 * @since 2021-08-18 13:47:05
 */
@Data
@SuppressWarnings("serial")
public class ItemQuery implements Serializable {
    private static final long serialVersionUID = -86435729521671320L;

    private Long id;

    private String itemCode;

    private String itemName;

    private String genName;

    private String itemType;

    private String brand;

    private String originPlace;

    private String basicMeasure;

    private Integer ifAssitMeasure;

    private String assitMeasure;

    private Integer ifImport;

    private String agent;

    private String packageUnit;

    private String roughWeight;

    private String netWeight;

    private String weightUnit;

    private String specification;

    private String model;

    private String manufacturer;

    private String volume;

    private String volumeUnit;

    private String circulationCode;

    private Integer ifTax;

    private String defaultTaxType;

    private String defaultTaxRate;

    private String itemManagementMethods;

    private String itemQuotaManagement;

    private String batchNumberRule;

    private Integer ifUseSale;

    private Integer ifUsePurchase;

    private String productHierarchy;

    private String itemDesc;

    private String createdUser;

    private Long createdTime;

    private String updatedUser;

    private Long updatedTime;

    private String description;

    private Integer version;

    private Integer deleted;

}
