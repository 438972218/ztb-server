package com.xdcplus.workflow.common.pojo.dto;

import cn.hutool.core.util.StrUtil;
import com.xdcplus.workflow.common.utils.LdapUtils;
import com.xdcplus.ztb.common.tool.pojo.bo.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * ldapDTO
 *
 * @author Rong.Jia
 * @date 2021/06/24
 */
@ApiModel("Ldap信息 参数对照对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class LdapDTO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 1837223176346084017L;

    /**
     * 是否开启, 0：关闭，1：开启
     */
    @NotNull(message = "是否启用 不能为空")
    @ApiModelProperty(value = "是否开启", required = true)
    private Byte enabled;

    /**
     * 类型（AD：1，OpenLdap: 2, SUN ONE :3 ）
     */
    @NotNull(message = "类型 不能为空")
    @ApiModelProperty(value = "类型", required = true)
    private Byte type;

    /**
     * Ldap 地址
     */
    @NotBlank(message = "Ldap 地址 不能为空")
    @ApiModelProperty(value = "Ldap 地址", required = true)
    private String address;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名 不能为空")
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码 不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    /**
     * 同步域
     */
    @NotBlank(message = "同步域 不能为空")
    @ApiModelProperty(value = "同步域", required = true)
    private String domain;

    /**
     * 是否同步组织机构
     */
    @NotNull(message = "是否同步组织机构 不能为空")
    @ApiModelProperty(value = "是否同步组织机构", required = true)
    private Byte syncOrganization;

    /**
     * 是否同步人员
     */
    @NotNull(message = "是否同步人员 不能为空")
    @ApiModelProperty(value = "是否同步人员", required = true)
    private Byte syncPerson;

    /**
     * 同步条件
     */
    @ApiModelProperty(value = "同步条件")
    private String conditions;

    /**
     * 同步频率, 格式：秒,分,时,天,月,周,年， 没有则-1 补位
     */
    @NotBlank(message = "同步频率 不能为空")
    @ApiModelProperty(value = "同步频率 （格式：秒 分 时 天 月 周 年， 没有则-1 补位）", required = true)
    private String frequency;

    /**
     *  顶级OU
     */
    @NotBlank(message = "顶级OU 不能为空")
    @ApiModelProperty(value = "顶级OU", required = true)
    private String baseOu;


    public String getConditions() {
        if (StrUtil.isBlank(conditions)) return LdapUtils.getConditions(this.type);
        return conditions;
    }
}
