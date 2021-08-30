package com.xdcplus.permission.service;

import com.xdcplus.permission.common.pojo.dto.syssecuritysettings.SysSecuritySettingsDto;
import com.xdcplus.permission.common.pojo.vo.syssecuritysettings.SysSecuritySettingsVo;

/**
 * 安全设置表(SysSecuritySettings)表服务接口
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:06
 */
public interface SysSecuritySettingsService {


    /**
     * 查询通过id
     *
     * @return {@link SysSecuritySettingsVo}
     */
    SysSecuritySettingsVo getSysSecuritySettings();

    /**
     * 保存系统安全设置
     *
     * @param sysSecuritySettingsDto 系统安全设置dto
     * @param loginUser              登录用户
     */
    void saveSysSecuritySettings(SysSecuritySettingsDto sysSecuritySettingsDto, String loginUser);






}
