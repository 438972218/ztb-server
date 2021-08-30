package com.xdcplus.vendorperm.controller;

import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.vendorperm.common.validator.groupvlidator.UpdateGroupValidator;
import com.xdcplus.vendorperm.common.pojo.dto.syssecuritysettings.SysSecuritySettingsDto;
import com.xdcplus.vendorperm.common.pojo.entity.SysRole;
import com.xdcplus.vendorperm.common.pojo.vo.syssecuritysettings.SysSecuritySettingsVo;
import com.xdcplus.vendorperm.service.SysSecuritySettingsService;
import com.xdcplus.ztb.common.mp.utils.AuthUtils;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 安全设置表(SysSecuritySettings)表控制层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:06
 */
@Slf4j
@Validated
@RestController
@RequestMapping("sysSecuritySettings")
@Api(value = "安全设置管理", tags = "安全设置管理")
public class SysSecuritySettingsController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private SysSecuritySettingsService sysSecuritySettingsService;


    /**
     * 获取权限设置信息
     *
     * @return {@link SysRole}
     */
    @ApiOperation("获取权限设置信息")
    @PostMapping(value = "getSysSecuritySettings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<SysSecuritySettingsVo> getSysSecuritySettings() {
        return ResponseVO.success(this.sysSecuritySettingsService.getSysSecuritySettings());
    }
    /**
     * 保存安全设置
     *
     * @param sysSecuritySettingsDto 系统安全设置dto
     * @return {@link ResponseVO}
     */
    @ApiOperation("保存安全设置")
    @PostMapping(value = "saveSysSecuritySettings", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO saveSysSecuritySettings(@RequestBody @Validated(UpdateGroupValidator.class)SysSecuritySettingsDto sysSecuritySettingsDto){
        log.info("saveSysSecuritySettings:{}", sysSecuritySettingsDto.toString());
        sysSecuritySettingsService.saveSysSecuritySettings(sysSecuritySettingsDto, AuthUtils.getCurrentUser());
        return ResponseVO.success();
    }

}
