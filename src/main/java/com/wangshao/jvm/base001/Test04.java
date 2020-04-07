package com.wangshao.jvm.base001;

/**
 * @author liutao
 * @create 2020-03-29-22:28
 */


public class Test04 {

    //-Xss1m
    //-Xss5m

    //栈调用深度
    private static int count;

    public static void recursion(){
        count++;
        recursion();
    }

    public static void main(String[] args) {
        try {
            recursion();
        } catch (Exception e) {
            System.out.println("调用最大深入:" + count);
            e.printStackTrace();
        }
    }
}
