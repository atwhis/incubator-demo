package com.ymchen.incubatordemo.examples.designmodel.chain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public abstract class AbstractApprove {

    private AbstractApprove nextApprove;

    final void approve(String name) {
        handle(name);
        if (null != nextApprove) {
            nextApprove.approve(name);
        } else {
            log.info("approve end.....");
        }
    }

    protected abstract void handle(String name);
}
