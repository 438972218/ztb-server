package com.xdcplus.workflow.common.pojo.vo;

import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * ldapVO
 *
 * @author Rong.Jia
 * @date 2021/06/24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("Ldap信息 参照对象")
public class LdapVO extends BaseBO implements Serializable {

    private static final long serialVersionUID = -584435460324028193L;

    /**
     * 是否开启, 0：关闭，1：开启
     */
    @ApiModelProperty("是否开启")
    private Byte enabled;

    /**
     * 类型（AD：1，OpenLdap: 2, SUN ONE :3 ）
     */
    @ApiModelProperty("类型")
    private Byte type;

    /**
     * Ldap 地址
     */
    @ApiModelProperty("Ldap 地址")
    private String address;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 同步域
     */
    @ApiModelProperty("密码")
    private String domain;

    /**
     * 是否同步组织机构
     */
    @ApiModelProperty("是否同步组织机构")
    private Byte syncOrganization;

    /**
     * 是否同步人员
     */
    @ApiModelProperty("是否同步人员")
    private Byte syncPerson;

    /**
     * 同步条件
     */
    @ApiModelProperty("同步条件")
    private String conditions;

    /**
     * 同步频率
     */
    @ApiModelProperty("同步频率 （格式：秒,分,时,天,月,周,年 没有则-1 补位）")
    private String frequency;

    /**
     *  顶级OU
     */
    @ApiModelProperty("顶级OU")
    private String baseOu;




}
