package com.xdcplus.permission.common.pojo.vo.sysuser;

import com.xdcplus.permission.common.pojo.vo.sysemployee.SysEmployeeVo;
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
    /**
     * 部门经理的用户信息
     */
    @ApiModelProperty("部门经理的用户信息")
    private SysUserVo sysUserVo;
    /**
     * 部门经理的员工信息
     */
    @ApiModelProperty("部门经理的员工信息")
    private SysEmployeeVo sysEmployeeVo;
}
