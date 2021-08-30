package com.xdcplus.permission;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 项目启动类
 *
 * @author yanjinyin
 * @date 2021/06/28 10:26
 */
@EnableFeignClients
@EnableEurekaClient
@EnableTransactionManagement
@MapperScan("com.xdcplus.permission.dao")
@EnableAsync
@EnableCaching
@EnableScheduling
@SpringBootApplication
public class PermissionApplication {

    public static void main(String[] args) {
        SpringApplication.run(PermissionApplication.class, args);
    }

}
