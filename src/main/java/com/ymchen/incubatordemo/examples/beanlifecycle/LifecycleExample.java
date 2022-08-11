package com.ymchen.incubatordemo.examples.beanlifecycle;

import com.ymchen.incubatordemo.examples.beanlifecycle.service.TestService;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * bean lifecycle : instance--->populate attribute(spring bean attribute)--->initialization--->destroy
 */

@Slf4j
@Component
public class LifecycleExample implements BeanFactoryPostProcessor, BeanPostProcessor, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static final Map<String, Object> TEST_MAP = new ConcurrentHashMap<>(128);


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        log.info(">>> lifecycle example beanFactory 后置处理器执行....");

        /*Arrays.stream(configurableListableBeanFactory.getBeanNamesForType(TestService.class)).forEach((n) -> {
            Class<? extends TestService> clazz = (Class<? extends TestService>) configurableListableBeanFactory.getType(n);
            TEST_MAP.put("test", clazz);
        });*/
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

        if (bean instanceof TestService) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(bean.getClass());
            enhancer.setCallback((MethodInterceptor) (o, method, args, proxy) -> invokeService(bean, method, args, proxy));
            return enhancer.create();
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

    /**
     * cglib对bean做增强
     *
     * @param bean
     * @param method
     * @param args
     * @param proxy
     * @return
     * @throws Throwable
     */
    private Object invokeService(Object bean, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        try {
            log.info(">>> testService testEnhancer before....");
            Object result = proxy.invoke(bean, args);
            log.info(">>> testService testEnhancer after....");
            return result;
        } catch (Throwable ex) {
            log.info(">>>enhancer exception invoke");
            throw ex;
        } finally {
            log.info(">>>enhancer finally invoke");
        }
    }
}
