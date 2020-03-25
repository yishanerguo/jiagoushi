package com.wangshao.thread.singleton;

/**
 * @author liutao
 * @create 2020-03-23-21:46
 */


public class DubbleSingleton {

    private static DubbleSingleton ds;

    public static DubbleSingleton getDs(){
        if (ds == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (DubbleSingleton.class) {
                //为什么还要加上判空,因为,当上个线程释放锁时,其他线程还会创建对象
                if (ds == null) {
                    ds = new DubbleSingleton();
                }
            }
        }
        return ds;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(DubbleSingleton.getDs().hashCode());
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(DubbleSingleton.getDs().hashCode());
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(DubbleSingleton.getDs().hashCode());
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
