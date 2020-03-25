package com.wangshao.thread.rongqi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author liutao
 * @create 2020-03-24-0:16
 */


public class UseQueue {

    public static void main(String[] args) throws Exception {

        //高性能无阻塞无界队列: ConcurrentLinkedQueue
//        ConcurrentLinkedQueue<String> q = new ConcurrentLinkedQueue<String>();
//        q.offer("a");
//        q.offer("b");
//        q.offer("c");
//        q.offer("d");
//        q.add("e");
//
//        System.out.println(q.poll()); //a 从头部去除元素,并且从队列里删除
//        System.out.println(q.size()); //4
//        System.out.println(q.peek()); //b
//        System.out.println(q.size()); //4

        //数组的阻塞队列
//        ArrayBlockingQueue<String> array = new ArrayBlockingQueue<String>(5);
//        array.put("a");
//        array.put("b");
//        array.add("c");
//        array.add("e");
//        array.add("f");
//
//        System.out.println(array.offer("f",3 , TimeUnit.SECONDS));
//        System.out.println(array.size());


        //链表的阻塞队列
//        LinkedBlockingQueue<String> lq  = new LinkedBlockingQueue<String>();
//        lq.offer("a");
//        lq.offer("b");
//        lq.offer("c");
//        lq.offer("d");
//        lq.offer("e");
//        lq.add("f");
//        System.out.println(lq.size());
//
//        for (Iterator iterator = lq.iterator(); iterator.hasNext(); ) {
//            String string = (String) iterator.next();
//            System.out.println(string);
//        }
//
//        List<String> list = new ArrayList<String>();
//        System.out.println(lq.drainTo(list,1 ));
//        System.out.println(list.size());
//        for (String string : list) {
//            System.out.println(string);
//        }
        //没有缓冲的队列
        final SynchronousQueue<String> sq = new SynchronousQueue<String>();
        //sq.add("nihao");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(sq.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //调用add不等于往队列里面加元素
                sq.add("assas");
            }
        });
        t2.start();
    }

}
