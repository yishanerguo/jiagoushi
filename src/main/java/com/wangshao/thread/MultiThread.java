package com.wangshao.thread;

/**
 * @author liutao
 * @create 2020-03-23-0:46
 * 关键字synchronized取得的锁都是对象锁,而不是把一段代码(方法)当做锁,
 * 所以代码中哪个线程先执行synchronzied关键字的方法,哪个线程就持有该方法所属对象的锁(lock)
 *
 * 在静态方法上加上synchronized关键字,表示锁定.class类,类一级别的锁(独占.class类)
 */


public class MultiThread {

    private  int num = 0;

    //静态方法里面必须是静态变量
    //static方法里面不能有局部变量
    public  synchronized void  printNum(String tag){
        try {

            if (tag.equals("a")) {
                num = 100;
                System.out.println("tag a, set num over");
                Thread.sleep(1000);
            } else {
                num = 200;
                System.out.println("tag b, set num over");
            }
            System.out.println("tag"+ tag+ ",num+"+ num);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //注意观察run方法输出顺序
    public static void main(String[] args) {

        //两个不同的对象:内部类里面的变量要设置成final
        final MultiThread m1 = new MultiThread();
        final MultiThread m2 = new MultiThread();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                m1.printNum("a");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                m2.printNum("b");
            }
        });

        t1.start();
        t2.start();
    }
}
