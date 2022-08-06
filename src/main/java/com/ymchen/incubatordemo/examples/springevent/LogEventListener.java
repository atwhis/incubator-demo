package com.ymchen.incubatordemo.examples.springevent;

import com.ymchen.incubatordemo.common.IncubatorDemoConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogEventListener {

    @Async(IncubatorDemoConstant.THREAD_POOL_NAME)
    @EventListener
    public void handlerLogEvent(LogEvent logEvent) {
        log.info("handler log event:{}", logEvent);
    }
}
