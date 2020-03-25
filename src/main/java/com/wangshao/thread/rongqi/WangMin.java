package com.wangshao.thread.rongqi;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author liutao
 * @create 2020-03-24-16:44
 */


public class WangMin implements Delayed {

    private String name;

    private String id; //身份证

    private long endTime; //截止时间

    //定义时间工具类
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public WangMin(String name, String id, long endTime) {
        this.name = name;
        this.id = id;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    /**
     * 用来判断是否到了截止时间
     * @param unit
     * @return
     */
    public long getDelay(TimeUnit unit){
       // return unit.convert(endTime, TimeUnit.MILLISECONDS) - unit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);

        return endTime - System.currentTimeMillis();
    }


    @Override
    public int compareTo(Delayed delayed) {
        WangMin wangMin = (WangMin) delayed;
        return this.getDelay(this.timeUnit) - wangMin.getDelay(this.timeUnit) > 0 ? 1 : 0;
    }
}
