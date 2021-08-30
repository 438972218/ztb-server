package com.xdcplus.permission.remote;

import com.xdcplus.permission.remote.fallback.EmailRemoteFallbackFactory;
import com.xdcplus.permission.remote.pojo.dto.RegisterNotificationDTO;
import com.xdcplus.ztb.common.tool.constants.ServiceConstant;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 权限收放调用
 *
 * @author Rong.Jia
 * @date 2021/08/03 16:21:01
 */
@Component
@RequestMapping("/email")
@FeignClient(value = ServiceConstant.WORKFLOW_SERVICE, fallbackFactory = EmailRemoteFallbackFactory.class)
public interface EmailRemote {
    /**
     * 发送邮件通知
     *
     * @param registerNotificationDTO 邮件通知dto
     * @return {@link ResponseVO<String>}
     */
    @ApiOperation("")
    @PostMapping(value = "notification/register", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseVO<String> sendRegisterNotification(@RequestBody RegisterNotificationDTO registerNotificationDTO);


}
