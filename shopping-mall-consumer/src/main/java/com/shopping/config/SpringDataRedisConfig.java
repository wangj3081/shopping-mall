package com.shopping.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ClassName SpringDataRedisConfig
 * @Description lettuce 的SpringData配置
 * @Author wangjian
 * @Date 2020/4/5 1:46 下午
 * @Version 1.0
 **/
@Configuration
public class SpringDataRedisConfig {

   @Bean("standalone")
   @ConfigurationProperties(prefix ="spring.redis")
   public RedisStandaloneConfiguration getStandaloneConfig(@Value("${spring.redis.host}") String hostName,
                                                           @Value("${spring.redis.port}") int port) {
       return new RedisStandaloneConfiguration(hostName, port);
   }

    @Bean("redisConnectionFactory")
    public LettuceConnectionFactory getConnectionFactory(RedisStandaloneConfiguration standalone) {
        return new LettuceConnectionFactory(standalone);
    }


    /*
    *
     * @Author wangjian
     * @Description  redis操作模板；使用的value存储格式为JDK自带的对象的格式
     * @Date 5:06 下午 2020/4/5
     * @Param factory: 
     * @return org.springframework.data.redis.core.RedisTemplate
     **/
    @Bean("redisTemplate")
    public RedisTemplate getTemplate(RedisConnectionFactory factory) {
        RedisTemplate template = new RedisTemplate();
        template.setKeySerializer(new StringRedisSerializer()); // key 通过字符串存储
        template.setValueSerializer(new JdkSerializationRedisSerializer()); // 保存的value为对象信息
        template.setHashKeySerializer(new StringRedisSerializer()); // key通过字符串存储
        template.setHashValueSerializer(new JdkSerializationRedisSerializer()); // 保存的value为对象信息
        template.setConnectionFactory(factory); // 设置引用该格式内容的连接工厂
        return template;
    }

    /*
    *
     * @Author wangjian
     * @Description  redis存储json内容的操作模板;使用后可观察redis数据库中存储的数据格式是否为 JSON 格式
     * @Date 5:05 下午 2020/4/5
     * @Param factory: 
     * @return org.springframework.data.redis.core.RedisTemplate
     **/
    @Bean("redisJsonTemplate")
    public RedisTemplate getJsonTemplate(RedisConnectionFactory factory) {
        RedisTemplate template = new RedisTemplate();
        template.setKeySerializer(new StringRedisSerializer()); // key 通过字符串存储
        template.setValueSerializer(new Jackson2JsonRedisSerializer(Object.class)); // 保存的value为JSON序列化对象信息
        template.setHashKeySerializer(new StringRedisSerializer()); // key通过字符串存储
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer(Object.class)); // 保存的value为JSON序列化对象信息
        template.setConnectionFactory(factory); // 设置引用该格式内容的连接工厂
        return template;
    }

}
