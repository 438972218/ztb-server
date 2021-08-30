package com.xdcplus.workflow.controller;

import com.xdcplus.workflow.common.pojo.dto.*;
import com.xdcplus.workflow.common.pojo.vo.MailDeliveryVO;
import com.xdcplus.workflow.common.pojo.vo.MailTemplateVO;
import com.xdcplus.workflow.common.pojo.vo.NotificationHistoryVO;
import com.xdcplus.workflow.service.MailDeliveryService;
import com.xdcplus.workflow.service.MailTemplateService;
import com.xdcplus.workflow.service.NotificationHistoryService;
import com.xdcplus.workflow.service.NotificationService;
import com.xdcplus.ztb.common.tool.pojo.dto.PageDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 电子邮件控制器
 *
 * @author Rong.Jia
 * @date 2021/08/11 08:33:17
 */
@Slf4j
@Validated
@RestController
@Api(value = "通知管理", tags = "通知管理")
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private MailTemplateService mailTemplateService;

    @Autowired
    private MailDeliveryService mailDeliveryService;

    @Autowired
    private NotificationHistoryService notificationHistoryService;

    @Autowired
    private NotificationService notificationService;

    @ApiOperation("同步邮件模板信息")
    @PostMapping("email/template")
    public ResponseVO syncMailTemplate(@RequestBody MailTemplateDTO mailTemplateDTO) {

        log.info("syncMailTemplate {}", mailTemplateDTO.toString());

        mailTemplateService.syncMailTemplate(mailTemplateDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除邮件模板")
    @DeleteMapping(value = "email/template/{templateId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "templateId", dataType = "Long", value = "邮件模板ID", required = true),
    })
    public ResponseVO deleteEmailTemplate(@PathVariable("templateId") @NotNull(message = "邮件模板ID 不能为空") Long templateId) {

        log.info("deleteEmailTemplate {}", templateId);

        mailTemplateService.deleteTemplate(templateId);

        return ResponseVO.success();
    }

    @ApiOperation("查询邮件模板列表")
    @GetMapping(value = "email/template", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<MailTemplateVO>> findEmailTemplates(PageDTO pageDTO) {

        log.info("findEmailTemplates {}", pageDTO);

        PageVO<MailTemplateVO> pageVO = mailTemplateService.findTemplates(pageDTO);

        return ResponseVO.success(pageVO);

    }

    @ApiOperation("查询单个邮件模板信息")
    @GetMapping(value = "email/template/{templateId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "templateId", dataType = "Long", value = "邮件模板ID", required = true),
    })
    public ResponseVO<MailTemplateVO> findEmailTemplate(@PathVariable("templateId") @NotNull(message = "邮件模板ID 不能为空") Long templateId) {

        log.info("findEmailTemplate {}", templateId);

        MailTemplateVO templateVO = mailTemplateService.findOne(templateId);

        return ResponseVO.success(templateVO);
    }

    @ApiOperation("验证邮件模板存在")
    @GetMapping(value = "email/template/exist/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "title", dataType = "String", value = "邮件模板名", required = true),
    })
    public ResponseVO<Boolean> validationMailTemplateExists(@PathVariable("name")
                                                @NotBlank(message = "邮件模板名 不能为空")
                                                        String name) {

        log.info("validationMailTemplateExists {}", name);

        return ResponseVO.success(mailTemplateService.validationExists(name));
    }

    @ApiOperation("同步邮件通知点信息")
    @PostMapping("email/delivery")
    public ResponseVO syncMailDelivery(@RequestBody MailDeliveryDTO mailDeliveryDTO) {

        log.info("syncMailDelivery {}", mailDeliveryDTO.toString());

        mailDeliveryService.syncMailDelivery(mailDeliveryDTO);

        return ResponseVO.success();
    }

    @ApiOperation("删除邮件通知点信息")
    @DeleteMapping(value = "email/delivery/{deliveryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "deliveryId", dataType = "Long", value = "邮件通知点ID", required = true),
    })
    public ResponseVO deleteMailDelivery(@PathVariable("deliveryId") @NotNull(message = "邮件通知点ID 不能为空") Long deliveryId) {

        log.info("deleteMailDelivery {}", deliveryId);

        mailDeliveryService.deleteMailDelivery(deliveryId);

        return ResponseVO.success();
    }

    @ApiOperation("查询邮件通知点列表")
    @GetMapping(value = "email/delivery", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<MailDeliveryVO>> findMailDelivery(MailDeliveryFilterDTO pageDTO) {

        log.info("findMailDelivery {}", pageDTO);

        PageVO<MailDeliveryVO> pageVO = mailDeliveryService.findMailDelivery(pageDTO);

        return ResponseVO.success(pageVO);

    }

    @ApiOperation("查询邮件通知点信息")
    @GetMapping(value = "email/delivery/{point}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "point", dataType = "String", value = "邮件通知点", required = true),
    })
    public ResponseVO<MailDeliveryVO> findEmailDelivery(@PathVariable("point")
                                                   @NotNull(message = "邮件通知点 不能为空")
                                                           String point) {

        log.info("findEmailDelivery {}", point);

        MailDeliveryVO mailDeliveryVO = mailDeliveryService.findMailDelivery(point);

        return ResponseVO.success(mailDeliveryVO);
    }

    @ApiOperation("查询邮件通知历史列表")
    @GetMapping(value = "/history/email", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<NotificationHistoryVO>> findEmailHistory(NotificationHistoryFilterDTO filterDTO) {

        log.info("findHistory {}", filterDTO);

        PageVO<NotificationHistoryVO> pageVO = notificationHistoryService.findNotificationHistory(filterDTO);

        return ResponseVO.success(pageVO);

    }

    @ApiOperation("发送邮件通知")
    @PostMapping(value = "/email", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<String> emailNotification(@RequestBody ExtraMailNotificationDTO extraMailNotificationDTO) {

        log.info("emailNotification {}", extraMailNotificationDTO.toString());

        return ResponseVO.success(notificationService.emailNodeNotification(extraMailNotificationDTO));
    }





























}
