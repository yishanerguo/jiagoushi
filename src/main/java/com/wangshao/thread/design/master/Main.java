package com.wangshao.thread.design.master;

import java.util.Random;

/**
 * @author liutao
 * @create 2020-03-24-21:03
 */


public class Main {

    public static void main(String[] args) {

        Master master = new Master(new Worker(), 20);

        Random r = new Random();

        for (int i = 0; i <= 100; i++) {
            Task t = new Task();
            t.setId(i);
            t.setPrice(r.nextInt(1000));
            System.out.println(r.nextInt(1090));
            master.submit(t);
        }

        master.execute();
        long start = System.currentTimeMillis();

        while (true) {
            if (master.isComplete()) {
                long end = System.currentTimeMillis() - start;
                int result = master.getResult();
                System.out.println("最终结果:" + result + ", 执行时间:" + end);
                break;
            }
        }
    }
}
