package com.ymchen.incubatordemo.examples.designmodel.strategy;

public class StrategyExample {

    public final static String NAME="张三";

    public static void main(String[] args) {
        GoOut goOut = new GoOut();

        Walk walk = new Walk();
        Car car = new Car();
        Fly fly = new Fly();

        goOut.setTransport(walk);
        goOut.goOut(NAME);

        goOut.setTransport(car);
        goOut.goOut(NAME);

        goOut.setTransport(fly);
        goOut.goOut(NAME);

    }
}
