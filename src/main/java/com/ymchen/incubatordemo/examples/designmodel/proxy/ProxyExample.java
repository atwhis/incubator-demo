package com.ymchen.incubatordemo.examples.designmodel.proxy;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class ProxyExample {

    public static void main(String[] args) {
        ZhangSan zhangSan = new ZhangSan();
        Agent agent = new Agent();
        agent.setZhangSan(zhangSan);

        agent.buyHouse();
    }

}
