package com.wangshao.disruptor.base;


import com.lmax.disruptor.EventHandler;

/**
 * @author liutao
 * @create 2020-03-25-17:40
 * 我们需要一个事件消费者,也就是一个事件处理器,这个事件处理器简单地把时间中存储的书打印到终端
 */


public class LongEventHandler implements EventHandler<LongEvent> {


    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println(longEvent.getValue());
    }
}
