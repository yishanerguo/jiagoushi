package com.wangshao.zookeeper.zkClient.watcher;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;

/**
 * @author liutao
 * @create 2020-04-06-23:55
 */


public class ZkClientWatcher2 {

    /** zookeeper地址 */
    static final String CONNECT_ADDR = "192.168.1.10:2181,192.168.1.11:2181,192.168.1.12:2181";
    /** session超时时间 */
    static final int SESSION_OUTTIME = 5000;//ms

    public static void main(String[] args) throws Exception {
        ZkClient zkc = new ZkClient(new ZkConnection(CONNECT_ADDR), 5000);

        zkc.createPersistent("/super", "1234");

        //监听节点的变化
        zkc.subscribeDataChanges("/super", new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("变更节点为:" + dataPath  + ",变更内容为:" + data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("删除的节点为:" + dataPath);
            }
        });

        Thread.sleep(3000);
        zkc.writeData("/super", "456", -1);
        Thread.sleep(1000);

        zkc.delete("/super");
        Thread.sleep(Integer.MAX_VALUE);
    }

}
