package com.xdcplus.ztb.common.remote.domain.perm.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 *
 * @author Rong.Jia
 * @date 2021/05/21 16:22
 */
@Data
public class UserPermVO implements Serializable {

    private static final long serialVersionUID = -3399425957755562453L;

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 名称（昵称或者真实姓名）
     */
    private String name;

    /**
     * 账号
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 数据来源类型，1- 系统添加, 2- ldap同步
     * ，默认 1
     */
    private Byte sourceType;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 账户是否被锁定(0:启用,1:锁定)
     */
    private Byte lockStatus;

    /**
     * 员工id
     */
    private Long employeeId;

    /**
     * 用户 -- 角色 ： 1对多
     * 立即从数据库中进行加载数据;
     */
    private List<Role> roles;

    /**
     * 角色ids
     */
    private List<Long> roleIds;

    /**
     * 帐号状态（0正常 1冻结）
     */
    private Byte status;

    @Data
    public static class Role implements Serializable {

        /**
         * 角色ID
         */
        private Long id;

        /**
         * 角色名称
         */
        private String name;

        /**
         * 权限资源列表 数组结构
         */
        private List<Permission> permissions;

        /**
         * 权限ids
         */
        private List<Long> permissionIds;

        @Data
        public static class Permission implements Serializable {

            /**
             * 权限
             */
            private Long id;

            /**
             * 父级id (若为顶级：0)
             */
            private Long parentId;

            /**
             * 功能的级别('menu','button')
             */
            private String level;

            /**
             * 权限名称
             */
            private String name;

            /**
             * 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
             */
            private String permission;

            /**
             * 路由地址
             */
            private String routeUrl;

            /**
             * 图标
             */
            private String icon;

            /**
             * 排序
             */
            private Integer sort;

            /**
             * 是否隐藏（0:隐藏，1：显示）
             */
            private Byte hide;


        }


    }


}
