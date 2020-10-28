package com.example.my2small.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.example.my2small.domain.Users;
import com.example.my2small.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@Controller
@RequestMapping("/userService")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/register")
    public String  registerCheck(String jsonStr, RedirectAttributes attr, HttpServletResponse response) throws IOException {
        Users user = JSONUtil.toBean(jsonStr,Users.class);
        if (null == user) {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(JSONUtil.toJsonStr("错误"));
            return null;
        }

        String username = user.getUsername();
        String email = user.getEmail();
        String phoneNum = user.getPhoneNum();
        String password = user.getPassword();
        String passwordCheck = (String) JSONUtil.parseArray(jsonStr).getByPath("passwordCheck");
        String regexForUsername = "^[\\u4e00-\\u9fa5]{1,7}$|^[\\dA-Za-z_]{1,14}$";
        Matcher matcher = Pattern.compile(regexForUsername).matcher(username);
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

    @RequestMapping("/login")
    public String loginByEmail(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               Model model) throws IOException {
        String id=userService.loginByEmail(email, password);
        if(null==id){
            model.addAttribute("msg","账号或密码错误");
            return "login";
        }else {
            return "redirect:/";
        }
    }
}
