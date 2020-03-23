package com.wangshao.thread;

/**
 * @author liutao
 * @create 2020-03-23-13:46
 * synchronized异常:
 * 1.可以在catch里面把错误的信息记录日志,继续执行后面的操作(后面的任务不是一个整体)
 * 2/任务是一个整体,就回滚前面已经执行前面的操作
 */


public class SyncException {

    private int i = 0;
    public synchronized void operation() {
        while (true) {
            try {
                i++;
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + ", i =" + i);
                if (i == 10) {
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("log info i =" + i);

                continue;
            }
        }
    }

    public static void main(String[] args) {
        final SyncException syncException = new SyncException();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                syncException.operation();
            }
        }, "t1");
        t1.start();
    }
}
