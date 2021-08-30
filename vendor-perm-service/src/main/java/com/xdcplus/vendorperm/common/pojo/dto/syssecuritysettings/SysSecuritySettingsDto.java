package com.xdcplus.vendorperm.common.pojo.dto.syssecuritysettings;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 安全设置表(SysSecuritySettings)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("安全设置 参数对照对象")
public class SysSecuritySettingsDto implements Serializable {
    private static final long serialVersionUID = 382133632247734092L;

    /**
     * 密码最小长度
     */
    @ApiModelProperty("密码最小长度")
    private Integer passwordMinLength;
    /**
     * 密码失败次数限制
     */
    @ApiModelProperty("密码失败次数限制")
    private Integer passwordFailNumberLimit;

}
