package com.ymchen.incubatordemo.examples.disruptor.example2;


import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Handler5 implements EventHandler<StringEvent> {
    @Override
    public void onEvent(StringEvent event, long sequence, boolean endOfBatch) {
        log.info("doing handler5");
    }
}
