package com.wangshao.thread.volatilee;

/**
 * @author liutao
 * @create 2020-03-23-16:43
 */


public class RunThread extends Thread{
    //volatile
    private  boolean isRunning = true;

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void run() {
        System.out.println("进入run方法");
        int i= 0;
        while (isRunning == true) {
            //...
        }
        System.out.println("线程停止");
    }

    public static void main(String[] args) throws InterruptedException {
        RunThread runThread = new RunThread();
        runThread.start();
        Thread.sleep(3000);
        runThread.setRunning(false);
//        System.out.println("isRunning的值已经被设置了false");
//        Thread.sleep(1000);
//        System.out.println(runThread.isRunning);
    }
}
