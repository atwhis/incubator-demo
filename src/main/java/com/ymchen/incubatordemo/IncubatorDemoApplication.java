package com.ymchen.incubatordemo;

import com.ymchen.external.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@SpringBootApplication
@EnableAsync
@ComponentScan(value = {"com.ymchen.external", "com.ymchen.incubatordemo"})
public class IncubatorDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(IncubatorDemoApplication.class, args);
//        TestService testService = configurableApplicationContext.getBean("testService", TestService.class);
//        log.info(testService.testEnhancer("zhangSan"));
//        log.info(testService.testEnhancer2("zhangSan2"));
//        configurableApplicationContext.close();

        // test spring default scan path
        MovieService movieService = configurableApplicationContext.getBean("movieService", MovieService.class);
        movieService.getMovieInfo();
    }

}
