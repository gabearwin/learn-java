package xyz.gabear.learn.javase.jms.java.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 主题模式-消费者
 */
public class AppConsumer {

    private static final String URL = "tcp://127.0.0.1:61616";
    private static final String TOPIC_NAME = "topic-test";

    public static void main(String[] args) throws JMSException {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
        // 2、创建Connection
        Connection connection = connectionFactory.createConnection();
        // 3、启动连接
        connection.start();
        // 4、创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 5、创建一个目标
        Destination destination = session.createTopic(TOPIC_NAME);
        // 6、创建一个消费者
        MessageConsumer consumer = session.createConsumer(destination);
        // 7、创建一个监听器
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("receive message:" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        // 8、关闭连接
        // connection.close();
    }
}
