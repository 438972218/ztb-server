package com.xdcplus.permission.common.pojo.query.sysdepartment;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 部门表(SysDepartment)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("过滤查询 参数对照对象")
public class SysDepartmentFilterQuery extends PageDTO implements Serializable {
    private static final long serialVersionUID = 240653071251559074L;
    /**
     *部门简称
     */
    private String shortName;
    /**
     * 部门全称
     */
    private String fullName;
    /**
     * 所属公司id
     */
    private Long companyId;
    /**
     *上级部门Id
     */
    private Long parentId;
    /**
     * 部门编号
     */
    private String code;
    /**
     * 部门负责人
     */
    private String manager;


}
