package com.example.my2small;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.my2small.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class MyblogApplicationTests {
    @Autowired
    UserService userService;
    @Test
    void contextLoads() {
    }
    @Test
    void regexTest(){
        String regexForUsername = "^\\w+$";
        Pattern p = Pattern.compile(regexForUsername);
        Matcher matcher = p.matcher("username15_ww");
        System.out.println(matcher.find());
    }
    @Test
    void getAvatar(){
        //byte[] img=userService.getAvatar("fc7e5f5b-68a6-4e15-8d9a-7f4c4aba417c");

    }
}
