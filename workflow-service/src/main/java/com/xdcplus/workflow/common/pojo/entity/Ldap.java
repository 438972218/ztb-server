package com.xdcplus.workflow.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ldap信息
 *
 * @author Rong.Jia
 * @date  2021-06-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("xdc_t_ldap")
public class Ldap implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 是否开启, 0：关闭，1：开启
     */
    private Byte enabled;

    /**
     * 类型（AD：1，OpenLdap: 2, SUN ONE :3 ）
     */
    private Byte type;

    /**
     * Ldap 地址
     */
    private String address;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 同步域
     */
    private String domain;

    /**
     *  顶级OU
     */
    private String baseOu;

    /**
     * 是否同步组织机构
     */
    private Byte syncOrganization;

    /**
     * 是否同步人员
     */
    private Byte syncPerson;

    /**
     * 同步条件
     */
    private String conditions;

    /**
     * 同步频率，格式：秒,分,时,天,月,周,年， 没有则-1 补位
     */
    private String frequency;

    /**
     * 添加时间
     */
    private Long createdTime;

    /**
     * 修改时间
     */
    private Long updatedTime;

    /**
     * 添加人
     */
    private String createdUser;

    /**
     * 修改人
     */
    private String updatedUser;

    /**
     * 描述
     */
    private String description;


}
