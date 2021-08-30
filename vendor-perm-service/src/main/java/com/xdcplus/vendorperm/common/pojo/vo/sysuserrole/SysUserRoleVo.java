package com.xdcplus.vendorperm.common.pojo.vo.sysuserrole;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserRoleVo implements Serializable {
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
     * 角色编号
     */
    private Long roleId;
    /**
     * 用户id
     */
    private Long userId;
}
