package com.example.my2small;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.my2small.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void parseJson(){
        String s="{\"phoneNum\":\"18282067926\",\"password\":\"1234\"}";
        Map<String,String> map = JSONObject.parseObject(s, Map.class);
        System.out.println(map.get("password"));
    }
}
