package com.shopping;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;
import com.shopping.integral.config.SpringContextUtil;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 积分服务
 * @author wangjian
 * @version 1.0
 * @see Application
 * @since JDK1.8
 */
@MapperScan(basePackages = {"com.shopping.integral.dao"})
@EnableSwagger2Doc
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        ApplicationContext context = SpringContextUtil.getApplicationContext();
//        boolean druidDataSource = context.containsBean("shardingDataSource");
//        String[] names = context.getBeanDefinitionNames();
//        System.err.println("*************************************:" );
//        for (String name : names) {
//            System.err.println(name);
//        }
//        ShardingDataSource dataSource = (ShardingDataSource) SpringContextUtil.getBean("shardingDataSource");
//        Map<String, DataSource> dataSourceMap = dataSource.getDataSourceMap();
//        dataSourceMap.forEach((key,val) -> {
//            System.err.println(JSONObject.toJSONString(key) + ":" + val);
//        });
    }
}
