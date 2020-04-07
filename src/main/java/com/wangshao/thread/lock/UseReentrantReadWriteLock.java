package com.wangshao.thread.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author liutao
 * @create 2020-03-25-16:58
 */


public class UseReentrantReadWriteLock {

    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public void read(){
        try {
            readLock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入....");
            Thread.sleep(3000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public  void write(){
        try {
            writeLock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入...");
            Thread.sleep(3000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        final UseReentrantReadWriteLock useReentrantReadWriteLock = new UseReentrantReadWriteLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                useReentrantReadWriteLock.read();
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                useReentrantReadWriteLock.read();
            }
        },"t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                useReentrantReadWriteLock.read();
            }
        },"t3");
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                useReentrantReadWriteLock.read();
            }
        },"t4");

//        t1.start();
//        t2.start();

//        t1.start();  //r
//        t3.start();  //w

        t3.start();
        t4.start();
    }
}
