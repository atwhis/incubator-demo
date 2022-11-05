package com.ymchen.incubatordemo.examples.liteflow;


public enum PriceTypeEnum {
    INIT("init", "初始化"),
    COUPON("coupon", "优惠卷"),
    DISCOUNT("discount", "折扣"),
    FREEINTEREST("freeInterest", "免息"),
    FREEPOST("freePost", "包邮"),
    FULLREDUCE("fullReduce", "满减"),
    ;

    private String name;

    private String desc;

    public String getDescribe() {
        return this.name + "[" + this.desc + "]";
    }

    PriceTypeEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
