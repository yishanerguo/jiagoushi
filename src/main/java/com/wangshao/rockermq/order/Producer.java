package com.wangshao.rockermq.order;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * @author liutao
 * @create 2020-04-05-15:58
 */


public class Producer {

    public static void main(String[] args) {

        try {
            MQProducer producer = new DefaultMQProducer("message_producer");

            ((DefaultMQProducer) producer).setNamesrvAddr("192.168.1.10:9876;192.168.1.11:9876");

            producer.start();

            String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};

            for (int i = 0; i < 100; i++) {
                //订单id相同的消息要有序
                int orderId = i %10;
                Message msg =
                        new Message("topictest",
                                tags[i % tags.length],
                                "KEY" + i,
                                ("hello rockmq" + i) .getBytes() );
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                        Integer id = (Integer) o;
                        int index = id % list.size();

                        return list.get(index);
                    }
                }, orderId);
                System.out.println(sendResult);
            }
            producer.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
