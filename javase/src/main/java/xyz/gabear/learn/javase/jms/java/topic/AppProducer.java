package xyz.gabear.learn.javase.jms.java.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 主题模式-生产者
 */
public class AppProducer {
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
        // 6、创建生产者
        MessageProducer producer = session.createProducer(destination);
        for (int i = 0; i < 100; i++) {
            // 7、创建消息
            TextMessage textMessage = session.createTextMessage("text" + i);
            // 8、发布消息
            producer.send(textMessage);
            System.out.println("send message success:" + textMessage.getText());
        }
        // 9、关闭连接
        connection.close();
    }
}
