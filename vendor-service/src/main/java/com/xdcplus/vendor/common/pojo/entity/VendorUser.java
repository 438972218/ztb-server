package com.xdcplus.vendor.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldStrategy;

import java.io.Serializable;

/**
 * 供应商用户(VendorUser)表实体类
 *
 * @author Fish.Fei
 * @since 2021-08-23 17:48:28
 */
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
@TableName("scm_vendor_user")
public class VendorUser implements Serializable {
    private static final long serialVersionUID = 358629711590193072L;

    @ApiModelProperty("信息主键")
    private Long id;

    @ApiModelProperty("供应商id")
    private Long vendorId;

    @ApiModelProperty("用户id")
    private Long userId;
}
