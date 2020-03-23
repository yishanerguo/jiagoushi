package com.wangshao.thread.notice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liutao
 * @create 2020-03-23-19:21
 */


public class ListAdd1 {

    private volatile static List list = new ArrayList();
    public void add(){
        list.add("bjsxt");
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        final ListAdd1 list1 = new ListAdd1();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        list1.add();
                        System.out.println("当前线程:" + Thread.currentThread().getName() + "添加一个元素...");
                        Thread.sleep(500);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
               while (true){
                   if (list1.size() == 5) {
                       System.out.println("当前线程接受到通知:" + Thread.currentThread().getName() + " list size =5 线程停止...");
                   }
               }
            }
        },"t2");

        t1.start();
        t2.start();
    }
}
