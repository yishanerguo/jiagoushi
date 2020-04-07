package com.wangshao.rockermq.order;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author liutao
 * @create 2020-04-05-16:11
 * 顺序消息消费,带事务方式(应用可控制offset什么时候提交)
 */


public class Consumer {

    public static void main(String[] args) throws Exception {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("message_consumer");

        consumer.setNamesrvAddr("192.168.1.10:9876;192.168.1.11:9876");

        /**
         * 设置consumer第一次启动时从队列头部开始消费时还是对垒尾部开始消费
         * 如果非第一次启动,那么按照上次消费的位置继续消费
         */

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        consumer.subscribe("topictest", "TagA || TagC || TagD");

        consumer.registerMessageListener(new MessageListenerOrderly() {
            AtomicLong consumeTimes = new AtomicLong(0);

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                consumeOrderlyContext.setAutoCommit(false);
                System.out.println(Thread.currentThread().getName() + "收到信息:" + list);
                this.consumeTimes.incrementAndGet();
                if((this.consumeTimes.get() %  2)  == 0){
                    return ConsumeOrderlyStatus.SUCCESS;
                }
                else if ((this.consumeTimes.get() % 3) == 0) {
                    return ConsumeOrderlyStatus.ROLLBACK;
                }
                else if ((this.consumeTimes.get() % 4) == 0) {
                    return ConsumeOrderlyStatus.COMMIT;
                }
                else if ((this.consumeTimes.get() % 5) == 0) {
                    consumeOrderlyContext.setSuspendCurrentQueueTimeMillis(3000);
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();
        System.out.println("consumer started..");
    }
}
