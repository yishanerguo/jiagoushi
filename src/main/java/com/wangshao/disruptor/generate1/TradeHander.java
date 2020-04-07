package com.wangshao.disruptor.generate1;


import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import java.util.UUID;

/**
 * @author liutao
 * @create 2020-03-25-22:20
 */


public class TradeHander implements EventHandler<Trade>, WorkHandler<Trade> {


    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }

    @Override
    public void onEvent(Trade event) throws Exception {
        //这里做具体的消费逻辑
        event.setId(UUID.randomUUID().toString());//简单生成id
        System.out.println(event.getId());
    }
}
