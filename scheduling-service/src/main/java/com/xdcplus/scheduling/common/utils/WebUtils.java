package com.xdcplus.scheduling.common.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.json.JSONUtil;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

/**
 *  WEB 工具类
 * @author Rong.Jia
 * @date 2021/05/19 13:30
 */
public class WebUtils {

    public static Mono<Void> writeFailedToResponse(ServerHttpResponse response, ResponseEnum responseEnum){
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getHeaders().set("Access-Control-Allow-Origin", "*");
        response.getHeaders().set("Cache-Control", "no-cache");
        String body = JSONUtil.toJsonStr(ResponseVO.error(responseEnum));
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(CharsetUtil.CHARSET_UTF_8));
        return response.writeWith(Mono.just(buffer))
                .doOnError(error -> DataBufferUtils.release(buffer));
    }

}
