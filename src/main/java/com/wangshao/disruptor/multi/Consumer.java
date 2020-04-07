package com.wangshao.disruptor.multi;

import com.lmax.disruptor.WorkHandler;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liutao
 * @create 2020-03-26-0:47
 */


public class Consumer implements WorkHandler<Order> {

    private String consumerId;

    private static AtomicInteger count = new AtomicInteger(0);

    public Consumer(String consumerId) {
        this.consumerId = consumerId;
    }

    @Override
    public void onEvent(Order event) throws Exception {
        System.out.println("当前消费者:" + this.consumerId + ", 消费消息:" + event.getId());
    }

    public int getCount(){
        return count.get();
    }
}
