package com.xdcplus.workflow.controller;

import com.xdcplus.workflow.common.pojo.dto.LdapDTO;
import com.xdcplus.workflow.common.pojo.vo.LdapVO;
import com.xdcplus.workflow.service.LdapService;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * ldap信息 前端控制器
 *
 * @author Rong.Jia
 * @date 2021-06-24
 */
@Slf4j
@Validated
@RestController
@Api(value = "ldap信息 管理", tags = "ldap信息 管理")
@RequestMapping("/ldap")
public class LdapController {

    @Autowired
    private LdapService ldapService;

    @ApiOperation("同步字段Ldap信息")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO syncLdap(@RequestBody LdapDTO ldapDTO) {

        log.info("syncLdap {}", ldapDTO.toString());

        ldapService.syncLdap(ldapDTO);

        return ResponseVO.success();

    }

    @ApiOperation("查询Ldap")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<LdapVO> findLdap() {

        log.info("findLdap {}", System.currentTimeMillis());

        return ResponseVO.success(ldapService.findLdap());

    }


}
