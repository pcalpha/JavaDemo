package cn.com.pcalpha.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSCustomer {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String BROKEURL = "tcp://45.78.35.167:61616";//默认连接地址

    public static void main(String[] args) {
        ConnectionFactory connectionFactory;//连接工厂
        Connection connection = null;//连接

        Session session;//会话 接受或者发送消息的线程
        Destination destination;//消息的目的地

        MessageConsumer messageConsumer;//消息的消费者

        //实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(JMSCustomer.USERNAME, JMSCustomer.PASSWORD, JMSCustomer.BROKEURL);

        try {
            //通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            //启动连接
            connection.start();
            //创建session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建一个连接HelloWorld的消息队列
            destination = session.createQueue("HelloWorld");
            //创建消息消费者
            messageConsumer = session.createConsumer(destination);

            messageConsumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    try {
                        String text = ((TextMessage) message).getText();
                        System.out.println(text);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

//            while (true) {
//                System.out.println("1");
//                TextMessage textMessage = (TextMessage) messageConsumer.receive(5000);
//                if(textMessage != null){
//                    System.out.println("收到的消息:" + textMessage.getText());
//                }else {
//                    break;
//                }
//            }

        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
//            if(null!=connection){
//                try {
//                    connection.close();
//                } catch (JMSException e) {
//                    e.printStackTrace();
//                }
//            }
        }

    }
}
