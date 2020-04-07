package com.wangshao.rockermq.message;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import org.apache.activemq.broker.region.group.MessageGroupSet;

/**
 * @author liutao
 * @create 2020-04-05-12:49
 */


public class Producer {

    public static void main(String[] args) throws Exception {

        String group_name = "message_producer";

        DefaultMQProducer producer = new DefaultMQProducer(group_name);

        producer.setNamesrvAddr("192.168.1.10:9876;192.168.1.11:9876");
        producer.start();

        for (int i = 0; i < 10; i++) {
            try {
                Message mes = new Message("topic1",
                        "tag1",
                        ("信息内容"+i).getBytes());
                SendResult sendResult = producer.send(mes);
                System.out.println(sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
//        for (int i = 0; i < 50; i++) {
//            try {
//                Message mes = new Message("topic1",
//                        "tag2",
//                        ("信息内容"+i).getBytes());
//                SendResult sendResult = producer.send(mes);
//                System.out.println(sendResult);
//            } catch (Exception e) {
//                e.printStackTrace();
//                Thread.sleep(1000);
//            }
//        }
//        for (int i = 0; i < 50; i++) {
//            try {
//                Message mes = new Message("topic2",
//                        "tag3",
//                        ("信息内容"+i).getBytes());
//                SendResult sendResult = producer.send(mes);
//                System.out.println(sendResult);
//            } catch (Exception e) {
//                e.printStackTrace();
//                Thread.sleep(1000);
//            }
//        }
    }

}
