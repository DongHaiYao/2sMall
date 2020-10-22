package com.example.my2small;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 93037
 */
@SpringBootApplication
@MapperScan("com.example.my2small.dao")
public class MyBlogApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(MyBlogApplication.class, args);
    }

}
