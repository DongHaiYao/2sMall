package com.example.my2small.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
/**
 * @Author：DongHai
 * @Date：2020/10/22
 * @Description:配置类
 **/
@Configuration
@EnableConfigurationProperties(JdbcProperties.class)
public class JdbcConfig {
    @Bean
    public DataSource dataSource(JdbcProperties jdbc){
        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setUrl(jdbc.getUrl());
        druidDataSource.setDriverClassName(jdbc.getDriverClassName());
        druidDataSource.setUsername(jdbc.getUsername());
        druidDataSource.setPassword(jdbc.getPassword());
        return druidDataSource;
    }
}
