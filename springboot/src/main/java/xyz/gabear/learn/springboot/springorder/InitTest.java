package xyz.gabear.learn.springboot.springorder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

// @Component
@Slf4j
public class InitTest implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.warn(bean.toString() + ", " + beanName);
        if (beanName.equalsIgnoreCase(Test.class.getSimpleName())) {
            log.warn("BeanPostProcessor, postProcessBeforeInitialization...");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equalsIgnoreCase(Test.class.getSimpleName())) {
            log.warn("BeanPostProcessor, postProcessAfterInitialization...");
        }
        return bean;
    }
}
