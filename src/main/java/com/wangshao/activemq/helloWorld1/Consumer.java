package com.wangshao.activemq.helloWorld1;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author liutao
 * @create 2020-04-02-19:27
 */


public class Consumer {

    public final String SELECTOR_1 = "color = 'blue'";

    public final String SELECTOR_2 = "color = 'blue' AND sal > 2000";

    public final String SELECTOR_3 = "receiver = 'A'";

    public final String SELECTOR_4 = "receiver = 'B'";

    //连接工厂
    private ConnectionFactory connectionFactory;

    //连接对象
    private Connection connection;

    //session对象
    private Session session;

    //消费者
    private MessageConsumer messageConsumer;

    //目标地址
    private Destination destination;

    public Consumer(){
        try {
            this.connectionFactory = new ActiveMQConnectionFactory(
                    "bhz","bhz" ,"tcp://localhost:61616" );
            this.connection = this.connectionFactory.createConnection();
            this.connection.start();
            this.session = this.connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
            this.destination = this.session.createQueue("first");
            this.messageConsumer = this.session.createConsumer(this.destination,SELECTOR_2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receiver(){
        try {
            this.messageConsumer.setMessageListener(new Listener());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    class Listener implements MessageListener{

        @Override
        public void onMessage(Message message) {

            try {
                if(message instanceof TextMessage){

                }

                if(message instanceof  MapMessage){
                    MapMessage ret = (MapMessage) message;
                    System.out.println(ret.toString());
                    System.out.println(ret.getString("naem"));
                    System.out.println(ret.getString("age"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        Consumer c = new Consumer();
        c.receiver();
    }
}


