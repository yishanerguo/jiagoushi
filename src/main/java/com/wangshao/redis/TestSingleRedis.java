package com.wangshao.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.*;



import java.util.*;

/**
 * @author liutao
 * @create 2020-04-03-16:12
 */


public class TestSingleRedis {

    //1.单独连接一台redis服务器
    private static Jedis jedis;

    //2.主从,哨兵,使用分片
    private static ShardedJedis shardedJedis;

    //3.连接池
    private static  ShardedJedisPool shardedJedisPool;

    @BeforeClass
    public static void setUpBeforeClass(){
        //单个节点
        jedis = new Jedis("192.168.1.7", 6379);

        //分片
        List<JedisShardInfo> shards = Arrays.asList(
          new JedisShardInfo("192.168.1.10",6379),
                new JedisShardInfo("192.168.1.13",6379));
        shardedJedis = new ShardedJedis(shards);

        GenericObjectPoolConfig goConfig = new GenericObjectPoolConfig();
        goConfig.setMaxTotal(100);
        goConfig.setMaxIdle(20);
        goConfig.setMaxWaitMillis(-1);
        goConfig.setTestOnBorrow(true);

        shardedJedisPool = new ShardedJedisPool(goConfig, shards);
        jedis.auth("123456");

    }

    public static void tearDownAfterClass(){
        jedis.disconnect();
        shardedJedis.disconnect();
        shardedJedisPool.destroy();
    }

    @Test
    public void testString(){


        //-----添加数据----------
        jedis.set("name","bhz");//向key-->name中放入了value-->xinxin
        System.out.println(jedis.get("name"));//执行结果：xinxin

        jedis.append("name", " is my lover"); //拼接
        System.out.println(jedis.get("name"));

        jedis.del("name");  //删除某个键
        System.out.println(jedis.get("name"));
        //设置多个键值对
        jedis.mset("name","bhz","age","27","qq","174754613");
        jedis.incr("age"); //进行加1操作
        System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
    }

    /**
     * redis操作map
     */
    @Test
    public void testMap(){
        //-----添加数据----------
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "xinxin");
        map.put("age", "22");
        map.put("qq", "123456");
        jedis.hmset("user",map);
        //取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println(rsmap);
        //删除map中的某个键值
        jedis.hdel("user","age");
        System.out.println(jedis.hmget("user", "age")); //因为删除了，所以返回的是null
        System.out.println(jedis.hlen("user")); //返回key为user的键中存放的值的个数2
        System.out.println(jedis.exists("user"));//是否存在key为user的记录 返回true
        System.out.println(jedis.hkeys("user"));//返回map对象中的所有key
        System.out.println(jedis.hvals("user"));//返回map对象中的所有value

        Iterator<String> iter = jedis.hkeys("user").iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ":" + jedis.hmget("user",key));
        }
    }

    /**
     * jedis操作list
     */
    @Test
    public void testList(){
        //开始前，先移除所有的内容
        jedis.del("java framework");
        System.out.println(jedis.lrange("java framework",0,-1));
        //先向key java framework中存放三条数据
        jedis.lpush("java framework","spring");
        jedis.lpush("java framework","struts");
        jedis.lpush("java framework","hibernate");
        //再取出所有数据jedis.lrange是按范围取出，
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
        System.out.println(jedis.lrange("java framework",0,-1));

        jedis.del("java framework");
        jedis.rpush("java framework","spring");
        jedis.rpush("java framework","struts");
        jedis.rpush("java framework","hibernate");
        System.out.println(jedis.lrange("java framework",0,-1));
    }

    /**
     * jedis操作set
     */
    @Test
    public void testSet(){
        //添加
        jedis.sadd("user1","liuling");
        jedis.sadd("user1","xinxin");
        jedis.sadd("user1","ling");
        jedis.sadd("user1","zhangxinxin");
        jedis.sadd("user1","who");
        //移除noname
        jedis.srem("user1","who");
        System.out.println(jedis.smembers("user1"));//获取所有加入的value
        System.out.println(jedis.sismember("user1", "who"));//判断 who 是否是user集合的元素
        System.out.println(jedis.srandmember("user1"));
        System.out.println(jedis.scard("user1"));//返回集合的元素个数
    }

    @Test
    public void testRLpush(){
        //jedis 排序
        //注意,此处的rpush和lpush是list的操作,是一个双向链表(从表现来看)
        jedis.del("a");//先清除数据，再加入数据进行测试
        jedis.rpush("a", "1");
        jedis.lpush("a","6");
        jedis.lpush("a","3");
        jedis.lpush("a","9");
        System.out.println(jedis.lrange("a",0, -1));
        jedis.sort("a");
        System.out.println(jedis.sort("a"));//输入排序后结果
        System.out.println(jedis.lrange("a",0 ,-1 ));
    }

    @Test
    public void testTrans(){
        long start = System.currentTimeMillis();
        Transaction tx = jedis.multi();
        for (int i = 0; i < 1000; i++) {
            tx.set("t"+i, "t"+i);
        }
        //System.out.println(tx.get("t1000").get());

        List<Object> results = tx.exec();
        long end = System.currentTimeMillis();
        System.out.println("transcation set:" + ((end-start)/1000.0) + "seconds");
    }

    @Test
    public void testPipelined(){
        Pipeline pipelined = jedis.pipelined();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            pipelined.set("p" + i, "p" + i);
        }
       // System.out.println(pipelined.get("p1000").get());
        List<Object> results = pipelined.syncAndReturnAll();
        long end = System.currentTimeMillis();
        System.out.println("pipelined set:" + ((end -start)/1000.0) + "seconds");
    }

    @Test
    public void testPipelineTrans() {
        long start = System.currentTimeMillis();
        Pipeline pipeline = jedis.pipelined();
        pipeline.multi();
        for (int i = 0; i < 100000; i++) {
            pipeline.set("" + i, "" + i);
        }
        pipeline.exec();
        List<Object> results = pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        System.out.println("Pipelined transaction SET: " + ((end - start)/1000.0) + " seconds");
    }

    @Test
    public void testShard() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String result = shardedJedis.set("shard" + i, "n" + i);
        }
        long end = System.currentTimeMillis();
        System.out.println("shard SET: " + ((end - start)/1000.0) + " seconds");
    }

    @Test
    public void testShardpipelined() {
        ShardedJedisPipeline pipeline = shardedJedis.pipelined();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            pipeline.set("sp" + i, "p" + i);
        }
        List<Object> results = pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        System.out.println("shardPipelined SET: " + ((end - start)/1000.0) + " seconds");
    }

    @Test
    public void testShardPool(){
        ShardedJedis sj = shardedJedisPool.getResource();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String result = sj.set("spn" + i, "n" + i);
        }
        long end = System.currentTimeMillis();
        //shardedJedisPool.returnResource(sj);
        System.out.println("shardPool SET: " + ((end - start)/1000.0) + " seconds");
    }
}
