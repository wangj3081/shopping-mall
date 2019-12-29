package com.shopping;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 积分服务
 * @author wangjian
 * @version 1.0
 * @see Application
 * @since JDK1.8
 */
@MapperScan(basePackages = {"com.shopping.integral.dao.*"})
@EnableSwagger2Doc
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
