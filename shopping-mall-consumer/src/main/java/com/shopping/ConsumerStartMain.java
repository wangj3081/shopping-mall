package com.shopping;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 客户端服务
 * @author wangjian
 * @version 1.0
 * @see ConsumerStartMain
 * @since JDK1.8
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.shopping")
@EnableSwagger2Doc
@EnableCircuitBreaker
public class ConsumerStartMain {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerStartMain.class, args);
    }
}
