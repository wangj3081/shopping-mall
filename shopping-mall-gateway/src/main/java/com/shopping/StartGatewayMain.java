package com.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *
 * 网关服务
 * @author wangjian
 * @version 1.0
 * @see StartGatewayMain
 * @since JDK1.8
 */
@SpringBootApplication
@EnableDiscoveryClient // consul 客户端
public class StartGatewayMain {
    public static void main(String[] args) {
        SpringApplication.run(StartGatewayMain.class, args);
    }
}
