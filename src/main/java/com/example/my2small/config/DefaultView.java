package com.example.my2small.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author：DongHai
 * @Date：2020/10/25
 * @Description: 默认页配置类
 **/
@Configuration
public class DefaultView implements WebMvcConfigurer {
    /**
    * @Author: DongHai
    * @Date: 2020/10/26
    * @Param: [registry]
    * @Return: void
     * 配置静态资源映射
    **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/signup.html").setViewName("signup");
        registry.addViewController("/login.html").setViewName("login");
    }

}
