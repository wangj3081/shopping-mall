package com.shopping.redis;

import com.alibaba.fastjson.JSONObject;
import com.shopping.ConsumerStartMain;
import com.shopping.pojo.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * @ClassName RedisTest
 * @Description TODO
 * @Author wangjian
 * @Date 2020/4/5 3:39 下午
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ConsumerStartMain.class)
public class RedisTest {
    @Autowired
    private LettuceConnectionFactory factory;

//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;

//    @Resource(name = "redisTemplate")
    @Resource(name = "redisJsonTemplate")
    private RedisTemplate<String, Object> redisTemplate;


    /*
    * 使用 pipeline 的好处为一次连接可以批量操作redis数据库，不用反复开关数据库连接处理，以此相对单个操作的处理性能更优
     * @Author wangjian
     * @Description  pipeline 流水线对象处理批量插入数据
     * @Date 9:25 下午 2020/4/5
     * @Param :
     * @return void
     **/
    @Test
    public void templatePipelineTest() {
        // 流水线处理，通过一个连接批量操作redis数据库
        List<Object> result = redisTemplate.executePipelined(new RedisCallback<Member>() {
            @Override
            public Member doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer keySerializer = redisTemplate.getKeySerializer(); // 自定义存储key的设置方式
                RedisSerializer valueSerializer = redisTemplate.getValueSerializer(); // 自定义存储value的序列化方式
                for (int i = 0; i < 10; i++) {
                    Member member = new Member();
                    member.setName("member-" + i);
                    member.setSalary(25000.00);
                    member.setBirthday(new Date());
                    member.setAge(i);
                    try {
                        // 由于该操作需要将 key 与 value 都转换成字节数组，此处需要增加转换处理
                        String key = "member-" + i;
                        /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ObjectOutputStream oos = new ObjectOutputStream(baos);
                        oos.writeObject(member); // 写入对象内容
                        oos.flush();*/
//                        redisConnection.set(key.getBytes(), baos.toByteArray());
                        // 按照指定格式序列化存储数据内容
                        redisConnection.set(keySerializer.serialize(key), valueSerializer.serialize(member));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        });
        System.err.println(result);
        System.err.println("**************************");
    }

    /*
    *
     * @Author wangjian
     * @Description  通过 pipeline 批量获取redis数据
     * @Date 9:26 下午 2020/4/5
     * @Param :
     * @return void
     **/
    @Test
    public void getRedisByPipelineTest() {
//       Object byteVal =redisTemplate.opsForValue().get("member-1");
//        System.out.println(JSONObject.toJSONString(byteVal));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("member-" + i);
        }
        List<Object> result = redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                for (int i = 0; i < list.size(); i++) {
                    redisConnection.get(list.get(i).getBytes());
                }
                return null;
            }
        });
        List<Member>  members = JSONObject.parseArray(JSONObject.toJSONString(result), Member.class);
        System.out.println(members.toString());
    }

    @Test
    public void templateObjectTest() {
        Member member = new Member();
        member.setAge(18);
        member.setBirthday(new Date());
        member.setName("小明");
        member.setSalary(20000.00);
//        redisTemplate.opsForValue().set("member-A", member);
        System.err.println("【查询内容】:" + redisTemplate.opsForValue().get("member-A"));
    }

    @Test
    public void templateTest() {
        redisTemplate.opsForHash().put("member", "name", "小王");
        Map<String, String> map = new HashMap<>();
        map.put("age", "18");
        map.put("birthday", "2002-04-05");
        redisTemplate.opsForHash().putAll("member", map);
        System.out.println(redisTemplate.opsForHash().get("member", "name"));
    }

    @Test
    public void testRedis() {
        System.err.println(this.factory);
        RedisConnection connection = this.factory.getConnection();
        connection.flushDb();
    }
}
