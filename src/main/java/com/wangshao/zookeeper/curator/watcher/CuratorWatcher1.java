package com.wangshao.zookeeper.curator.watcher;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author liutao
 * @create 2020-04-07-11:10
 */


public class CuratorWatcher1 {

    /** zookeeper地址 */
    static final String CONNECT_ADDR = "192.168.1.10:2181,192.168.1.11:2181,192.168.1.12:2181";
    /** session超时时间 */
    static final int SESSION_OUTTIME = 5000;//ms

    public static void main(String[] args) throws Exception {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);

        CuratorFramework cf = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_ADDR)
                .sessionTimeoutMs(SESSION_OUTTIME)
                .retryPolicy(retryPolicy)
                .build();

        cf.start();

        //建立一个cache缓存
        final NodeCache cache = new NodeCache(cf, "/super",false);
        cache.start(true);
        cache.getListenable().addListener(new NodeCacheListener() {
            /**
             * 触发事件为创建节点和更新节点,在删除节点的时候并不触发此操作
             * @throws Exception
             */
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("路径为:" + cache.getCurrentData().getPath());
                System.out.println("数据为:" + new String(cache.getCurrentData().getData()));
                System.out.println("状态为:" + cache.getCurrentData().getStat());
                System.out.println("--------------------------------------------");
            }
        });

        Thread.sleep(1000);
        cf.create().forPath("/super", "123".getBytes());

        Thread.sleep(1000);
        cf.setData().forPath("/super", "456".getBytes());

        Thread.sleep(1000);
        cf.delete().forPath("/super");

        Thread.sleep(Integer.MAX_VALUE);
    }
}
