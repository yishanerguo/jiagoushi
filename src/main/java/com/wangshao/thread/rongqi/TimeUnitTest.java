package com.wangshao.thread.rongqi;

import java.util.concurrent.TimeUnit;

/**
 * @author liutao
 * @create 2020-03-23-23:25
 */


public class TimeUnitTest {

    private TimeUnit timeUnit = TimeUnit.DAYS;


    public void outInfo(){
        System.out.println(timeUnit.name());
        System.out.println(timeUnit.toDays(1));
        System.out.println(timeUnit.toHours(1));
        System.out.println(timeUnit.toMinutes(1));
        System.out.println(timeUnit.toSeconds(1));
        System.out.println(timeUnit.toMillis(1));
        System.out.println(timeUnit.toNanos(1));
        System.out.println(timeUnit.convert(1, TimeUnit.DAYS) + timeUnit.name());
        System.out.println(timeUnit.convert(24, TimeUnit.HOURS) + timeUnit.name());
        System.out.println(timeUnit.convert(1440, TimeUnit.MINUTES) + timeUnit.name());
        System.out.println("------------------------------");
    }

    public static void main(String[] args) {
        TimeUnitTest tut = new TimeUnitTest();
        tut.outInfo();
    }
}
