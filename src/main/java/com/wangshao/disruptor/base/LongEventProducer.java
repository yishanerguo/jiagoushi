package com.wangshao.disruptor.base;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author liutao
 * @create 2020-03-25-17:45
 * 很明显的是:当用一个简单队列来发布事件的时候会牵涉更多的细节,这是因为事件对象还需要预先创建
 * 发布事件最少需要两步:获取下一个事件槽并发布事件(发布事件的时候要使用try/final)保证事件一定会被发布
 * 如果我们使用ringbuffer,next()获取一个事件槽,那么一定要发布对应的事件
 * 如果不能发布事件,那么就会引起disruptor状态的混乱
 * 尤其是在多个事件生产者的情况下会导致事件消费者不能消费,从而不得不重启应用才能恢复
 */


public class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer;


    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /**
     * onData用来发布事件,每嗲用一次就发布一次事件
     * 它的参数会通过事件传递给消费者
     * @param byteBuffer
     */
    public void onData(ByteBuffer byteBuffer) {
        //1.可以把ringBuffer看做一个事件队列,那么么next就是得到下面一个事件槽
        long sequeuece = ringBuffer.next();

        try {
            //2.用上面的索引去除一个空的事件用于填充(获取该序列号对应的事件对象)
            LongEvent event = ringBuffer.get(sequeuece);
            //3.获取要通过事件传递的业务数据
            event.setValue(byteBuffer.getLong(0));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //4.发布事件
            //注意,最后的ringbuffer.publish方法必须包含在finally中,以确保必须得到调用,如果某个请求的sequeuece未被提交,将会堵塞后续的发布操作或者其他的producer
            ringBuffer.publish(sequeuece);
        }
    }
}
