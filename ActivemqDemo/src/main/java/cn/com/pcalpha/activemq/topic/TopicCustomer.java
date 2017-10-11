package cn.com.pcalpha.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;

public class TopicCustomer {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String BROKEURL = "tcp://45.78.35.167:61616";//默认连接地址

    private static final String topic1 = "mq.topic";



    public static void main(String[] args) {

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);

        Connection connection;

        try {

            connection = factory.createConnection();

            connection.start();

            final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

            Topic topic = session.createTopic(topic1);

            MessageConsumer consumer = session.createConsumer(topic);

            consumer.setMessageListener(new MessageListener() {

                public void onMessage(Message msg) {

                    MapMessage message = (MapMessage) msg;

                    try {

                        System.out.println("--订阅者一收到消息：" + new Date(message.getLong("count")));

                        session.commit();

                    } catch (JMSException e) {

                        e.printStackTrace();

                    }

                }

            });

        } catch (JMSException e) {

            e.printStackTrace();

        }

    }
}
