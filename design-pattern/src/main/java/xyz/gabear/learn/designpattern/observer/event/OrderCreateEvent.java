package xyz.gabear.learn.designpattern.observer.event;

import org.springframework.context.ApplicationEvent;

public class OrderCreateEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public OrderCreateEvent(Object source) {
        super(source);
    }
}
