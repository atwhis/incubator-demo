package com.ymchen.incubatordemo;

import com.ymchen.incubatordemo.examples.beanlifecycle.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class IncubatorDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(IncubatorDemoApplication.class, args);
//        TestService testService = configurableApplicationContext.getBean("testService", TestService.class);
//        log.info(testService.testEnhancer("zhangSan"));
//        log.info(testService.testEnhancer2("zhangSan2"));
//        configurableApplicationContext.close();
    }

}
