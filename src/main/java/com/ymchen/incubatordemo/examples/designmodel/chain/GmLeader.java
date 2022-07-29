package com.ymchen.incubatordemo.examples.designmodel.chain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GmLeader extends AbstractApprove {

    @Override
    protected void handle(String name) {
        log.info("gmLeader handle {}'s approve.....", name);
    }
}
