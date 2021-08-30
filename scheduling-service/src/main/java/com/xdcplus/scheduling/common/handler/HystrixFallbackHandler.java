package com.xdcplus.scheduling.common.handler;

import cn.hutool.json.JSONUtil;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Hystrix 服务降级处理
 * @date 2021/05/24 13:42
 * @author Rong.Jia
 */
@Slf4j
@Component
public class HystrixFallbackHandler implements HandlerFunction<ServerResponse> {
    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {

        Optional<Object> originUris = serverRequest.attribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);

        originUris.ifPresent(originUri -> log.error("网关转发请求：{}，Hystrix服务降级处理", originUri));

        String body = JSONUtil.toJsonStr(ResponseVO.error(ResponseEnum.SERVICE_EXCEPTIONS));

        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(body));
    }
}
