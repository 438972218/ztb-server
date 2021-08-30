package com.xdcplus.vendorperm.common.pojo.vo.syscompany;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分部表(公司表)(SysCompany)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:07
 */
@Data
@ApiModel("公司表部分整合的treeVo")
public class SysCompanyDepartmentVo implements Serializable {
    private static final long serialVersionUID = -71918126685051943L;
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
     * 上级组织
     */
    @ApiModelProperty("上级组织")
    private Long parentId;
    /**
     *
     */
    @ApiModelProperty("1:公司，2代表部门")
    private int type;
    private Boolean disabled;
    @ApiModelProperty("children")
    @JsonInclude(value=JsonInclude.Include.NON_NULL)
    protected List<SysCompanyDepartmentVo> children;
}
