package com.xdcplus.ztb.common.auto;

import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  定时器连接池
 * @date 2019/6/11 20:45:22
 * @author Rong.Jia
 */
@Configuration
@ConditionalOnClass({DispatcherServlet.class, WebMvcConfigurer.class})
public class ScheduledConfig {

    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(NumberConstant.SIXTEEN);
        taskScheduler.initialize();
        return taskScheduler;
    }

}
