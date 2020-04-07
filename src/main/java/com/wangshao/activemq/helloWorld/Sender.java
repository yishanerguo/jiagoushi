package com.wangshao.activemq.helloWorld;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author liutao
 * @create 2020-04-02-16:05
 */


public class Sender {

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
        //使用事务的方法创建session
        //Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        //设置手动确认方式
        Session session = connection.createSession(Boolean.TRUE, Session.CLIENT_ACKNOWLEDGE);


        //4.通过session创建destination对象,指的是一个客户端用来指定生产消息目标和消费消息来源的对象,在ptp模式中,destination被称作queue(队列)
        //在pub/sub模式,destination被称作topic(主题),在程序中可以使用多个queue和topic
        Destination destination = session.createQueue("queue1");

        //5.我们需要通过session对象创建消息的发送和接受对象(生产者和消费者)
        MessageProducer messageProducer = session.createProducer(destination);

        //6.我们可以使用messageproducer的setdeliverymode方法为其设置持久化特性和非持久化特性
        messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        //7.最后我们使用jms规范的textmessage形式创建数据,并用messageproducer的send()方法发送数据,同理客户端使用receive方法进行接受数据,最后不要忘记关闭connection连接
        for (int i = 0; i <= 5 ; i++) {
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("我是消息内容,id为:" + i);
            messageProducer.send(textMessage);
            System.out.println("生产者:" + textMessage.getText());
        }

        //使用事务提交
        session.commit();

        if(connection !=null){
            connection.close();
        }

    }

}
