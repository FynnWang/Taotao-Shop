package com.taotao.rest.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisTest {

    @Test
    public void testJedis() {
        //创建Jedis对象
        Jedis jedis = new Jedis("192.168.0.200", 6379);
        jedis.set("key1", "wangfangood");
        String key1 = jedis.get("key1");
        System.out.println(key1);
        jedis.close();
    }

    @Test
    public void testJedidPool() {
        //创建Jedis连接池
        JedisPool jedisPool = new JedisPool("192.168.0.200", 6379);
        Jedis resource = jedisPool.getResource();
        resource.set("key2", "ok");
        String key2 = resource.get("key2");
        System.out.println(key2);
    }


}
