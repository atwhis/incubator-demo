package com.ymchen.incubatordemo.examples.designmodel.singleton;

public class StaticModel {

    private StaticModel() {

    }

    private static class Instance {
        private static final StaticModel staticModel=new StaticModel();
    }

    public StaticModel getInstance() {
        return Instance.staticModel;
    }
}
