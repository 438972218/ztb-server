package com.xdcplus.ztb.common.remote.domain.perm.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统用户信息
 *
 * @author Rong.Jia
 * @date 2021/08/03 16:33:41
 */
@Data
public class SysUserInfoVO implements Serializable {

    private static final long serialVersionUID = -7668466443085040093L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 账号
     */
    private String userName;
    /**
     * 邮箱
     */
    private String mail;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 帐号状态（0正常 1冻结）
     */
    private String status;
    /**
     * 最后登陆IP
     */
    private String loginIp;
    /**
     * 最后登陆时间
     */
    private Long loginDate;
    /**
     * 账户是否被锁定(0:启用,1:锁定)
     */
    private String lockStatus;
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
     * 员工ID
     */
    private Long employeeId;

    /**
     * 角色
     */
    List<Long> sysRoleVoList=new ArrayList<>();

    /**
     * 数据来源类型，1- 系统添加, 2- ldap同步,默认是1
     */
    private String sourceType;

}
