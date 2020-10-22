package com.example.my2small.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * @Author：DongHai
 * @Date：2020/10/22
 * @Description:配置类
 **/
@ConfigurationProperties(prefix = "jdbc")
@Data
public class JdbcProperties {
    private String url;
    private String driverClassName;
    private String username;
    private String password;
}
