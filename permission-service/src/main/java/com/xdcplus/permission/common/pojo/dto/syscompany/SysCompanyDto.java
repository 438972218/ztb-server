package com.xdcplus.permission.common.pojo.dto.syscompany;

import com.xdcplus.permission.common.validator.groupvlidator.InsertGroupValidator;
import com.xdcplus.permission.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.ztb.common.tool.validator.groupvlidator.IdGroupValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 分部表(公司表)(SysCompany)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("分部(公司)Dto")
public class SysCompanyDto implements Serializable {
    private static final long serialVersionUID = -71918126685051943L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @NotNull(groups = {IdGroupValidator.class,UpdateGroupValidator.class}, message = "id 不能为空")
    private Long id;
    /**
     * 公司类型:(0:集团，01：公司)
     */
    @ApiModelProperty("公司类型:(0:集团，1：公司)")
    @NotNull(groups = {InsertGroupValidator.class,UpdateGroupValidator.class}, message = "公司类型不能为空")
    private Integer companyType;
    /**
     * 公司/分部 简称
     */
    @ApiModelProperty("公司/分部 简称")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "公司/分部简称不能为空")
    private String shortName;
    /**
     * 公司/分部全称
     */
    @ApiModelProperty("公司/分部全称")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "公司/分部全称简称不能为空")
    private String fullName;
    /**
     * 上级组织
     */
    @ApiModelProperty("上级组织")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "上级组织不能为空")
    private Long parentId;

    /**
     * 上级组织(上线code)
     */
    @ApiModelProperty("上级组织(上线code)")
    private String parentCode;
    /**
     * 网站
     */
    @ApiModelProperty("网站")
    private String website;
    /**
     * 编号（唯一性，手动输入）
     */
    @ApiModelProperty("公司编号")
    @NotNull(groups = {InsertGroupValidator.class, UpdateGroupValidator.class}, message = "公司编号不能为空")
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
    @ApiModelProperty("开户银行")
    private String bankAccount;

    /**
     * 开户银行账号信息
     */
    @ApiModelProperty("开户银行账号信息")
    private String bankAccountNumber;

    @ApiModelProperty("公司地址")
    private String address;


}
