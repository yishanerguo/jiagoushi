package com.wangshao.zookeeper.zookeeper.base;



import org.apache.zookeeper.*;



import java.util.concurrent.CountDownLatch;

/**
 * @author liutao
 * @create 2020-04-06-10:09
 */


public class ZookeeperBase {

    //zookeeper地址
    static final String CONNECT_ADDR = "192.168.1.10:2181,192.168.1.11:2181,192.168.1.12:2181";

    //session超时时间
    static final int SESSION_OUTTIME = 2000;//ms

    //信号量,阻塞程序执行,用于等待zookeeper连接成功,发送成功信息
    static final CountDownLatch  countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {

        ZooKeeper zk = new ZooKeeper(CONNECT_ADDR, SESSION_OUTTIME, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                //获取事件的状态
                Event.KeeperState state = watchedEvent.getState();
                Event.EventType eventType = watchedEvent.getType();

                //如果是建立连接
                if (Event.KeeperState.SyncConnected == state) {
                    if(Event.EventType.None == eventType){
                        //如果建立连接成功,则发送信号量,让后续阻塞程序向下执行
                        countDownLatch.countDown();
                        System.out.println("zk 建立连接");
                    }
                }

            }
        });

        //进行阻塞
        countDownLatch.await();

        System.out.println(".............");

        //创建父节点
//        zk.create("/testroot","testroot".getBytes() , ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        //创建子节点
//        zk.create("/testroot/children","children data".getBytes() , ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        //获取节点信息
//        byte[] data = zk.getData("/testroot", false, null);
//        System.out.println(new String(data));
//        byte[] data1 = zk.getData("/testroot/children", false, null);
//        System.out.println(new String(data1));
//        System.out.println(zk.getChildren("/testroot",false));

        //修改节点的值
//        zk.setData("/testroot","modify data root".getBytes() ,-1);
//        byte[] data = zk.getData("/testroot", false, null);
//        System.out.println(new String(data));

        //判断节点是否存在
        System.out.println(zk.exists("/testroot/children",false));

        //删除节点
//        zk.delete("/testroot/children",-1);
//        System.out.println(zk.exists("/testroot/children",false));
        zk.close();
    }
}
