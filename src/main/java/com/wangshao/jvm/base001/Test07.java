package com.wangshao.jvm.base001;

/**
 * @author liutao
 * @create 2020-03-29-23:44
 */


public class Test07 {

    public static void alloc(){
        byte[] b = new byte[2];

    }

    public static void main(String[] args) {
        //tlab分配
        //参数：-XX:+UseTLAB -XX:+PrintTLAB -XX:+PrintGC -XX:TLABSize=102400 -XX:-ResizeTLAB -XX:TLABRefillWasteFraction=100 -XX:-DoEscapeAnalysis -server

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            alloc();

        }
    }
}
