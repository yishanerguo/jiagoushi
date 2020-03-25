package com.wangshao.thread.design.future;

import javax.xml.crypto.Data;

/**
 * @author liutao
 * @create 2020-03-24-17:48
 */


public class Main {

    public static void main(String[] args) throws InterruptedException{
        FutureClient fc = new FutureClient();
        //先直接返回一个空数据对象
        Date date = fc.request("请求参数");
        System.out.println("请求发送成功...");
        System.out.println("做其他事情...");

        //真正需要数据的时候
        String result = date.getRequest();
        System.out.println(result);
    }
}
