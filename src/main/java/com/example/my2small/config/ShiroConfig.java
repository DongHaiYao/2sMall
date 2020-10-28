package com.example.my2small.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.example.my2small.domain.UserRealm;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: LingXiao
 * @Date: 2020/10/29 0029
 * @Description: Shiro配置类
 **/
@Configuration
public class ShiroConfig {
    /**
    * @Author LingXiao
    * @Description //这个方法使前端可以使用shiro的标签
    * @Date 16:21 2020/10/30 0030
    * @Param []
    * @return at.pollux.thymeleaf.shiro.dialect.ShiroDialect
    **/
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }


    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
        return defaultWebSecurityManager;
    }


    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        Map<String,String> filterMap=new LinkedHashMap<>();
        filterMap.put("/home.html","user");
        filterMap.put("/logout","logout");
        bean.setFilterChainDefinitionMap(filterMap);
        bean.setLoginUrl("/login.html");
        return bean;
    }
    /**
    *
     * @Author LingXiao
     * @Description //自动登录的cookie设置
     * @Date 22:35 2020/10/31 0031
     * @Param []
     * @return org.apache.shiro.web.servlet.SimpleCookie
     **/
    public SimpleCookie rememberCookie(){
        SimpleCookie cookie=new SimpleCookie("rememberMe");
        cookie.setMaxAge(60*60*24*7);
        return cookie;
    }
    /**
    *
     * @Author LingXiao
     * @Description //自动登录cookie的加密
     * @Date 22:36 2020/10/31 0031
     * @Param []
     * @return org.apache.shiro.web.mgt.CookieRememberMeManager
     **/
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager manager=new CookieRememberMeManager();
        manager.setCookie(rememberCookie());
        manager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return manager;
    }
}
