package com.wangshao.thread.threadPool.rejected;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liutao
 * @create 2020-03-25-2:05
 */


public class UseThreadPoolExecutor2 implements Runnable{

    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        try {
            int temp = count.incrementAndGet();
            System.out.println("任务" + temp);
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Runnable> queue =
                //new LinkedBlockingQueue<>();
                new ArrayBlockingQueue<Runnable>(10);
        ThreadPoolExecutor ex = new ThreadPoolExecutor(
                5,
                10,
                120L,
                TimeUnit.SECONDS,
                queue);

        for (int i = 0; i < 20; i++) {
            ex.execute(new UseThreadPoolExecutor2());
        }

        Thread.sleep(1000);
        System.out.println("queue size:" + queue.size());
        Thread.sleep(2000);
    }
}
