package com.wangshao.thread.singleton;

/**
 * @author liutao
 * @create 2020-03-23-22:11
 */


public class Singleton {
    //在类的初始化时候就被加载了,线程被使用之前
    private static class InnerSingleton{
        private static Singleton singleton = new Singleton();
    }

    public static Singleton getInstance() {
        return InnerSingleton.singleton;
    }
}
