package com.xdcplus.ztb.common.remote.domain.perm.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 根据用户名，查询部门经理的员工信息、用户信息 返回VO
 *
 * @author Bullion.Yan
 * @date 2021/08/27
 */
@Data
public class GetDepartmentManagerEmployeeVoAndSysUserVoByUserNameVO {

    private static final long serialVersionUID = -2545757993364195694L;
    /**
     * 部门经理的用户信息
     */
    @ApiModelProperty("部门经理的用户信息")
    private SysUserInfoVO sysUserVo;
    /**
     * 部门经理的员工信息
     */
    @ApiModelProperty("部门经理的员工信息")
    private SysEmployeeVO sysEmployeeVo;
}
