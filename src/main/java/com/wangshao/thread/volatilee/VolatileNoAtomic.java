package com.wangshao.thread.volatilee;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liutao
 * @create 2020-03-23-17:39
 * volatile关键字不具备synchronized关键字的原子性(同步)
 */


public class VolatileNoAtomic extends Thread {

    //private  static volatile int count;
    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
       addCount();
    }

    private static void addCount(){
        for (int i = 0; i < 1000; i++) {
            //count++;
            count.incrementAndGet();

        }
        System.out.println(count);

    }

    public static void main(String[] args) {
        VolatileNoAtomic[] arr = new VolatileNoAtomic[100];
        for (int i = 0; i < 10; i++) {
            arr[i] = new VolatileNoAtomic();
        }

        for (int i = 0; i < 10; i++) {
            arr[i].start();
        }
    }
}
