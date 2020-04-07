package com.wangshao.activemq.helloWorld;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author liutao
 * @create 2020-04-02-17:10
 */


public class receiver {

    public static void main(String[] args) throws Exception {

        //1.建立connectionfactory工厂对象,需要填入用户名,密码,以及要连接的地址,均使用默认即可,默认端口为"tcp://localhost:61616"
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
//                ActiveMQConnectionFactory.DEFAULT_USER,
//                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
//                "tcp://localhost:61616");
        //安全验证,不是谁都能访问
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "bhz",
                "bhz",
                "tcp://localhost:61616");

        //2.通过connnectionfacoty工厂对象我们创建一个connection连接,并且调用connnection的start方法开启连接,connection默认是关闭的
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //3.通过connectiondduixiangchangjain sesssion绘画(上下文环境对象),用于接收消息,参数配置1为是否启用是事务,参数配置2为签收模式,一般我们设置自动签收
        //Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);

        //4.通过session创建destination对象,指的是一个客户端用来指定生产消息目标和消费消息来源的对象,在ptp模式中,destination被称作queue(队列)
        //在pub/sub模式,destination被称作topic(主题),在程序中可以使用多个queue和topic
        Destination destination = session.createQueue("queue1");

        //5.我们需要通过session对象创建消息的发送和接受对象(生产者和消费者)
        MessageConsumer messageConsumer = session.createConsumer(destination);

        while (true){
            TextMessage textMessage = (TextMessage) messageConsumer.receive();
            //手工去签收消息,另其一个线程(Tcp)
            textMessage.acknowledge();
            if(textMessage ==null) break;
            System.out.println("收到的内容:" + textMessage.getText());
        }

        if(connection !=null){
            connection.close();
        }

    }
}
