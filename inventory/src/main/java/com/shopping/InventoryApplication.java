package com.shopping;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 库存服务
 * @author wangjian
 * @version 1.0
 * @see InventoryApplication
 * @since JDK1.8
 */
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.shopping")
@EnableSwagger2Doc
@MapperScan("com.shopping.inventory.dao")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class InventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }
}
