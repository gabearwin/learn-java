package xyz.gabear.learn.javase.jms.spring.producer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppProducer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("producer.xml");
        ProducerService service = context.getBean(ProducerService.class);
        for (int i = 0; i < 100; i++) {
            service.sendMessage("发送消息" + i);
        }
        context.close();
    }
}
