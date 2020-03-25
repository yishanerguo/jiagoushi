package com.wangshao.thread.rongqi;



import java.util.concurrent.DelayQueue;

/**
 * @author liutao
 * @create 2020-03-24-16:42
 */


public class WangBa implements Runnable{

    private DelayQueue<WangMin> queue = new DelayQueue<WangMin>();

    public  boolean yinye = true;

    public void shangji(String name, String id,int money){
        WangMin man = new WangMin(name, id, 1000 * money + System.currentTimeMillis());
        System.out.println("网名:" + man.getName()+"身份证:"+man.getId()+ "交钱:"+ money+"块,开始上机...");
        this.queue.add(man);
    }

    public void xiaji(WangMin wangMin){
        System.out.println("网名:" + wangMin.getName()+"身份证:"+wangMin.getId()+ "时间下机.....");
    }
    @Override
    public void run() {
        while (yinye) {
            try {
                WangMin wangMin = queue.take();
                xiaji(wangMin);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        try {
            System.out.println("网吧开始营业...");
            WangBa feiyu = new WangBa();
            Thread shangwang = new Thread(feiyu);
            shangwang.start();

            feiyu.shangji("路人甲", "123", 1);
            feiyu.shangji("路人乙", "234", 10);
            feiyu.shangji("路人丙", "345", 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
