package com.wangshao.rockermq.message;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageConst;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @author liutao
 * @create 2020-04-05-12:56
 */


public class Consumer2 {

    public Consumer2(){

        try {
            String group_name = "message_consumer";

            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(group_name);
            consumer.setNamesrvAddr("192.168.1.10:9876;192.168.1.11:9876");
            consumer.subscribe("topic1","tag1 || tag2 || tag3" );
            //广播模式下需要先启动consumer
            consumer.setMessageModel(MessageModel.BROADCASTING);
            consumer.registerMessageListener(new Listener());
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Listener implements MessageListenerConcurrently{

        @Override
        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

            try {

                for (MessageExt msg : list) {
                    String topic = msg.getTopic();
                    String msgBody = new String(msg.getBody(),"utf-8");
                    String tags = msg.getTags();
                    System.out.println("收到消息:" +  "topic:" + topic + ",tags:" + tags  + ",msg:" + msg);
//                    String orignMsgId = msg.getProperties().get(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID);
//                    System.out.println(orignMsgId);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }

            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
    }

    public static void main(String[] args) {
        Consumer2 c2 = new Consumer2();
        System.out.println("c2 start ....");
    }
}
