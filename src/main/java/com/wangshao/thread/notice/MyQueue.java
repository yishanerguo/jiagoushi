package com.wangshao.thread.notice;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liutao
 * @create 2020-03-23-20:08
 */


public class MyQueue {

    //1.需要一个承装元素的集合
    private LinkedList<Object> list = new LinkedList<Object>();

    //2.需要一个计数器
    private AtomicInteger count =new AtomicInteger(0);

    //3.需要制定上限和下限
    private final int minSize = 0;

    private final int maxSize;


    //4.构造方法
    public MyQueue(int size) {
        this.maxSize = size;
    }

    //5.初始化一个对象用于加锁
    private final Object lock = new Object();

    //加入元素:把object加入到blockingQueue里,如果blockingqueue没有空间,则调用此方法的线程阻断,直到blockingqueue里面有空间在继续
    public void put(Object obj){
        synchronized (lock) {
            while (count.get() == this.maxSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //1.加入元素
            list.add(obj);
            //2.计数器累加
            count.incrementAndGet();
            //3.通知另外一个线程(唤醒)
            lock.notify();
            System.out.println("新加入元素为:" + obj);
        }
    }

    //取走元素:取走blockingqueue里排在首位的对象,若blockingqueue为空,阻断进入等待状态直到blockingqueue有新的数据加入
    public Object take(){
        Object ret = null;
        synchronized (lock) {
            while (count.get() == this.minSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //1.移除元素操作
            ret = list.removeFirst();
            //2.计数器递减
            count.decrementAndGet();
            //3.唤醒另外一个线程
            lock.notify();

        }
            return ret;
    }

    //获取队列里面
    public int getSize(){
        return this.count.get();
    }

    public static void main(String[] args) {
        final MyQueue myQueue = new MyQueue(5);
        myQueue.put("a");
        myQueue.put("b");
        myQueue.put("c");
        myQueue.put("d");
        myQueue.put("e");

        System.out.println("当前容器的长度:" + myQueue.getSize());

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myQueue.put("f");
                myQueue.put("g");
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Object o1 = myQueue.take();
                System.out.println("移除元素为:" + o1);
                Object o2 = myQueue.take();
                System.out.println("移除元素为:" + o2);
            }
        }, "t2");

//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        t2.start();
    }
}
