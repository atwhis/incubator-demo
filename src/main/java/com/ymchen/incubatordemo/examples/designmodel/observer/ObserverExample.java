package com.ymchen.incubatordemo.examples.designmodel.observer;

public class ObserverExample {

    public static void main(String[] args) {
        GoblinObserver goblinObserver = new GoblinObserver();
        SlimeObserver slimeObserver = new SlimeObserver();

        Hero hero = new Hero();

        hero.move();

        hero.addObserver(goblinObserver);
        hero.move();

        hero.addObserver(slimeObserver);
        hero.move();

        hero.delObserver(goblinObserver);
        hero.move();
    }
}
