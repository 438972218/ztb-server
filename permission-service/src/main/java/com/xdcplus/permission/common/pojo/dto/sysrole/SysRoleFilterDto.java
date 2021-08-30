package com.xdcplus.permission.common.pojo.dto.sysrole;

import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 角色表(SysRole)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:08:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("角色过滤查询 参数对照对象")
public class SysRoleFilterDto extends PageDTO implements Serializable {
    private static final long serialVersionUID = -16306813213510891L;
    /**
     * 角色名称
     */

    @ApiModelProperty("角色名称,支持模糊查询")
    private String name;
    /**
     * 所属分部（公司）
     */
    @ApiModelProperty("所属分部（公司）")
    private Long companyId;

}
