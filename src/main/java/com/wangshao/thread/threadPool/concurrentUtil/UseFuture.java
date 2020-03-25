package com.wangshao.thread.threadPool.concurrentUtil;

import java.util.concurrent.*;

/**
 * @author liutao
 * @create 2020-03-25-15:18
 */


public class UseFuture implements Callable<String> {

    private  String para;

    public UseFuture(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {

        //模拟执行耗时
        Thread.sleep(3000);
        String result = this.para + "处理完成";
        return result;
    }
    //主控函数
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String queryStr = "query";

        //构造futuretask,并且传入需要真正进行业务逻辑处理的类,该类异性是实现了callable接口的类
        FutureTask<String> future = new FutureTask<>(new UseFuture(queryStr));
        FutureTask<String> future1 = new FutureTask<>(new UseFuture(queryStr));

        //创建一个固定线程池且线程数量为1
        ExecutorService service = Executors.newFixedThreadPool(2);

        //这里提交任务future,则开启线程执行realdata的call方法执行
        //submit和executor的区别:1.submit可以传入实现callable结构的实例对象,2.submit方法有返回值
        Future<?> f1 = service.submit(future);
        Future<?> f2 = service.submit(future1);

        System.out.println("请求完毕");

        try {
            //这类可以做额外的数据操作,也及时主程序执行其他业务逻辑
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //调用获取数据方法,如果call()方法没有执行完成,则依然会进行等待,异步调用(等待自己的线程执行完成返回回来)
        System.out.println("数据:" + future.get());
        System.out.println("数据:" + future1.get());
        System.out.println("1111111111111111111111");

        service.shutdown();
    }
}
