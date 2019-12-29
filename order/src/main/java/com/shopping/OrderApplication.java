package com.shopping;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 订单服务
 * @author wangjian
 * @version 1.0
 * @see OrderApplication
 * @since JDK1.8
 */
@EnableDiscoveryClient // consul 连接客户端
@EnableFeignClients(basePackages = "com.shopping") // 扫描 feign 接口定义的包
@MapperScan(basePackages = "com.shopping.order.dao")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableSwagger2Doc
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
