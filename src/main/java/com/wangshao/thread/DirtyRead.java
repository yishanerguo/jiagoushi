package com.wangshao.thread;

/**
 * @author liutao
 * @create 2020-03-23-2:31
 * 业务整体需要使用完成的synchronized,保持业务的原子性
 */


public class DirtyRead {

    private String username = "bjsxt";
    private String password = "123";

    public synchronized void setValue(String username,String password){
        this.username = username;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.password = password;

        System.out.println("setValue方法得到:username = " + this.username + ", password =" + this.password);
    }

    public void getValue(){
        System.out.println("getValue方法得到:" + this.username + ", password =" + this.password);
    }

    public static void main(String[] args) throws InterruptedException {

        final  DirtyRead dirtyRead = new DirtyRead();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                dirtyRead.setValue("z3", "456");
            }
        }, "t1");
        t1.start();
//        Thread.sleep(4000);
        dirtyRead.getValue();
    }
}
