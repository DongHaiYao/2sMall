package com.example.my2small.service;

import com.example.my2small.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author：DongHai
 * @Date：2020/10/22
 * @Description:
 **/
@Service
public class UserService {
    @Autowired
    private IUserDao userDao;

}
