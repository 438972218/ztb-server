package com.xdcplus.scheduling.common.authorization;

import com.xdcplus.scheduling.common.utils.WebUtils;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
public class CustomAccessDeniedHandler implements ServerAccessDeniedHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException e) {

        log.error("CustomAccessDeniedHandler {}", e.getMessage());

        ServerHttpResponse response = exchange.getResponse();
        return WebUtils.writeFailedToResponse(response, ResponseEnum.UNAUTHORIZED);
    }
}
