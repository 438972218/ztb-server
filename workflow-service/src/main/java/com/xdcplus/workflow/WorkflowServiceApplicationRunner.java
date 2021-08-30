package com.xdcplus.workflow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

/**
 * Spring 启动后的操作
 *
 * @author Rong.Jia
 * @date 2021/05/24 09:24
 */
@Slf4j
@Component
public class WorkflowServiceApplicationRunner implements ApplicationRunner {

    @Autowired
    private TaskExecutor taskExecutor;

    @Override
    public void run(ApplicationArguments args) {

        log.info("InteractionEngineApplication stater .....");

    }
}
