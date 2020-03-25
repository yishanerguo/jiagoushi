package com.wangshao.thread.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author liutao
 * @create 2020-03-25-1:03
 */
class Temp extends  Thread{

    public void run(){
        System.out.println("run");
    }
}


public class ScheduleJob {

    public static void main(String[] args) {
        Temp command = new Temp();

        ScheduledExecutorService schedulef = Executors.newScheduledThreadPool(1);

        //schedulef.scheduleWithFixedDelay(command, 1, 1, TimeUnit.SECONDS);
        schedulef.scheduleAtFixedRate(command, 1, 3, TimeUnit.SECONDS);
    }
}
