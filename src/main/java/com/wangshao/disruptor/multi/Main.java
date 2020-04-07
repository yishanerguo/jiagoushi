package com.wangshao.disruptor.multi;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;


import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * @author liutao
 * @create 2020-03-26-0:53
 */


public class Main {

    public static void main(String[] args) throws InterruptedException {

        //创建ringbuffer
        RingBuffer<Order> ringBuffer =
                RingBuffer.create(ProducerType.MULTI,
                        new EventFactory<Order>() {
                            @Override
                            public Order newInstance() {
                                return new Order();
                            }
                        },
                        1024 * 1024,
                        new YieldingWaitStrategy());
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        Consumer[] comsumers = new Consumer[3];
        for (int i = 0; i < comsumers.length; i++) {
            comsumers[i] = new Consumer("c" + i);
        }

        WorkerPool<Order> workerPool =
                new WorkerPool<Order>(ringBuffer,
                        sequenceBarrier,
                        new IntEventExceptionHandler(),
                        comsumers);

        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());
        workerPool.start(
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));

        final CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            final Producer producer = new Producer(ringBuffer);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < 100; j++) {
                        producer.onData(UUID.randomUUID().toString());
                    }
                }
            }).start();
        }
        Thread.sleep(2000);
        System.out.println(".....................开始生产.........................");
        latch.countDown();
        Thread.sleep(5000);
        System.out.println("总数:" + comsumers[0].getCount());
    }

    static class IntEventExceptionHandler implements ExceptionHandler{

        @Override
        public void handleEventException(Throwable ex, long sequence, Object event) {

        }

        @Override
        public void handleOnStartException(Throwable ex) {

        }

        @Override
        public void handleOnShutdownException(Throwable ex) {

        }
    }
}
