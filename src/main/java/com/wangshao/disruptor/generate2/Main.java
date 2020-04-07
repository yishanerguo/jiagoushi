package com.wangshao.disruptor.generate2;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;
import com.wangshao.disruptor.generate1.Trade;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author liutao
 * @create 2020-03-26-0:13
 */


public class Main {

    public static void main(String[] args) throws InterruptedException {

        long beginTime = System.currentTimeMillis();
        int bufferSize = 1024;
        ExecutorService executor= Executors.newFixedThreadPool(8);

        Disruptor<Trade> disruptor = new Disruptor<>(new EventFactory<Trade>() {
            @Override
            public Trade newInstance() {
                return new Trade();
            }
        }, bufferSize, executor, ProducerType.SINGLE, new BusySpinWaitStrategy());

        //菱形操作

        //使用disruptor创建消费者组c1,c2
//        EventHandlerGroup<Trade> handlerGroup =
//                disruptor.handleEventsWith(new Handler1(), new Handler2());
//        //声明c1,c2完事之后执行jms消息发送操作,也是流程走到c3
//        handlerGroup.then(new Handler3());

        //顺序操作
//        disruptor.handleEventsWith(new Handler1()).
//                handleEventsWith(new Handler2()).
//                handleEventsWith(new Handler3());

        //六边形操作
        Handler1 h1 = new Handler1();
        Handler2 h2 = new Handler2();
        Handler3 h3 = new Handler3();
        Handler4 h4 = new Handler4();
        Handler5 h5 = new Handler5();

        disruptor.handleEventsWith(h1, h2);
        disruptor.after(h1).handleEventsWith(h4);
        disruptor.after(h2).handleEventsWith(h5);
        disruptor.after(h4, h5).handleEventsWith(h3);

        disruptor.start();
        CountDownLatch latch = new CountDownLatch(1);
        //生产者准备
         executor.submit(new TradePublisher(disruptor,latch));

         latch.await(); //等待生产者完事

        disruptor.shutdown();
        executor.shutdown();
        System.out.println("总耗时:" + (System.currentTimeMillis()-beginTime));
    }
}
