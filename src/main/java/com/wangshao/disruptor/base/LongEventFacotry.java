package com.wangshao.disruptor.base;

import com.lmax.disruptor.EventFactory;

/**
 * @author liutao
 * @create 2020-03-25-17:38
 * 需要躺disruptor为我们创建事件,我们同时还声明了一个eventfactory来实例化event对象
 */


public class LongEventFacotry implements EventFactory {

    @Override
    public Object newInstance() {
        return new LongEvent();
    }
}
