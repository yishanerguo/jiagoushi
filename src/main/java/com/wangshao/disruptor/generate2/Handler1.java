package com.wangshao.disruptor.generate2;


import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import com.wangshao.disruptor.generate1.Trade;

/**
 * @author liutao
 * @create 2020-03-26-0:00
 */


public class Handler1 implements EventHandler<Trade> , WorkHandler<Trade> {

    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }

    @Override
    public void onEvent(Trade event) throws Exception {
        System.out.println("handler: set name");
        event.setName("h1");
        Thread.sleep(1000);
    }
}
