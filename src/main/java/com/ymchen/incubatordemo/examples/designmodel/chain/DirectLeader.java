package com.ymchen.incubatordemo.examples.designmodel.chain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DirectLeader extends AbstractApprove {

    @Override
    protected void handle(String name) {
        log.info("directLeader handle {}'s approve.....", name);
    }
}
