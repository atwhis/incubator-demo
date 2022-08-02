package com.ymchen.incubatordemo.examples.beanlifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * bean lifecycle : instance--->populate attribute(spring bean attribute)--->initialization--->destroy
 */

@Slf4j
@Component
public class LifecycleExample implements BeanFactoryPostProcessor, BeanPostProcessor, ApplicationContextAware {

    private static ApplicationContext applicationContext;


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        log.info(">>> lifecycle example beanFactory 后置处理器执行....");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info(">>> set applicationContext invoke");
        LifecycleExample.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("testService".equals(beanName)) {
            log.info(">>> testService  bean 初始化前的后置处理器执行....");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("testService".equals(beanName)) {
            log.info(">>> testService  bean 初始化后的后置处理器执行....");
        }
        return bean;
    }
}
