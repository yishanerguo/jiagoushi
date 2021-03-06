package com.wangshao.socket.bio2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liutao
 * @create 2020-03-26-14:34
 */


public class HandlerExecutorPool {

    private ExecutorService executor;
    public HandlerExecutorPool(int maxPoolSize, int queueSize){
         executor = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                maxPoolSize,
                120L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize)
        );
    }

    public void execute(Runnable task) {
        this.executor.execute(task);
    }
}
