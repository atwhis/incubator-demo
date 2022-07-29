package com.ymchen.incubatordemo.examples.designmodel.singleton;

public class LanHan1 {

    private static LanHan1 lanHan1;

    private LanHan1() {

    }

    // lock whole method  , low efficient
    public static synchronized LanHan1 getInstance() {
        if (null == lanHan1) {
            lanHan1 = new LanHan1();
        }
        return lanHan1;
    }
}
