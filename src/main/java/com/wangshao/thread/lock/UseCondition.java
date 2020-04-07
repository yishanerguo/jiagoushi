package com.wangshao.thread.lock;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liutao
 * @create 2020-03-25-16:26
 */


public class UseCondition {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void method1(){
        try {
            lock.lock();
            System.out.println("当期线程:" + Thread.currentThread().getName() + "进入等待状态...");
            Thread.sleep(3000);
            System.out.println("当期线程:" + Thread.currentThread().getName() + "释放锁...");
            condition.await();
            System.out.println("当期线程:" + Thread.currentThread().getName() + "继续执行...");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void method2(){
        try {
            lock.lock();
            System.out.println("当期线程:" + Thread.currentThread().getName() + "进入...");
            Thread.sleep(3000);
            System.out.println("当期线程:" + Thread.currentThread().getName() + "发出唤醒...");
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        UseCondition useCondition = new UseCondition();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                useCondition.method1();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                useCondition.method2();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
