package com.xdcplus.scheduling.common.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger 网关聚合属性
 *
 * @author Rong.Jia
 * @date 2021/07/28 08:53:16
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "swagger.gateway")
public class SwaggerGatewayProperties {

    /**
     * swagger interface version default:  3.0
     */
    private String swaggerVersion = "3.0";

    /**
     * swagger api-docs default: /v3/api-docs
     */
    private String swaggerApiDocs = "/v3/api-docs";

    /**
     *  header Name， 默认： X-Forwarded-Prefix
     */
    private String headerName = "X-Forwarded-Prefix";



}
