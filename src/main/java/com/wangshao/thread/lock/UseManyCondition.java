package com.wangshao.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liutao
 * @create 2020-03-25-16:37
 */


public class UseManyCondition {

    private ReentrantLock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();

    public void m1(){
        try {
            lock.lock();
            System.out.println("当期线程:" + Thread.currentThread().getName() + "进入方法m1等待...");
            c1.await();
            System.out.println("当期线程:" + Thread.currentThread().getName() + "方法m1继续...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void m2(){
        try {
            lock.lock();
            System.out.println("当期线程:" + Thread.currentThread().getName() + "进入方法m2等待...");
            c1.await();
            System.out.println("当期线程:" + Thread.currentThread().getName() + "方法m2继续...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void m3(){
        try {
            lock.lock();
            System.out.println("当期线程:" + Thread.currentThread().getName() + "进入方法m3等待...");
            c2.await();
            System.out.println("当期线程:" + Thread.currentThread().getName() + "方法m3继续...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void m4(){
        try {
            lock.lock();
            System.out.println("当期线程:" + Thread.currentThread().getName() + "唤醒...");
            c1.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void m5(){
        try {
            lock.lock();
            System.out.println("当期线程:" + Thread.currentThread().getName() + "唤醒...");
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        final UseManyCondition useManyCondition = new UseManyCondition();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                useManyCondition.m1();
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                useManyCondition.m2();
            }
        },"t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                useManyCondition.m3();
            }
        },"t3");
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                useManyCondition.m4();
            }
        },"t4");
        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                useManyCondition.m5();
            }
        },"t5");


        t1.start(); //c1
        t2.start(); //c1
        t3.start(); //c2

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t4.start(); //c1


    }
}
