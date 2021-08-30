package com.xdcplus.scheduling.common.authorization;

import com.xdcplus.scheduling.common.utils.WebUtils;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * token无效或者已过期自定义响应
 * @author Rong.Jia
 */
@Slf4j
public class RestAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {
    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {

        log.error("RestAuthenticationEntryPoint {}", e.getMessage());

        ServerHttpResponse response = exchange.getResponse();
        return WebUtils.writeFailedToResponse(response, ResponseEnum.NOT_LOGGED_IN);
    }
}
