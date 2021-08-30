package com.xdcplus.vendorperm.common.pojo.dto.syscompany;

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
@ApiModel("分部(公司)过滤查询Dto")
public class SysCompanyFilterDto extends PageDTO implements Serializable {
    private static final long serialVersionUID = -71918126685051943L;
    /**
     * 公司/分部 简称
     */
    @ApiModelProperty("公司/分部 简称,支持模糊查询")
    private String shortName;
    /**
     * 公司/分部全称
     */
    @ApiModelProperty("公司/分部全称,支持模糊查询")
    private String fullName;
    /**
     * 上级组织
     */
    @ApiModelProperty("上级组织")
    private Long parentId;
    /**
     * 编号（唯一性，手动输入）
     */
    @ApiModelProperty("编号（唯一性，手动输入）")
    private String code;
    /**
     * 负责人
     */
    @ApiModelProperty("负责人,支持模糊查询")
    private String principal;


}
