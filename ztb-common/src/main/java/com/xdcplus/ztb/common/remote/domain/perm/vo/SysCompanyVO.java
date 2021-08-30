package com.xdcplus.ztb.common.remote.domain.perm.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 公司VO
 *
 * @author Rong.Jia
 * @date 2021/08/04 14:27:29
 */
@Data
public class SysCompanyVO implements Serializable {

    private static final long serialVersionUID = -2545757993364195692L;

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
     * 公司类型
     */
    private String type;

    private Boolean disabled;


    /**
     * 上级组织
     */
    private Long parentId;

    /**
     * 上级组织公司类型
     */
    private Integer parentCompanyType;

    private String parentName;
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


    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    protected List<SysCompanyVO> children;

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

    /**
     * 公司地址
     */
    private String address;


}
