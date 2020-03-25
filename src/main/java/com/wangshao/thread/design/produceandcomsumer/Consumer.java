package com.wangshao.thread.design.produceandcomsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author liutao
 * @create 2020-03-24-21:31
 */


public class Consumer implements Runnable{

    private BlockingQueue<Data> queue;

    public Consumer(BlockingQueue queue){
        this.queue = queue;
    }

    //随机对象
    private static Random r = new Random();

    @Override
    public void run() {

        while (true) {
            try {
                Data data = this.queue.take();
                //进行数据处理,休眠
                Thread.sleep(1000);
                System.out.println("当前消费线程:" + Thread.currentThread().getName() + ", 消费成功为id: " + data.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
