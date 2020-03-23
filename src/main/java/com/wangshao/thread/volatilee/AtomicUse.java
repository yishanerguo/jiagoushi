package com.wangshao.thread.volatilee;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liutao
 * @create 2020-03-23-18:19
 */


public class AtomicUse extends Thread {

    private static AtomicInteger count = new AtomicInteger(0);

    //多个addAndGet在一个方法内是非原子性的,需要加synchronized进行修饰,保证4个addAndGet整体原子性
    public int multiAdd(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count.addAndGet(1);
        count.addAndGet(2);
        count.addAndGet(3);
        count.addAndGet(4);
        return count.get();

    }

    public static void main(String[] args) {
        final AtomicUse atomicUse = new AtomicUse();

        List<Thread> list = new ArrayList<Thread>();

        for (int i = 0; i < 100; i++) {
           list.add(new Thread(new Runnable() {
               @Override
               public void run() {
                   System.out.println(atomicUse.multiAdd());
               }
           }));

        }

        for (Thread t : list) {
            t.start();
        }
    }

}
