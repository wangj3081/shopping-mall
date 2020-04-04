package com.shopping.util;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;

/**
 * @ClassName RedisClientUtil
 * @Description Redis 工具类
 * @Author wangjian
 * @Date 2020/4/4 11:20 下午
 * @Version 1.0
 **/
public class RedisClientUtil {
    public static final String REDIS_HOST = "192.168.205.130";
    public static final int REDIS_PORT = 6379;
    public static final int REDIS_DATABASE = 0;
    public static final String REDIS_AUTH = "redis";

    public static final ThreadLocal<StatefulRedisConnection> THREAD_LOCAL = new ThreadLocal();

    /*
    *
     * @Author wangjian
     * @Description  获取操作对象
     * @Date 11:26 下午 2020/4/4
     * @Param :
     * @return io.lettuce.core.api.StatefulRedisConnection
     **/
    public static StatefulRedisConnection redisClient() {
        StatefulRedisConnection connect = THREAD_LOCAL.get();
        if (connect == null) {
            RedisURI redisURI = new RedisURI();
            redisURI.setHost(REDIS_HOST);
            redisURI.setPort(REDIS_PORT);
            redisURI.setPassword(REDIS_AUTH);
            redisURI.setDatabase(REDIS_DATABASE);
            RedisClient redisClient = RedisClient.create(redisURI);
            connect = redisClient.connect();
            THREAD_LOCAL.set(connect);
        }
        return connect;
    }

    /*
    *
     * @Author wangjian
     * @Description  关闭连接对象
     * @Date 11:27 下午 2020/4/4
     * @Param :
     * @return void
     **/
    public static void close() {
        StatefulRedisConnection connection = THREAD_LOCAL.get();
        if (connection != null) {
            connection.close();
            THREAD_LOCAL.remove();
        }
    }
}
