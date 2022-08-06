package com.ymchen.incubatordemo.examples.springevent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogEventPublisher implements CommandLineRunner {

    private ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {

        LogEvent smsLogEvent = LogEvent.builder().msg("send sms").build();
        LogEvent mqLogEvent = LogEvent.builder().msg("send mq").build();
        log.info(">>>log event publish invoke......");
        applicationContext.publishEvent(smsLogEvent);
        applicationContext.publishEvent(mqLogEvent);
    }
}
