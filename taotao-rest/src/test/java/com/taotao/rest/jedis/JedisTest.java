package com.taotao.rest.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;

public class JedisTest
{
    @Test
    public void testJedis ()
    {
        //创建Jedis对象
        Jedis jedis = new Jedis("192.168.0.200", 6379);
        jedis.set("key1", "wangfangood");
        String key1 = jedis.get("key1");
        System.out.println(key1);
        jedis.close();
    }

    @Test
    public void testJedidPool ()
    {
        //创建Jedis连接池
        JedisPool jedisPool = new JedisPool("192.168.0.200", 6379);
        Jedis resource = jedisPool.getResource();
        resource.set("key2", "ok");
        String key2 = resource.get("key2");
        System.out.println(key2);
        resource.close();
        jedisPool.close();
    }

    @Test
    public void testJedisCluster ()
    {
        HashSet nodes = new HashSet();
        nodes.add(new HostAndPort("192.168.0.200", 7001));
        nodes.add(new HostAndPort("192.168.0.200", 7002));
        nodes.add(new HostAndPort("192.168.0.200", 7003));
        nodes.add(new HostAndPort("192.168.0.200", 7004));
        nodes.add(new HostAndPort("192.168.0.200", 7005));
        nodes.add(new HostAndPort("192.168.0.200", 7006));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("key2", "wangfan");
        String key2 = jedisCluster.get("key2");
        System.out.println(key2);
        jedisCluster.close();
    }

    @Test
    public void testRedisSpring ()
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        JedisPool jedisPool = (JedisPool) applicationContext.getBean("redisClient");
        Jedis jedis = jedisPool.getResource();
        String key1 = jedis.get("key1");
        System.out.println(key1);
        jedis.close();
    }

    @Test
    public void testRedisClusterSpring ()
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        JedisCluster jedisCluster = (JedisCluster) applicationContext.getBean("redisClient");
        String key2 = jedisCluster.get("key2");
        System.out.println(key2);
        jedisCluster.close();
    }
}
