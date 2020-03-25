package com.wangshao.thread.rongqi;



import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author liutao
 * @create 2020-03-24-16:24
 */


public class UsePriorityBlockingQueue {

    public static void main(String[] args) throws InterruptedException {
        //优先级的队列
        PriorityBlockingQueue<Task> q = new PriorityBlockingQueue<Task>();
        Task t1  = new Task();
        t1.setId(3);
        t1.setName("id为3");
        Task t2 = new Task();
        t2.setId(4);
        t2.setName("id为4");
        Task t3 = new Task();
        t3.setId(1);
        t3.setName("id为1");

        q.add(t1);
        q.add(t2);
        q.add(t3);

        System.out.println("容器:" + q);
        //调用take方法才排序
        System.out.println(q.take().getId());
        System.out.println("容器:" + q);
    }
}
