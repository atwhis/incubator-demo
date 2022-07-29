package com.ymchen.incubatordemo.examples.disruptor.example1;


import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentEventClearHandler implements EventHandler<StudentEvent> {
    @Override
    public void onEvent(StudentEvent event, long sequence, boolean endOfBatch) {
        log.info("doing studentEventClearHandler");
        event.clear();
    }
}
