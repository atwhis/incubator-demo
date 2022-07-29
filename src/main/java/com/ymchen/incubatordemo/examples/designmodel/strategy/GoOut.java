package com.ymchen.incubatordemo.examples.designmodel.strategy;

import lombok.Data;

@Data
public class GoOut {

    private Transport transport;

    public void goOut(String name) {
        transport.goOut(name);
    }
}
