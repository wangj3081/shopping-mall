package com.shopping.redis;

import com.shopping.util.RedisClientUtil;
import io.lettuce.core.GeoArgs;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AsyncCommandDemo
 * @Description 基于lettuce异步操作Redis实例
 * @Author wangjian
 * @Date 2020/4/4 11:28 下午
 * @Version 1.0
 **/
public class AsyncCommandDemo {

    public static void main(String[] args) throws Exception {
        RedisAsyncCommands redisAsyncCommands = RedisClientUtil.redisConnection().async();
//        operation(redisAsyncCommands);
//        hash(redisAsyncCommands);
//        list(redisAsyncCommands);
//        set(redisAsyncCommands);
        zSet(redisAsyncCommands);
        // 地理位置
//        geoLocation(redisAsyncCommands);
    }

    private static void geoLocation(RedisAsyncCommands redisAsyncCommands) throws InterruptedException, ExecutionException {
        redisAsyncCommands.geoadd("point", 116.010000, 39.010000, "不知道的地界");
        redisAsyncCommands.geoadd("point", 116.010000, 39.015000, " 不知道的地界二号");
        RedisFuture point = redisAsyncCommands.georadius("point", 116.000000, 39.000000, 2000, GeoArgs.Unit.m);
        System.out.println("【附近的位置】:" + point.get());
        RedisClientUtil.close();
    }

    private static void zSet(RedisAsyncCommands redisAsyncCommands) throws InterruptedException, ExecutionException {
        redisAsyncCommands.zadd("language", 1, "Java");
        redisAsyncCommands.zadd("language", 2, "C++");
        redisAsyncCommands.zadd("language", 3, "C");
        System.out.println("升序排序:" + redisAsyncCommands.zrange("language", 0 , -1).get());
        System.out.println("降序排序:" + redisAsyncCommands.zrevrange("language", 0, -1).get());

        System.out.println("带分数升序:" + redisAsyncCommands.zrangeWithScores("language", 0, -1).get());
        ;
        System.out.println("带分数降序:" + redisAsyncCommands.zrevrangeWithScores("language", 0, -1).get());
        RedisClientUtil.close();
    }

    private static void set(RedisAsyncCommands redisAsyncCommands) throws InterruptedException, ExecutionException {
        // set 操作
        redisAsyncCommands.sadd("one", "a", "a", "b", "c", "c").get();
        redisAsyncCommands.sadd("two", "a", "d", "e","f").get();
        System.out.println("交集运算:" + redisAsyncCommands.sinter("one", "two").get());
        // 第一个set存在第二个不存在的内容
        System.out.println("差集运算:" + redisAsyncCommands.sdiff("one", "two").get());
        // 第二个set存在第一个不存在的内容
        System.out.println("差集运算:" + redisAsyncCommands.sdiff("two", "one").get());
        System.out.println("并集运算:" + redisAsyncCommands.sunion("one", "two").get());
        RedisClientUtil.close();
    }

    private static void list(RedisAsyncCommands redisAsyncCommands) throws InterruptedException, ExecutionException {
        redisAsyncCommands.lpush("message-queue", "messageA","messageB","messageC").get();
        RedisFuture size = redisAsyncCommands.llen("message-queue");
        System.out.println("【List长度】:" + size.get());
        System.out.println("【List内容】:" + redisAsyncCommands.lrange("message-queue", 0, -1).get());
        for (int i = 0; i < (Long)size.get(); i++) {
            System.out.println("【弹出内容】:" + redisAsyncCommands.rpop("message-queue").get());
        }
        RedisClientUtil.close();
    }

    private static void hash(RedisAsyncCommands redisAsyncCommands) throws InterruptedException, ExecutionException {
        // hash操作,异步处理加入 get
        redisAsyncCommands.hset("member-A", "name", "李四").get();
        // 在Hash操作中可以一次性保存一个Map数据之中
        Map<String, String> map = new HashMap<>();
        map.put("age", String.valueOf(18));
        map.put("salar", String.valueOf(2600.0));
        redisAsyncCommands.hmset("member-A", map).get();
        System.out.println(redisAsyncCommands.hgetall("member-A").get());
        RedisClientUtil.close();
    }

    /*
    *
     * @Author wangjian
     * @Description  常规操作
     * @Date 11:58 下午 2020/4/4
     * @Param redisAsyncCommands:
     * @return void
     **/
    private static void operation(RedisAsyncCommands redisAsyncCommands) {
        redisAsyncCommands.setex("Hello", 2, "你好吗");
        redisAsyncCommands.setex("World", 2, "你好吗");
        RedisFuture future = redisAsyncCommands.get("Hello");
        try {
            System.out.println(future.get());
            RedisFuture keys = redisAsyncCommands.keys("*");
            System.out.println("所有key:" + keys.get());
            TimeUnit.SECONDS.sleep(3);
            // 如果使用同一个future对象获取内容则一直都是一致的，与时间无关系，除非重新获取
            System.out.println(future.get());
            TimeUnit.SECONDS.sleep(3);
            System.out.println("2秒后:" + redisAsyncCommands.get("Hello").get());
            RedisClientUtil.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
