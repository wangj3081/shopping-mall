package com.shopping;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 库存服务
 * @author wangjian
 * @version 1.0
 * @see InventoryApplication
 * @since JDK1.8
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.shopping.inventory.dao")
@EnableSwagger2Doc
public class InventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }
}
