package com.example.my2small;

import com.example.my2small.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
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
}
