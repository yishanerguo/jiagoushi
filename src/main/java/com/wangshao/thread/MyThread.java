package com.wangshao.thread;

/**
 * @author liutao
 * @create 2020-03-23-0:05
 * 线程安全概念:当多个线程访问某一个类时,这个对象始终都能表现出正确的行为,那么这个类(对象或方法)就是宪政安全的
 * synchronize:可以在任意对象或方法上加锁,而加锁的这段代码称为互斥区或临界区
 */


public class MyThread extends Thread {

    private int count = 5;


    public synchronized void run() {
        count--;
        System.out.println(this.currentThread().getName()+"count="+ count);
    }

    public static void main(String[] args) {

        /**
         * 分析:当多个线程访问myhread的run方法时,以排队的方式进行处理(这里排队是按照cpu分配的先后顺序而定的),
         *     一个线程想要执行synchronized修饰的方法里的代码:
         *     1.尝试获取锁
         *     2.如果拿到锁,执行synchronzied代码题日荣,拿不到锁,这个线程就会不断的尝试获取这把锁,知道拿到为止,
         *     而且是多个线程同时去竞争这个把锁(也就是会有锁竞争的问题)
         */
        MyThread myThread = new MyThread();
        Thread t1 = new Thread(myThread, "t1");
        Thread t2 = new Thread(myThread, "t2");
        Thread t3 = new Thread(myThread, "t3");
        Thread t4 = new Thread(myThread, "t4");
        Thread t5 = new Thread(myThread, "t5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
