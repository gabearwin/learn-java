package xyz.gabear.learn.designpattern.observer.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import xyz.gabear.learn.designpattern.observer.event.OrderCreateEvent;

@Component
public class OrderSmsListener implements ApplicationListener<OrderCreateEvent> {
    @Override
    public void onApplicationEvent(OrderCreateEvent event) {
        System.out.println("发送短信消息成功");
    }
}
