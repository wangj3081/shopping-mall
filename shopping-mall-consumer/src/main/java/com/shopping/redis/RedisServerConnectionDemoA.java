package com.shopping.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;

/**
 * @ClassName RedisServerConnectionDemoA
 * @Description lettuce 操作 redis
 * @Author wangjian
 * @Date 2020/4/4 11:05 上午
 * @Version 1.0
 **/
public class RedisServerConnectionDemoA {

    public static final String REDIS_HOST = "192.168.205.130";
    public static final int REDIS_PORT = 6379;
    public static final String REDIS_AUTH = "redis";

    public static void main(String[] args) {
//        connectionA();
//        connectionB();
        connectionC();
    }

    private static void connectionC() {
        final String REDIS_ADDRESS = "redis://redis@192.168.205.130:6379/0";
        RedisURI redisURI = RedisURI.create(REDIS_ADDRESS);
        RedisClient client = RedisClient.create(redisURI);
        StatefulRedisConnection<String, String> connect = client.connect();
        System.out.println(connect);
        connect.close();
        client.shutdown();
    }

    private static void connectionB() {
        RedisURI build = RedisURI.Builder.redis(REDIS_HOST)
                .withPort(REDIS_PORT)
                .withPassword(REDIS_AUTH)
                .withDatabase(0).build();
        RedisClient client = RedisClient.create(build);
        StatefulRedisConnection<String, String> connect = client.connect();
        System.out.println(connect); // 连接实例
        connect.close();
        client.shutdown();
    }

    private static void connectionA() {
        RedisURI redisURI = RedisURI.create(REDIS_HOST, REDIS_PORT);
        redisURI.setPassword(REDIS_AUTH);
        redisURI.setDatabase(0); // 连接索引信息
        RedisClient client = RedisClient.create(redisURI);
        StatefulRedisConnection<String, String> connect = client.connect();
        System.out.println(connect);
        connect.close(); // 关闭一个数据库连接
        client.shutdown(); //  关闭客户端
    }
}
