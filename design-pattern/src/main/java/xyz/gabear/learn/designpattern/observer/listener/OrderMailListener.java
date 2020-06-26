package xyz.gabear.learn.designpattern.observer.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;
import xyz.gabear.learn.designpattern.observer.event.OrderCreateEvent;

@Component
public class OrderMailListener implements SmartApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("发送邮件消息成功");
    }

    // 指定监听的哪个事件
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == OrderCreateEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return true;
    }

    // 多个监听器，指定执行顺序
    @Override
    public int getOrder() {
        return 0;
    }
}
