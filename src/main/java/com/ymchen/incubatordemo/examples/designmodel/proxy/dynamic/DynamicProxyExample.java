package com.ymchen.incubatordemo.examples.designmodel.proxy.dynamic;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Slf4j
public class DynamicProxyExample {

    public static void main(String[] args) {
        House zhangSan = new ZhangSan();

        House house = (House) Proxy.newProxyInstance(zhangSan.getClass().getClassLoader(), zhangSan.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                log.info("Agent before buyHouse");
                method.invoke(zhangSan, args);
                log.info("Agent after buyHouse");
                return null;
            }
        });

        house.buyHouse();
    }

}
