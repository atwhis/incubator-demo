package com.ymchen.incubatordemo.common.model;


public interface Calculator {

    default int cal(int a, int b) {
        return a + b;
    }

    double calculator(double a, double b);
}
