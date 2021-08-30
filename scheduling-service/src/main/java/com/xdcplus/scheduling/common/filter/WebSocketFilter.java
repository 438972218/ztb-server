package com.xdcplus.scheduling.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * 拦截第一次WebSocket请求，做特殊处理
 *
 * @author Rong.Jia
 * @date 2021/08/18 11:00:45
 */
@Component
@Slf4j
public class WebSocketFilter implements GlobalFilter, Ordered {

    private final static String DEFAULT_FILTER_PATH = "/imServer/info";
    private static final List<String> webSocket_scheme = Arrays.asList("");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.debug("============== WebSocketFilter =========================");

        String upgrade = exchange.getRequest().getHeaders().getUpgrade();
        URI requestUrl = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
        String scheme = requestUrl.getScheme();

        if (!"ws".equals(scheme) && !"wss".equals(scheme)) {
            return chain.filter(exchange);
        } else if (DEFAULT_FILTER_PATH.equals(requestUrl.getPath())) {
            String wsScheme = convertWsToHttp(scheme);
            URI wsRequestUrl = UriComponentsBuilder.fromUri(requestUrl).scheme(wsScheme).build().toUri();
            exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, wsRequestUrl);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 300;
    }

    /**
     * ws转换成http
     *
     * @param scheme 协议类型
     * @return {@link String}
     */
    private static String convertWsToHttp(String scheme) {
        scheme = scheme.toLowerCase();
        return "ws".equals(scheme) ? "http" : "wss".equals(scheme) ? "https" : scheme;
    }


}
