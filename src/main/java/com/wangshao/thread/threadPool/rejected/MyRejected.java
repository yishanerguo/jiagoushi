package com.wangshao.thread.threadPool.rejected;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author liutao
 * @create 2020-03-25-1:56
 */


public class MyRejected implements RejectedExecutionHandler {

    public MyRejected(){

    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        //可以把被拒绝的记录日志,起一个定时任务,重新加入队列
        System.out.println("d自定义处理....");
        System.out.println("当前被拒绝任务为:" + r.toString());
    }
}
