package cn.com.pcalpha.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;

public class TopicProducer {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String BROKEURL = "tcp://45.78.35.167:61616";//默认连接地址

    private static final String topic1 = "mq.topic";

    public static void main(String[] arg){

        ActiveMQConnectionFactory amcf = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);

        try {

            Connection conn = amcf.createConnection();

            conn.start();

            Session session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

            Destination d = session.createTopic(topic1);

            MessageProducer producer = session.createProducer(d);

            for (int i = 0; i <= 20; i++){

                MapMessage message = session.createMapMessage();

                Date date = new Date();

                message.setLong("count",date.getTime());

                Thread.sleep(1000);

                producer.send(message);

                System.out.println("--发送消息：" + date);

                session.commit();

            }



            session.close();

            conn.close();

        }catch(Exception e) {

            e.printStackTrace();

        }

    }

}
