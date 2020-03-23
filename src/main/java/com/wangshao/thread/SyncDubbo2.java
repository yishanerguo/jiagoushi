package com.wangshao.thread;

/**
 * @author liutao
 * @create 2020-03-23-3:07
 * synchronized的重入:有父子继承关系,方法都加synchronized修改,也是线程安全
 */


public class SyncDubbo2 {

    static  class Main{
        public int i = 10;
        public  void operationSu(){
            try {
                i--;
                System.out.println("Main print i =" + i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Sub extends Main{
        public synchronized void operationsup(){
            try {
                while (i > 0){
                    i--;
                    System.out.println("sup print i = " + i);
                    Thread.sleep(100);
                    this.operationSu();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final Sub sub = new Sub();
        Thread t1 = new Thread(new Runnable() {
            public void run() {

                sub.operationsup();
            }
        });
        t1.start();
    }
}
