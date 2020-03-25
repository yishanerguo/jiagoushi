package com.wangshao.thread.design.master;

import com.sun.xml.internal.fastinfoset.algorithm.BooleanEncodingAlgorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author liutao
 * @create 2020-03-24-20:25
 */


public class Master {

    //1.有一个盛放任务的容器\
    private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<Task>();

    //2.需要有一个盛放worker的集合
    private HashMap<String,Thread> workers = new HashMap<String,Thread>();

    //3.需要有个盛放每一个worker执行=任务的结果集合
    private ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<String,Object>();

    //4.构造方法
    public Master(Worker worker, int workerCount) {
        worker.setWorkQueue(this.workQueue);
        worker.setResultMap(this.resultMap);

        for (int i = 0; i < workerCount; i++) {
            this.workers.put(Integer.toString(i),new Thread(worker));
        }
    }

    //5.需要一个提交任务的方法
    public void submit(Task task){
        this.workQueue.add(task);
    }

    //6.需要有个执行方法,启动所有的worker方法去执行任务
    public void execute(){
        for (Map.Entry<String, Thread> me : workers.entrySet()) {
            me.getValue().start();
        }
    }

    //7.判断是否运行结束的方法
    public Boolean isComplete(){
        for (Map.Entry<String, Thread> me : workers.entrySet()) {
            if(me.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }

    //8.计算结果方法
    public int getResult(){

        int priceResult = 0;
        for(Map.Entry<String,Object> me : resultMap.entrySet()){
            priceResult +=(Integer) me.getValue();
        }
        return priceResult;
    }
}
