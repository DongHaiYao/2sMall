package com.example.my2small.service;

import com.example.my2small.dao.IUserDao;
import com.example.my2small.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

/**
 * @Author：DongHai
 * @Date：2020/10/22
 * @Description:
 **/
@Service("userService")
public class UserService {
    @Autowired
    private IUserDao userDao;

    public Users findById(String id){
        return userDao.findById(id);
    }

    public void saveUser(Users user){
         userDao.saveUser(user);
    }

    public String loginByEmail(String email,String password){
        return userDao.loginByEmail(email, password);
    }
}
