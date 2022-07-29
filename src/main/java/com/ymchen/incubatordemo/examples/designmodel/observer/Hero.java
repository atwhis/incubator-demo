package com.ymchen.incubatordemo.examples.designmodel.observer;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Hero {

    private List<AbstractObserver> observers = new ArrayList<AbstractObserver>();

    public void addObserver(AbstractObserver observer) {
        observers.add(observer);
    }

    public void delObserver(AbstractObserver observer) {
        observers.remove(observer);
    }

    public void move() {
        log.info("hero move.....");
        notifysAll();
    }

    public void notifysAll() {
        if (null != observers && !observers.isEmpty()) {
            observers.forEach(observer -> observer.move());
        } else {
            log.info("not observer found.....");
        }
    }

}
