package com.wangshao.rockermq.quickstart;


import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * @author liutao
 * @create 2020-04-04-23:07
 * 发送消息
 */


public class Producer {

    public static void main(String[] args) throws InterruptedException, MQClientException {
        DefaultMQProducer producer =new DefaultMQProducer("quickstart_producer");
        producer.setNamesrvAddr("192.168.1.10:9876;192.168.1.11:9876");
        producer.start();

        for (int i = 0; i < 100; i++) {
            try {
                Message msg = new Message("TopicTest",
                        "TagA",
                        ("hello rocketmq" + i) .getBytes() );
                SendResult sendResult = producer.send(msg);
                System.out.println(sendResult);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        producer.shutdown();
    }
}
