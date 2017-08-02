package com.taotao.rest.dao.impl;

import com.taotao.rest.dao.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisClientCluster implements JedisClient
{
    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public String set (String key, String value)
    {
        String result = jedisCluster.set(key, value);
        return result;
    }

    @Override
    public String get (String key)
    {
        String result = jedisCluster.get(key);
        return result;
    }

    @Override
    public long hset (String hkey, String key, String value)
    {
        Long result = jedisCluster.hset(hkey, key, value);
        return result;
    }

    @Override
    public String hget (String hkey, String key)
    {
        String hget = jedisCluster.hget(hkey, key);
        return hget;
    }

    @Override
    public long incr (String key)
    {
        Long incr = jedisCluster.incr(key);
        return incr;
    }


    @Override
    public long expire (String key, int second)
    {
        Long expire = jedisCluster.expire(key, second);
        return expire;
    }

    @Override
    public long ttl (String key)
    {
        Long ttl = jedisCluster.ttl(key);
        return ttl;
    }
}
