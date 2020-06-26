package xyz.gabear.learn.designpattern.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import xyz.gabear.learn.designpattern.observer.event.OrderCreateEvent;

@Service
public class OrderService {
    @Autowired
    ApplicationContext applicationContext;

    public void saveOrder() {
        // applicationContext.getBean();
        applicationContext.publishEvent(new OrderCreateEvent(new Integer(22)));
    }
}
