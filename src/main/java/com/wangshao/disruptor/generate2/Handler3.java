package com.wangshao.disruptor.generate2;

import com.lmax.disruptor.EventHandler;
import com.wangshao.disruptor.generate1.Trade;

/**
 * @author liutao
 * @create 2020-03-26-0:08
 */


public class Handler3 implements EventHandler<Trade> {
    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("handler3: name: " + event.getName() + " , price: " + event.getPrice() + ";  instance: " + event.toString());
    }
}
