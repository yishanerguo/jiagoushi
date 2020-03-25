package com.wangshao.thread.rongqi;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liutao
 * @create 2020-03-23-23:41
 */


public class UseConcurrentMap {

    public static void main(String[] args) {
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String, Object>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        map.putIfAbsent("k3", "xxx");
        System.out.println(map.get("k2"));
        System.out.println(map.size());

        for (Map.Entry<String,Object> me : map.entrySet()){
            System.out.println("key:" + me.getKey() + ", value" + me.getValue());
        }
    }
}
