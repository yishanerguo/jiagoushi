package com.wangshao.thread.notice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author liutao
 * @create 2020-03-23-19:34
 * wait notify 方法:wait释放锁,notify不释放锁(唤醒并不代表释放锁,等待线程执行完后再释放锁)
 */


public class ListAdd2 {

    private volatile static List list = new ArrayList();

    public void add(){
        list.add("bjsxt");
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {

        final ListAdd2 list2 = new ListAdd2();
        /**
         * 1.s实例化出来一个lock
         * 当时用wait和notify的时候,一定要配合着synchronized关键字去使用(不是实时的问题)
         */

        //final Object lock = new Object();

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //synchronized (lock) {
                        for (int i = 0; i < 10; i++) {
                            list2.add();
                            System.out.println("当前线程:" + Thread.currentThread().getName() + "添加一个元素...");
                            Thread.sleep(500);
                            if (list2.size() == 5) {
                                //lock.notify();
                                countDownLatch.countDown();
                                System.out.println("已经发出通知");
                            }
                        }
                   // }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
               // synchronized (lock) {
                    if (list2.size() != 5) {
                        try {
                            //lock.wait();
                            countDownLatch.await();
                            System.out.println("t2进入...");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("当前线程:" + Thread.currentThread().getName() + "收到通知线程停止");
                   // throw new RuntimeException();
                }
            //}
        }, "t2");

        t1.start();
        t2.start();
    }
}
