package com.wangshao.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author liutao
 * @create 2020-04-03-12:56
 */


public class testRedis {

    private static Jedis jedis = new Jedis("192.168.1.7", 6379);

    @Test
    public void test(){
        jedis.auth("123456");
        //设置string类型
 //       jedis.set("sex", "man");

        //一次性取多个值
//        List<String> list = jedis.mget("name", "age", "sex");
//        for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
//            String  string = (String) iterator.next();
//            System.out.println(string);
//        }

        //hash类型
//        Map<String,String> user = new HashMap<String, String>();
//        user.put("name","huangyuxuan");
//        user.put("age", "23");
//        user.put("sex", "男");
//        jedis.hset("user", user);

//        List<String> rsmap = jedis.hmget("user", "name", "age", "sex");
//        System.out.println(rsmap);
//
//        jedis.hdel("user","age");
//        System.out.println(jedis.hmget("user","age"));//因为删除了,所以返回的是null
//        System.out.println(jedis.hlen("user"));//返回key为user的建中存放的值得个数2
//        System.out.println(jedis.exists("user"));//是否存在key为user的记录,返回true
//        System.out.println(jedis.hkeys("user"));//返回map对象中所有的key
//        System.out.println(jedis.hvals("user"));//返回map对象中的所有value

//        testStr();
//          testList();
          testSet();
    }

    public static void testStr(){

        //----添加数据-------
        jedis.set("name","bhz" );
        System.out.println(jedis.get("name"));

        jedis.append("name", "is my lover"); //拼接
        System.out.println(jedis.get("name"));

        //设置多个键值对
        jedis.mset("name","bhz","age","27","qq","1213212");
        jedis.incr("age");
        System.out.println(jedis.get("name") + "-" +  jedis.get("age") + "-" + jedis.get("qq"));
    }

    public static void testList(){
        //,先移除所有的内容
        jedis.del("java framework");
        System.out.println(jedis.lrange("java framework", 0, -1));
        //先向key  java framework中存放三条数据
        jedis.lpush("java framework","spring");
        jedis.lpush("java framework","struts");
        jedis.lpush("java framework","hebernate");
        //再取出所有数据redis.lrange是按范围取出
        //第一是key,第二个是起始位置,第三个是结束位置,jedis,llen获取长度 -1表示取得所有
        System.out.println(jedis.lrange("java framework",0 , -1));


        jedis.del("java framework");
        jedis.rpush("java framework","spring");
        jedis.rpush("java framework","struts");
        jedis.rpush("java framework","hibernate");
        System.out.println(jedis.lrange("java framework",0 ,-1 ));
    }

    public static void testSet(){
        //添加
        jedis.sadd("user1", "liuling");
        jedis.sadd("user1", "xinxin");
        jedis.sadd("user1", "ling");
        jedis.sadd("user1", "zhangxinxin");
        jedis.sadd("user1", "who");

        //移除nonname
        jedis.srem("user1","who");
        System.out.println(jedis.smembers("user1"));//获取所有加入的value
        System.out.println(jedis.sismember("user1", "who"));//判断who是否是user集合的元素
        System.out.println(jedis.srandmember("user1"));
        System.out.println(jedis.scard("user1"));//返回集合的元素的个数
    }
}
