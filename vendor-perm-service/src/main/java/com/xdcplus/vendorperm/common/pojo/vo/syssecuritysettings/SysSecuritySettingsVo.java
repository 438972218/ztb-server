package com.xdcplus.vendorperm.common.pojo.vo.syssecuritysettings;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 安全设置表(SysSecuritySettings)实体类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:05
 */
@Data
public class SysSecuritySettingsVo implements Serializable {
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
