package com.example.my2small.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.example.my2small.domain.Users;
import com.example.my2small.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @Author：DongHai
 * @Date：2020/10/22
 * @Description: 用户控制层
 **/
@RestController
@RequestMapping("/userService")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public String  registerCheck(@RequestBody Users user, @RequestParam("passwordCheck")String passwordCheck ,
                               RedirectAttributes attr, HttpServletResponse response) throws IOException {

        if (null == user) {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(JSONUtil.toJsonStr("错误"));
            return null;
        }

        String username = user.getUsername();
        String email = user.getEmail();
        String phoneNum = user.getPhoneNum();
        String password = user.getPassword();

        String regexForUsername = "^[\\u4e00-\\u9fa5]{1,7}$|^[\\dA-Za-z_]{1,14}$";
        Pattern pattern = Pattern.compile(regexForUsername);
        Matcher matcher = pattern.matcher(username);
        boolean isUsername = matcher.find();
        boolean isEmail = Validator.isEmail(email);
        boolean isPhoneNum = Validator.isNumber(phoneNum);
        boolean isCompared = password.equals(passwordCheck);
        if (isCompared&&isEmail&&isPhoneNum&&isUsername){
            attr.addAttribute("user",user);
            return "redirect:/user";
        }else {
            //TODO
            return "failed";
        }
    }
    @PostMapping("/user")
    public String saveUser(@ModelAttribute("user") Users user){
        user.setId(IdUtil.randomUUID());
        user.setCreateTime(DateUtil.date().toSqlDate());
        userService.saveUser(user);
        return "index.html";
    }
}
