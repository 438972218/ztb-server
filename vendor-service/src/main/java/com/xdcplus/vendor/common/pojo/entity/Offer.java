package com.xdcplus.vendor.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 出价记录
 *
 * @author Rong.Jia
 * @since 2021-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_offer")
public class Offer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 金额
     */
    private String money;

    /**
     * 出价时间
     */
    private Long offerTime;

    /**
     * 出价人
     */
    private String offerUser;

    /**
     * 表单ID
     */
    private Long requestId;

    /**
     * 出价物标识
     */
    private String offerGoods;

    /**
     * 供应商ID
     */
    private Long vendorId;

    /**
     * 添加人
     */
    private String createdUser;

    /**
     * 添加时间
     */
    private Long createdTime;

    /**
     * 修改人
     */
    private String updatedUser;

    /**
     * 修改时间
     */
    private Long updatedTime;

    /**
     * 藐视
     */
    private String description;


}
