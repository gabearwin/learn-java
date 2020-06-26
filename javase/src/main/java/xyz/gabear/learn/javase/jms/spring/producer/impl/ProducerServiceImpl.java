package xyz.gabear.learn.javase.jms.spring.producer.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import xyz.gabear.learn.javase.jms.spring.producer.ProducerService;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

public class ProducerServiceImpl implements ProducerService {
    private static Logger logger = LoggerFactory.getLogger(ProducerServiceImpl.class);

    @Autowired
    private JmsTemplate jmsTemplate;
    @Resource(name = "topicDestination")
    private Destination destination;

    @Override
    public void sendMessage(final String message) {
        // 使用jmsTemplate发送消息
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(message);
                return textMessage;
            }
        });
        logger.info("success send message: " + message);
    }
}
