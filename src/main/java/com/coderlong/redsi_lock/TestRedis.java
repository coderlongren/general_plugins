package com.coderlong.redsi_lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestRedis {
    public static void main(String[] args) {
        Jedis instance = new Jedis("127.0.0.1", 6379);
        Long res = instance.setnx("test", "ABCD");
        Long s = instance.setnx("name1", "ABCD");
        instance.set("a", "s","nx", "nx", 3);
        if (res == 1) {
            System.out.println("key test setnx success!");
        } else {
            System.out.println("key test exists");
        }
    }
}
