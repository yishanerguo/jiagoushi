package com.wangshao.thread.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author liutao
 * @create 2020-03-25-0:55
 */


public class UseExecutors {

    public static void main(String[] args) {

        //固定线程池
        ExecutorService pool1 = Executors.newFixedThreadPool(10);

        //单个线程池
        ExecutorService poo2 = Executors.newSingleThreadExecutor();

        //缓存线程池
        ExecutorService pool3 = Executors.newCachedThreadPool();

        //周期性线程池
        ScheduledExecutorService pool4 = Executors.newScheduledThreadPool(10);

    }


}
