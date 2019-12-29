package com.shopping;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;
import com.shopping.inventory.config.SpringContextUtil;
import io.seata.config.springcloud.SpringContextProvider;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;

/**
 * 库存服务
 * @author wangjian
 * @version 1.0
 * @see InventoryApplication
 * @since JDK1.8
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.shopping.inventory.dao")
public class InventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }
}
