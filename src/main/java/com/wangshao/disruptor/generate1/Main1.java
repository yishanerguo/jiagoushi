package com.wangshao.disruptor.generate1;

import com.lmax.disruptor.*;

import java.util.concurrent.*;

/**
 * @author liutao
 * @create 2020-03-25-22:25
 */


public class Main1 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int BUFFER_SIZE = 1024;
        int THREAD_NUMBERS = 4;

        /**
         * createSingleProducer创建一个单生产者的ringbuffer
         * 第一个:eventfactory,事件工厂,产生数据填充到ringbuffer的
         * 第二个:ringbuffer大小,它必须是2的指数倍,目的为了求模运算提高效率
         * 第三个:等待策略
         */
        RingBuffer<Trade> ringbuffer = RingBuffer.createSingleProducer(new EventFactory<Trade>() {
            @Override
            public Trade newInstance() {
                return new Trade();
            }
        }, BUFFER_SIZE, new YieldingWaitStrategy());

        //创建线程池
        ExecutorService executors = Executors.newFixedThreadPool(THREAD_NUMBERS);

        //创建sequencebarrier
        SequenceBarrier sequenceBarrier = ringbuffer.newBarrier();

        //创建消息处理器
        BatchEventProcessor<Trade> transprProcessor = new BatchEventProcessor<>(
                ringbuffer, sequenceBarrier, new TradeHander());

        //这一步的木笔就是把消费者的位置信息引用注入到生产者,如果一个消费者,就可以省略
        ringbuffer.addGatingSequences(transprProcessor.getSequence());

        //把消息处理器提交到线程池
        executors.submit(transprProcessor);

        //如果存在国歌消费者,那重复执行上面3行代码,把tradehandler换成其他消费者类

        Future<Void> future = executors.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                long seq;
                for (int i = 0; i < 10; i++) {
                    seq = ringbuffer.next(); //占个坑 -- ringbuffer一个可用区块
                    ringbuffer.get(seq).setPrice(Math.random() * 9999);//给这个区块放入数据
                    ringbuffer.publish(seq);//发布这个区块的数据使用handler(consumer)可见
                }
                return null;
            }
        });

//        future.get();//等待生产者结束
        Thread.sleep(1000);//等待1秒,等消费者处理完成
        transprProcessor.halt();//通知时间(或者消息)处理器可以结束了(并不是马上结束)
        executors.shutdown();

    }
}
