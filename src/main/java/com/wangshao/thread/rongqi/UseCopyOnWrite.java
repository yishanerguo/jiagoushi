package com.wangshao.thread.rongqi;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author liutao
 * @create 2020-03-24-0:02
 */


public class UseCopyOnWrite {

    public static void main(String[] args) {

        CopyOnWriteArrayList<String> cwal  = new CopyOnWriteArrayList<String>();
        CopyOnWriteArraySet<String> cwas = new CopyOnWriteArraySet<>();
    }
}
