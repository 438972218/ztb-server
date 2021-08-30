package com.xdcplus.permission.remote.fallback;

import com.xdcplus.permission.remote.EmailRemote;
import com.xdcplus.permission.remote.pojo.dto.RegisterNotificationDTO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 权限收放降级处理
 *
 * @author Rong.Jia
 * @date 2021/08/03 16:27:47
 */
@Slf4j
@Component
public class EmailRemoteFallbackFactory implements FallbackFactory<EmailRemote> {

    @Override
    public EmailRemote create(Throwable cause) {
        return new EmailRemote() {
            @Override
            public ResponseVO<String> sendRegisterNotification(RegisterNotificationDTO registerNotificationDTO) {

                log.error("sendRegisterNotification {}", cause.getMessage());

                return ResponseVO.success();
            }
        };
    }










}
