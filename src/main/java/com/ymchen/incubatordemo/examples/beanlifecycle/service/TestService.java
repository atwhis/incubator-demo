package com.ymchen.incubatordemo.examples.beanlifecycle.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component
public class TestService implements BeanNameAware {

    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        log.info(">>> testService set method invoke...");
        this.str = str;
    }

    public TestService() {
        log.info(">>> testService construct invoke...");
    }

    @PostConstruct
    public void init() {
        log.info(">>> testService init method invoke...");
    }

    @PreDestroy
    public void destroy() {
        log.info(">>> testService destroy method invoke...");
    }

    @Override
    public void setBeanName(String name) {
        log.info(">>> testService setBeanName invoke...");
    }
}
