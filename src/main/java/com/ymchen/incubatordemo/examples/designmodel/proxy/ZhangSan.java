package com.ymchen.incubatordemo.examples.designmodel.proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ZhangSan implements House {

    @Override
    public void buyHouse() {
        log.info("ZhangSan buyHouse");
    }
}
