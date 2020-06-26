package xyz.gabear.learn.javase.jms.spring.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumerMessageListener implements MessageListener {
    private static Logger logger  = LoggerFactory.getLogger(ConsumerMessageListener.class);
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            logger.info("receive message: "+textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
