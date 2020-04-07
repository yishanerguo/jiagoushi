package com.wangshao.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author liutao
 * @create 2020-04-02-20:37
 */


public class Consumer3 {


    private ConnectionFactory connectionFactory;

    private Connection connection;

    private Session session;

    private MessageConsumer messageConsumer;

   public Consumer3(){
       try {
           this.connectionFactory = new ActiveMQConnectionFactory(
                   "bhz","bhz" ,"tcp://localhost:61616" );
           this.connection = this.connectionFactory.createConnection();
           this.connection.start();
           this.session = this.connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

   public void receive() throws Exception {
       Destination destination = session.createTopic("topic1");
        messageConsumer = session.createConsumer(destination);
       messageConsumer.setMessageListener(new Listener());
   }

   class Listener implements MessageListener{

       @Override
       public void onMessage(Message message) {

           if(message instanceof TextMessage){
               TextMessage textMessage = (TextMessage) message;
               System.out.println(textMessage);
           }
       }
   }

    public static void main(String[] args) throws Exception {
        Consumer3 consumer = new Consumer3();
        consumer.receive();
    }
}
