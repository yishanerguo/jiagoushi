package com.wangshao.thread.design.produceandcomsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author liutao
 * @create 2020-03-24-21:35
 */


public class Main {

    public static void main(String[] args) {

        //内存缓冲区
        BlockingQueue<Data> queue = new LinkedBlockingQueue<>(10);

        //生产者
        Provider p1 = new Provider(queue);
        Provider p2 = new Provider(queue);
        Provider p3 = new Provider(queue);

        //消费者
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        Consumer c3 = new Consumer(queue);

        //创建线程池运行,这个一个缓存功能的线程池,可以创建无穷大的线程,没有任务的时候不创建线程,空闲线程存活时间为60s

        ExecutorService cachePool = Executors.newCachedThreadPool();
        cachePool.execute(p1);
        cachePool.execute(p2);
        cachePool.execute(p3);
        cachePool.execute(c1);
        cachePool.execute(c2);
        cachePool.execute(c3);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        p1.stop();
        p2.stop();
        p3.stop();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cachePool.shutdown();
//        cachePool.shutdownNow();
    }
}
