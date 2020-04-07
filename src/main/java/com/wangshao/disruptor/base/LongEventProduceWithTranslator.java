package com.wangshao.disruptor.base;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author liutao
 * @create 2020-03-25-21:03
 *
 * disruptor 3.0 提供了lamdd式的api,这样可以把一些复杂的操作放在ringBffer
 * 所有在disruptor3.0以后的版本最好使用event publisher或者event translator来发布事件
 */


public class LongEventProduceWithTranslator {

    //一个translattor可以看做一个事件的初始化器,publicEvent方法会调用它
    //填充event
    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR =
            new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
                @Override
                public void translateTo(LongEvent event, long sequeue, ByteBuffer buffer) {
                    event.setValue(buffer.getLong(0));
                }
            };

    private final RingBuffer<LongEvent> ringBuffer;


    public LongEventProduceWithTranslator(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer buffer) {
        ringBuffer.publishEvent(TRANSLATOR, buffer);
    }
}
