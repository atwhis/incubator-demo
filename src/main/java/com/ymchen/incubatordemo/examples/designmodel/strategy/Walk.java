package com.ymchen.incubatordemo.examples.designmodel.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Walk implements Transport {
    @Override
    public void goOut(String name) {
        log.info("{}:goOut by Walk", name);
    }
}
