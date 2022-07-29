package com.ymchen.incubatordemo.examples.designmodel.proxy;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Agent implements House {
    private ZhangSan zhangSan;

    @Override
    public void buyHouse() {
        log.info("Agent before buyHouse");
        zhangSan.buyHouse();
        log.info("Agent after buyHouse");
    }
}
