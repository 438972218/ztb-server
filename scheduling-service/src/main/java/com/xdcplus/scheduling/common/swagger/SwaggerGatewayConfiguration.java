package com.xdcplus.scheduling.common.swagger;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * swagger网关配置
 *
 * @author Rong.Jia
 * @date 2021/07/28 08:52:48
 */
@Configuration
@EnableConfigurationProperties(SwaggerGatewayProperties.class)
public class SwaggerGatewayConfiguration {

    private final SwaggerGatewayProperties swaggerGatewayProperties;

    public SwaggerGatewayConfiguration(SwaggerGatewayProperties swaggerGatewayProperties) {
        this.swaggerGatewayProperties = swaggerGatewayProperties;
    }

    @Bean
    @Primary
    public GatewaySwaggerProvider gatewaySwaggerProvider(RouteLocator routeLocator,
                                                         GatewayProperties gatewayProperties) {
        return new GatewaySwaggerProvider(routeLocator,gatewayProperties, swaggerGatewayProperties);
    }

    @Bean
    public SwaggerHeaderFilter swaggerHeaderFilter(){
        return new SwaggerHeaderFilter(swaggerGatewayProperties);
    }




}
