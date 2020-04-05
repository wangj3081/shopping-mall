package com.shopping.redis;

import com.shopping.util.RedisClientUtil;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @ClassName RedisPollConnectionDemo
 * @Description Redis 数据库连接池用例
 * @Author wangjian
 * @Date 2020/4/5 12:08 下午
 * @Version 1.0
 **/
public class RedisPollConnectionDemo {

    private static final int MAX_IDLE = 10 ; // 最大的维持连接数
    private static final int MIN_IDLE = 1 ; // 最小的维持连接数
    private static final int MAX_TOTAL = 1 ; //最大的可用数量
    private static final boolean TEST_ON_BORROW = true; // 连接测试后返回

    public static void main(String[] args) throws Exception {
        GenericObjectPoolConfig pollConfig = new GenericObjectPoolConfig();
        pollConfig.setMaxIdle(MAX_IDLE); // 设置最大的维持连接数
        pollConfig.setMinIdle(MIN_IDLE); // 设置最小的维持连接数
        pollConfig.setMaxTotal(MAX_TOTAL); // 设置最大的可用连接数
        pollConfig.setTestOnBorrow(TEST_ON_BORROW); // 连接测试后返回
        // 2、连接池的创建需要依赖于连接的配置类实例
        GenericObjectPool<StatefulRedisConnection<String,String>> poll = ConnectionPoolSupport.createGenericObjectPool(RedisClientUtil::redisConnection, pollConfig);
        for (int i = 0; i < 10; i++) {
            // 获取一个连接
            StatefulRedisConnection<String, String> redisConnection = poll.borrowObject();
            System.out.println("【连接池对象】:" + redisConnection);
            System.out.println("【测试连接】:" + redisConnection.sync().ping());
            redisConnection.close();
        }
    }
}
