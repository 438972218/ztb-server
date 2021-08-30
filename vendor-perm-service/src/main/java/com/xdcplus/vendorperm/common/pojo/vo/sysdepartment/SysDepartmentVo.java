package com.xdcplus.vendorperm.common.pojo.vo.sysdepartment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xdcplus.vendorperm.common.pojo.vo.sysemployee.SysEmployeeVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 部门表(SysDepartment)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:10
 */
@Data
@ApiModel("部门Vo")
public class SysDepartmentVo implements Serializable {
    private static final long serialVersionUID = 240653071251559074L;
    /**
     * 主键
     */
    private Long id;
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

    private String shortName;

    private String fullName;

    private Long companyId;


    private Long parentId;

    private SysEmployeeVo managerVo;

    /**
     *
     */
    private String code;
    /**
     * 部门负责人
     */
    private Long manager;
    @ApiModelProperty("children")
    @JsonInclude(value=JsonInclude.Include.NON_NULL)
    protected List<SysDepartmentVo> children;


}
