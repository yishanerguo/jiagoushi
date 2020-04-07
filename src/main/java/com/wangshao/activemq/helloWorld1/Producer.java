package com.wangshao.activemq.helloWorld1;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author liutao
 * @create 2020-04-02-18:54
 */


public class Producer {

    //单例模式

    //连接工厂
    private ConnectionFactory connectionFactory;

    //连接对象
    private Connection connection;

    //session对象
    private Session session;

    //生产者
    private MessageProducer messageProducer;

    public Producer(){
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

    public Session getSession(){
        return this.session;
    }

    public void send1() throws Exception {

        try {
            Destination destination = this.session.createQueue("first");
            MapMessage msg1 = this.session.createMapMessage();
            msg1.setString("naem", "张三");
            msg1.setString("age","23" );
            msg1.setStringProperty("color","blue");
            msg1.setIntProperty("sal",2200);
            int id = 1;
            msg1.setInt("id",id);
            String receiver = id%2 == 0 ? "A" : "B";
            msg1.setStringProperty("receiver", receiver);

            MapMessage msg2 = this.session.createMapMessage();
            msg2.setString("naem","李四");
            msg2.setString("age", "26");
            msg2.setStringProperty("color","red");
            msg2.setIntProperty("sal", 1300);
            id = 2;
            msg2.setInt("id", id);
            receiver = id%2 ==0 ? "A":"B";
            msg2.setStringProperty("receiver", receiver);

            MapMessage msg3 = this.session.createMapMessage();
            msg3.setString("naem","王五");
            msg3.setString("age", "28");
            msg3.setStringProperty("color","green");
            msg3.setIntProperty("sal", 1500);
            id = 3;
            msg3.setInt("id", id);
            receiver = id%2 ==0 ? "A":"B";
            msg3.setStringProperty("receiver", receiver);

            MapMessage msg4 = this.session.createMapMessage();
            msg4.setString("naem","赵六");
            msg4.setString("age", "30");
            msg4.setStringProperty("color","blue");
            msg4.setIntProperty("sal", 1800);
            id = 4;
            msg4.setInt("id", id);
            receiver = id%2 ==0 ? "A":"B";
            msg4.setStringProperty("receiver", receiver);

            this.messageProducer.send(destination, msg1, DeliveryMode.NON_PERSISTENT, 2, 1000*60*10L);
            this.messageProducer.send(destination, msg2, DeliveryMode.NON_PERSISTENT, 3, 1000*60*10L);
            this.messageProducer.send(destination, msg3, DeliveryMode.NON_PERSISTENT, 6, 1000*60*10L);
            this.messageProducer.send(destination, msg4, DeliveryMode.NON_PERSISTENT, 9, 1000*60*10L);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void send2() throws Exception {
        try {
            Destination destination = this.session.createQueue("first");
            TextMessage msg = this.session.createTextMessage("我是一个字符串内容");
            this.messageProducer.send(destination,msg,DeliveryMode.NON_PERSISTENT,9,1000*60*10L );
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Producer producer = new Producer();
        producer.send1();
        //producer.send2();
    }
}
