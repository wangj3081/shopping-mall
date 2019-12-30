package com.shopping.wms.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import io.seata.rm.datasource.DataSourceProxy;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 *
 * @author wangjian
 * @version 1.0
 * @see DataSourceConfig
 * @since JDK1.8
 */
@Configuration
public class DataSourceConfig {
//
    @Bean(value = "druidDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDruidDataSource(){
        return new DruidDataSource();
    }


    @Primary
    @Bean(value = "dataSource")
    public DataSource dataSource(@Qualifier("druidDataSource") DataSource druidDataSource) {
        return new DataSourceProxy(druidDataSource);
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier(value = "dataSource") DataSource dataSource) throws IOException {
        // 这里用 MybatisSqlSessionFactoryBean 代替了 SqlSessionFactoryBean，否则 MyBatisPlus 不会生效
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        mybatisSqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:/mapper/*.xml"));
        mybatisSqlSessionFactoryBean.setTransactionFactory(new SpringManagedTransactionFactory());
        return mybatisSqlSessionFactoryBean;
    }


}
