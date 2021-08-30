package com.xdcplus.vendorperm.common.pojo.query.syscompany;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("分部过滤查询 参数对照对象")
public class SysCompanyFilterQuery extends PageDTO implements Serializable {
    private static final long serialVersionUID = -71918126685051943L;
    @ApiModelProperty("公司地址")
    private String address;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;
    /**
     * 公司/分部 简称
     */
    @ApiModelProperty("公司/分部 简称")
    private String shortName;
    /**
     * 公司/分部全称
     */
    @ApiModelProperty("公司/分部全称")
    private String fullName;
    /**
     * 上级组织
     */
    @ApiModelProperty("上级组织")
    private Long parentId;
    /**
     * 网站
     */
    @ApiModelProperty("网站")
    private String website;
    /**
     * 编号（唯一性，手动输入）
     */
    @ApiModelProperty("编号（唯一性，手动输入）")
    private String code;
    /**
     * 负责人
     */
    @ApiModelProperty("负责人")
    private String principal;
    /**
     * 负责人电话
     */
    @ApiModelProperty("负责人电话")
    private String principalPhone;
    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createdUser;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Long createdTime;
    /**
     * 更新人
     */
    @ApiModelProperty("更新人")
    private String updatedUser;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Long updatedTime;
    /**
     * 开户银行
     */
    private String bankAccount;

    /**
     * 开户银行账号信息
     */
    private String bankAccountNumber;


}
