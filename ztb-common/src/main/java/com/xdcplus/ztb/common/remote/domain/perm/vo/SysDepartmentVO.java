package com.xdcplus.ztb.common.remote.domain.perm.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 系统部门信息
 *
 * @author Rong.Jia
 * @date 2021/08/03 17:27:00
 */
@Data
public class SysDepartmentVO implements Serializable {

    private static final long serialVersionUID = -5901436552333795718L;

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

    private SysEmployeeVO managerVo;

    /**
     *
     */
    private String code;

    /**
     * 部门负责人
     */
    private Long manager;

    @JsonInclude(value=JsonInclude.Include.NON_NULL)
    protected List<SysDepartmentVO> children;



}
