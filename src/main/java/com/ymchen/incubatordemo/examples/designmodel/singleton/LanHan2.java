package com.ymchen.incubatordemo.examples.designmodel.singleton;

public class LanHan2 {

    private static volatile LanHan2 lanHan2;

    private LanHan2() {

    }

    // double check
    public static LanHan2 getInstance() {
        if (null == lanHan2) {
            synchronized (LanHan2.class) {
                if (lanHan2 == null) {
                    lanHan2 = new LanHan2();
                }
            }
        }
        return lanHan2;
    }
}
