package com.xdcplus.permission.service.impl;

import cn.hutool.core.date.DateUtil;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.permission.common.enums.IsDeletedEnum;
import com.xdcplus.permission.dao.SysSecuritySettingsDao;
import com.xdcplus.permission.common.pojo.dto.syssecuritysettings.SysSecuritySettingsDto;
import com.xdcplus.permission.common.pojo.entity.SysSecuritySettings;
import com.xdcplus.permission.common.pojo.vo.syssecuritysettings.SysSecuritySettingsVo;
import com.xdcplus.permission.service.SysSecuritySettingsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 安全设置表(SysSecuritySettings)表服务实现类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:06
 */
@Service("sysSecuritySettingsService")
public class SysSecuritySettingsServiceImpl
        extends BaseServiceImpl<SysSecuritySettings, SysSecuritySettingsVo, SysSecuritySettings, SysSecuritySettingsDao>
        implements SysSecuritySettingsService {
    @Resource
    private SysSecuritySettingsDao sysSecuritySettingsDao;
    public static final Long id=Long.parseLong("0");
    @Override
    public SysSecuritySettingsVo getSysSecuritySettings() {
        SysSecuritySettings sysSecuritySettings = this.getById(id);
        if (sysSecuritySettings != null && IsDeletedEnum.NO_DELETED.getCode().equals(sysSecuritySettings.getDeleted())) {
            SysSecuritySettingsVo sysSecuritySettingsVo = this.objectConversion(sysSecuritySettings);
            return sysSecuritySettingsVo;
        }
        return new SysSecuritySettingsVo();
    }

    @Override
    public void saveSysSecuritySettings(SysSecuritySettingsDto sysSecuritySettingsDto,String loginUser) {
        SysSecuritySettings sysSecuritySettingsResult = this.getById(id);
        if(sysSecuritySettingsResult==null){
            SysSecuritySettings sysSecuritySettings = new SysSecuritySettings();
            org.springframework.beans.BeanUtils.copyProperties(sysSecuritySettingsDto, sysSecuritySettings);
            long currentTime=DateUtil.current();
            sysSecuritySettings.setId(id);
            sysSecuritySettings.setUpdatedTime(currentTime);
            sysSecuritySettings.setCreatedTime(currentTime);
            sysSecuritySettings.setUpdatedUser(loginUser);
            sysSecuritySettings.setCreatedUser(loginUser);
            sysSecuritySettings.setDeleted(IsDeletedEnum.NO_DELETED.getCode());
            this.save(sysSecuritySettings);
        }else{
            org.springframework.beans.BeanUtils.copyProperties(sysSecuritySettingsDto, sysSecuritySettingsResult);
            long currentTime=DateUtil.current();
            sysSecuritySettingsResult.setUpdatedTime(currentTime);
            sysSecuritySettingsResult.setUpdatedUser(loginUser);
            this.updateById(sysSecuritySettingsResult);
        }
    }
}
