package com.xdcplus.scheduling.common.authorization;

import cn.hutool.core.convert.Convert;
import com.xdcplus.ztb.common.cache.RedisUtils;
import com.xdcplus.ztb.common.tool.constants.AuthConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 鉴权管理器
 * @author Rong.Jia
 * @date 2021/17:21
 */
@Component
@AllArgsConstructor
@Slf4j
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private final RedisUtils redisUtils;
    private final WhiteListConfig whiteListConfig;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {

        log.debug("============== AuthorizationManager =========================");

        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        // Restful接口权限设计 @link https://www.cnblogs.com/haoxianrui/p/14396990.html
        String restPath = request.getMethodValue() + "_" + request.getURI().getPath();
        log.info("请求路径：{}", restPath);

        PathMatcher pathMatcher = new AntPathMatcher();
        // 对应跨域的预检请求直接放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(Boolean.TRUE));
        }

        // 无需鉴权直接放行
        for (String url : whiteListConfig.getUrls()) {
//            if (pathMatcher.match(url, restPath)) {
            if (pathMatcher.match(url, request.getURI().getPath())) {
                log.info("请求无需鉴权，请求路径：{}", restPath);
                return Mono.just(new AuthorizationDecision(Boolean.TRUE));
            }
        }
        return Mono.just(new AuthorizationDecision(Boolean.TRUE));

/*
        // 从缓存取资源权限角色关系列表
        Map<Object, Object> permissionRoles = redisUtils.getRedisTemplate().opsForHash().entries(AuthConstant.PERMISSION_ROLES_KEY);
        Iterator<Object> iterator = permissionRoles.keySet().iterator();
        // 请求路径匹配到的资源需要的角色权限集合authorities统计
        Set<String> authorities = new HashSet<>();
        while (iterator.hasNext()) {
            String pattern = (String) iterator.next();
            if (pathMatcher.match(pattern, restPath)) {
                authorities.addAll(Convert.toList(String.class, permissionRoles.get(pattern)));
            }
        }

        //认证通过且角色匹配的用户可访问当前路径
        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(roleId -> {
                    // roleId是请求用户的角色(格式:ROLE_{roleId})，authorities是请求资源所需要角色的集合
                    log.debug("访问路径：{}", restPath);
                    log.debug("用户角色：{}", roleId);
                    log.debug("资源需要角色：{}", authorities);
                    return authorities.contains(roleId);
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(Boolean.TRUE));
*/

    }

}
