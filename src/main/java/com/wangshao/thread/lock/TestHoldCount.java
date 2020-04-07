package com.wangshao.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liutao
 * @create 2020-03-25-16:49
 */


public class TestHoldCount {

    //重入锁
    private ReentrantLock lock = new ReentrantLock();

    public void m1(){
        try {
            lock.lock();
            System.out.println("进入m1方法,holdcount数为:" + lock.getHoldCount());

            //调用m2方法
            m2();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void m2(){
        try {
            lock.lock();
            System.out.println("进入m2方法,holdcount数为:" + lock.getHoldCount());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        TestHoldCount testHoldCount = new TestHoldCount();
        testHoldCount.m1();
    }
}
