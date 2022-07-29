package com.ymchen.incubatordemo.examples.designmodel.chain;

public class ChainExample {

    public static void main(String[] args) {
        DirectLeader directLeader = new DirectLeader();
        DeptLeader deptLeader = new DeptLeader();
        GmLeader gmLeader = new GmLeader();

        directLeader.approve("张三");


        directLeader.setNextApprove(deptLeader);
        directLeader.approve("张三");

        deptLeader.setNextApprove(gmLeader);
        directLeader.approve("张三");
    }
}
