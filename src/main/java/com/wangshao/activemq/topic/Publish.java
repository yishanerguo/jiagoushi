package com.wangshao.activemq.topic;

import com.wangshao.activemq.helloWorld1.Producer;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author liutao
 * @create 2020-04-02-20:29
 */


public class Publish {


    private ConnectionFactory connectionFactory;

    private Connection connection;

    private Session session;

    private MessageProducer messageProducer;

    public Publish(){
        try {
            this.connectionFactory = new ActiveMQConnectionFactory(
                    "bhz",
                    "bhz",
                    "tcp://localhost:61616");

            this.connection = this.connectionFactory.createConnection();
            this.connection.start();
            this.session = this.connection.createSession(false,session.AUTO_ACKNOWLEDGE);
            this.messageProducer = this.session.createProducer(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() throws Exception {
        Destination destination = session.createTopic("topic1");
        TextMessage textMessage = session.createTextMessage("我是内容");
        messageProducer.send(destination, textMessage);
    }

    public static void main(String[] args) throws Exception {
        Publish p = new Publish();
        p.sendMessage();
    }
}
