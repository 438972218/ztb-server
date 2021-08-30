package com.xdcplus.vendorperm.common.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 分部表(公司表)(SysCompany)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysCompany implements Serializable {
    private static final long serialVersionUID = -71918126685051943L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 公司/分部 简称
     */
    private String shortName;
    /**
     * 公司/分部全称
     */
    private String fullName;
    /**
     * 上级组织
     */
    private Long parentId;

    /**
     * 上级组织
     */
    private String parentCode;
    /**
     * 网站
     */
    private String website;
    /**
     * 编号（唯一性，手动输入）
     */
    private String code;
    /**
     * 负责人
     */
    private String principal;
    /**
     * 负责人电话
     */
    private String principalPhone;
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
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 是否已经逻辑删除（0：未删除 1：已删除）
     */
    private Integer deleted;

    /**
     * 开户银行
     */
    private String bankAccount;

    /**
     * 开户银行账号信息
     */
    private String bankAccountNumber;

    /**
     * 公司类型:(0:集团，1：公司)
     */
    private Integer companyType;

    private String address;


}
