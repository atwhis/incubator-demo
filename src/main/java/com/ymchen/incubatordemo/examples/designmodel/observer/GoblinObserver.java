package com.ymchen.incubatordemo.examples.designmodel.observer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GoblinObserver implements AbstractObserver {

    @Override
    public void move() {
        log.info("goblin move.....");
    }
}
