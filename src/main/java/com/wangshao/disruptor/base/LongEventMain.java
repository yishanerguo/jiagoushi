package com.wangshao.disruptor.base;


import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liutao
 * @create 2020-03-25-21:15
 */


public class LongEventMain {

    public static void main(String[] args) {

        //创建缓冲池
        ExecutorService executor = Executors.newCachedThreadPool();

        //创建工厂
        LongEventFacotry facotry = new LongEventFacotry();

        //创建buffersize,也就是ringbuffer的大小,必须是2的n次方
        int ringBufferSize = 1024 * 1024;

        /**
         //BlockingWaitStrategy 是最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现
         WaitStrategy BLOCKING_WAIT = new BlockingWaitStrategy();
         //SleepingWaitStrategy 的性能表现跟BlockingWaitStrategy差不多，对CPU的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景
         WaitStrategy SLEEPING_WAIT = new SleepingWaitStrategy();
         //YieldingWaitStrategy 的性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于CPU逻辑核心数的场景中，推荐使用此策略；例如，CPU开启超线程的特性
         WaitStrategy YIELDING_WAIT = new YieldingWaitStrategy();
         */

        //创建disruptor
        /**
         * 1.第一个参数为工厂对象,用于创建一个个的longevent,longevent是实际消费数据
         * 2.第二个位缓冲区大小
         * 3.第三个参数:线程池,进行disruptor内部的数据接收处理调度
         * 4.第四个参数:producertype.single和producer.multi表示一个生产者和多个生产者
         * 5.第五个参数:是一个钟策略,生产和消费的策略
         */
        Disruptor<LongEvent> disruptor =
                new Disruptor<LongEvent>(facotry, ringBufferSize, executor, ProducerType.SINGLE, new YieldingWaitStrategy());

        //连接消费事件方法
        disruptor.handleEventsWith(new LongEventHandler());

        //启动
        disruptor.start();

        //disruptord的事件发布过程是一个两阶段提交的过程
        //发布事件
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);
//      LongEventProduceWithTranslator producer = new LongEventProduceWithTranslator(ringBuffer);

        ByteBuffer bytebuffer = ByteBuffer.allocate(8);
        for (long a = 0; a < 100; a++) {
            bytebuffer.putLong(0, a);
            producer.onData(bytebuffer);
        }

        disruptor.shutdown();//关闭disruptor,方法会堵塞,直到所有的事件都得到处理
        executor.shutdown();//关闭disruptor使用的线程池,若果需要的话,必须手动关闭,disruptor在shutdown时不会自动关闭
    }
}
