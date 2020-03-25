package com.wangshao.thread.rongqi;

import java.security.Key;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author liutao
 * @create 2020-03-24-0:26
 */


public class UseDeque {

    public static void main(String[] args) {
        LinkedBlockingDeque<String> dq = new LinkedBlockingDeque<>(10);
        dq.addFirst("a");
        dq.addFirst("b");
        dq.addFirst("c");
        dq.addFirst("d");
        dq.addFirst("e");
        dq.addLast("f");
        dq.addLast("g");
        dq.addLast("h");
        dq.addLast("i");
        dq.addLast("j");
       // dq.offer("k");
        System.out.println("查看头部元素:" + dq.peekFirst());
        System.out.println("获取尾部元素:" + dq.pollLast());

        Object[] bbjs = dq.toArray();
        for (int i = 0; i < bbjs.length; i++) {
            System.out.println(bbjs[i]);
        }

    }
}
