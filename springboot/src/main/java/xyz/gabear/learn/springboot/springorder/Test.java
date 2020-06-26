package xyz.gabear.learn.springboot.springorder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// @Component
@Slf4j
public class Test implements InitializingBean, ApplicationContextAware, DisposableBean {

    @PostConstruct
    public void postCon() {
        log.warn("PostConstruct...");
    }

    @PreDestroy
    public void preDes() {
        log.warn("PreDestroy...");
    }

    /**
     * InitializingBean
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        log.warn("InitializingBean, afterPropertiesSet...");
    }

    // /**
    //  * BeanPostProcessor
    //  */
    // @Override
    // public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    //     log.warn("BeanPostProcessor, postProcessBeforeInitialization...");
    //     log.warn(bean.toString() + ", " + beanName);
    //     return null;
    // }
    //
    // /**
    //  * BeanPostProcessor
    //  */
    // @Override
    // public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    //     log.warn("BeanPostProcessor, postProcessAfterInitialization...");
    //     return null;
    // }

    /**
     * ApplicationContextAware
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.warn("ApplicationContextAware, setApplicationContext...");
    }

    /**
     * DisposableBean
     */
    @Override
    public void destroy() throws Exception {
        log.warn("DisposableBean, destroy...");
    }

}

/*
 * ApplicationContextAware, setApplicationContext...
 * BeanPostProcessor, postProcessBeforeInitialization...
 * PostConstruct...
 * InitializingBean, afterPropertiesSet...
 * BeanPostProcessor, postProcessAfterInitialization...
 * PreDestroy...
 * DisposableBean, destroy...
 */

