package com.ymchen.incubatordemo.examples.disruptor.example2;


import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Handler1 implements EventHandler<StringEvent>, WorkHandler<StringEvent> {
    @Override
    public void onEvent(StringEvent event, long sequence, boolean endOfBatch) {
        log.info("doing handler1");
    }

    @Override
    public void onEvent(StringEvent event) {
        log.info(event.getStr() + "doing workHanlder1");
    }
}
