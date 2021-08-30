package com.xdcplus.vendorperm.controller;

import com.xdcplus.ztb.common.mp.controller.AbstractController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色权限组信息表(SysUserRole)表控制层
 *
 * @author Bullion.Yan
 * @since 2021-07-08 09:18:21
 */
@Slf4j
@Validated
@RestController
@RequestMapping("sysUserRole")
@Api(value = "", tags = " ")
public class SysUserRoleController extends AbstractController {

}
