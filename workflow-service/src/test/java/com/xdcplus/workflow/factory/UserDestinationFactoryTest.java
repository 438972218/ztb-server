package com.xdcplus.workflow.factory;

import com.xdcplus.workflow.WorkflowServiceApplicationTests;
import com.xdcplus.workflow.factory.user.UserDestinationFactory;
import com.xdcplus.workflow.factory.user.UserDestinationParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户目标工厂测试类
 *
 * @author Rong.Jia
 * @date 2021/07/19
 */
public class UserDestinationFactoryTest extends WorkflowServiceApplicationTests {

    @Autowired
    private UserDestinationFactory userDestinationFactory;

    @Test
    void postProcess() {

        UserDestinationParam userDestinationParam = UserDestinationParam.builder()
                .userToType(5L)
                .userId(1410181068562178050L)
                .build();

        Long aLong = userDestinationFactory.postProcess(userDestinationParam);
        System.out.println(aLong);

    }


}
