package com.wangshao.thread.design.master;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author liutao
 * @create 2020-03-24-20:29
 */


public class Worker implements Runnable{

    private ConcurrentLinkedQueue<Task> workQueue;

    private ConcurrentHashMap<String,Object> resultMap;

    public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue) {
        this.workQueue = workQueue;
    }

    public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    @Override
    public void run() {
        while (true) {
            Task input = this.workQueue.poll();
            if (input == null) {
                break;
            }
            Object output = handle(input);
            this.resultMap.put(Integer.toString(input.getId()), output);
        }
    }

    private Object handle(Task input){
        Object output = null;
        try {
            Thread.sleep(500);
            output = input.getPrice();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output;
    }
}
