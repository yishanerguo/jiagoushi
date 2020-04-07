package com.wangshao.disruptor.generate2;

import com.lmax.disruptor.EventHandler;
import com.wangshao.disruptor.generate1.Trade;

/**
 * @author liutao
 * @create 2020-03-26-0:06
 */


public class Handler2 implements EventHandler<Trade> {
    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("handel2: set prive");
        event.setPrice(17.0);
        Thread.sleep(1000);
    }
}
