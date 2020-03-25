package com.wangshao.thread.threadLocal;

/**
 * @author liutao
 * @create 2020-03-23-21:16
 */


public class ConnThreadLocal {

    public static ThreadLocal<String> th = new ThreadLocal<>();

    public  void getTh() {
        System.out.println(Thread.currentThread().getName() + ":" + this.th.get());
    }

    public  void setTh(String value) {
        th.set(value);
    }

    public static void main(String[] args) {
        final ConnThreadLocal ct = new ConnThreadLocal();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ct.setTh("张三");
                ct.getTh();
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    ct.getTh();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2");

        t1.start();
        t2.start();
    }
}
