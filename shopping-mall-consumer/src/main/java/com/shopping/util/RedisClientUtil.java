package com.shopping.util;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @ClassName RedisClientUtil
 * @Description Redis 工具类
 * @Author wangjian
 * @Date 2020/4/4 11:20 下午
 * @Version 1.0
 **/
public class RedisClientUtil {
    private static final String REDIS_HOST = "192.168.205.130";
    private static final int REDIS_PORT = 6379;
    private static final int REDIS_DATABASE = 0;
    private static final String REDIS_AUTH = "redis";
    private static final int MAX_IDLE = 10 ; // 最大的维持连接数
    private static final int MIN_IDLE = 1 ; // 最小的维持连接数
    private static final int MAX_TOTAL = 1 ; //最大的可用数量
    private static final boolean TEST_ON_BORROW = true; // 连接测试后返回
    private static GenericObjectPool<StatefulRedisConnection<String,String>> poll; // 连接池操作对象

    private static final ThreadLocal<StatefulRedisConnection> THREAD_LOCAL = new ThreadLocal();

    static {
        RedisURI redisURI = new RedisURI();
        redisURI.setHost(REDIS_HOST);
        redisURI.setPort(REDIS_PORT);
        redisURI.setPassword(REDIS_AUTH);
        redisURI.setDatabase(REDIS_DATABASE);
        RedisClient redisClient = RedisClient.create(redisURI); // 数据库连接对象
        GenericObjectPoolConfig pollConfig = new GenericObjectPoolConfig();
        pollConfig.setMaxIdle(MAX_IDLE); // 设置最大的维持连接数
        pollConfig.setMinIdle(MIN_IDLE); // 设置最小的维持连接数
        pollConfig.setMaxTotal(MAX_TOTAL); // 设置最大的可用连接数
        pollConfig.setTestOnBorrow(TEST_ON_BORROW); // 连接测试后返回
        poll = ConnectionPoolSupport.createGenericObjectPool(()-> redisClient.connect(), pollConfig);
    }

    /*
    *
     * @Author wangjian
     * @Description  获取操作对象
     * @Date 11:26 下午 2020/4/4
     * @Param :
     * @return io.lettuce.core.api.StatefulRedisConnection
     **/
    public static StatefulRedisConnection<String, String> redisConnection() {
        StatefulRedisConnection connect = THREAD_LOCAL.get();
        if (connect == null) {
            connect = build();
//            connect = redisClient.connect();
            THREAD_LOCAL.set(connect);
        }
        return connect;
    }

    /*
    *
     * @Author wangjian
     * @Description  构建连接对象
     * @Date 12:37 下午 2020/4/5
     * @Param :
     * @return io.lettuce.core.RedisClient
     **/
    private static StatefulRedisConnection<String, String> build() {

        try {
            return poll.borrowObject();
        } catch (Exception e) {
            return null;
        }
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
