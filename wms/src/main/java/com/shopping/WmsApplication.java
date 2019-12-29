package com.shopping;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 仓储服务
 * @author wangjian
 * @version 1.0
 * @see WmsApplication
 * @since JDK1.8
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.shopping")
@EnableDiscoveryClient // consul 连接客户端
@EnableSwagger2Doc
public class WmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmsApplication.class, args);
    }
}
