package com.xdcplus.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

/**
 * Spring 启动后的操作
 *
 * @author Fish.Fei
 * @date 2021/05/24 09:24
 */
@Component
public class SourcingApplicationRunner implements ApplicationRunner {

    @Autowired
    private TaskExecutor taskExecutor;

    @Override
    public void run(ApplicationArguments args) {

    }
}
