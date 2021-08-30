package com.xdcplus.permission.controller;

import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.permission.service.SysRolePermissionGroupService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色权限组信息表(SysRolePermissionGroup)表控制层
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:08
 */
@Slf4j
@Validated
@RestController
@RequestMapping("sysRolePermissionGroup")
@Api(value = "", tags = " ")
public class SysRolePermissionGroupController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private SysRolePermissionGroupService sysRolePermissionGroupService;



}
