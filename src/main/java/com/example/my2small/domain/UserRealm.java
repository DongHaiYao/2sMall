package com.example.my2small.domain;

import com.example.my2small.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.util.UUID;

/**
 * @Author: LingXiao
 * @Date: 2020/10/29 0029
 * @Description: 自定义的userRealm，完成授权和认证
 **/

public class UserRealm extends AuthorizingRealm  {
    @Autowired
    UserService userService;
    /**
     * @Author LingXiao
     * @Description //授权
     * @Date 20:30 2020/10/29 0029
     * @Param [principalCollection]
     * @return org.apache.shiro.authz.AuthorizationInfo
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    /**
    *
     * @Author LingXiao
     * @Description //认证
     * @Date 20:31 2020/10/29 0029
     * @Param [authenticationToken]
     * @return org.apache.shiro.authc.AuthenticationInfo
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        String phoneNum=token.getUsername();
        Users user=userService.findByPhoneNum(phoneNum);
        String realmName=getName();
        if(user==null){
            return null;
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),realmName);
    }
}
