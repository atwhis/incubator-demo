package com.ymchen.incubatordemo.examples.designmodel.singleton;

public class Ehan {

    private final static Ehan instance=new Ehan();

    private Ehan() {
    }

    public static Ehan getInstance() {
        return instance;
    }
}
