package com.example.my2small.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import com.example.my2small.domain.Users;
import com.example.my2small.service.UserService;
import com.example.my2small.utilTools.RegisterConfim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
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

public class UserController {
    @Autowired
    UserService userService;

    /**
    * @Author: DongHai
    * @Date: 2020/10/30
    * @Param: [user]
    * @Return: org.springframework.web.servlet.ModelAndView
     * TODO : 重定向后url的问题,失败后的返回问题
    **/
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public ModelAndView saveUser(Users user,HttpServletRequest request){
        if (RegisterConfim.registerConfim(request,user.getUsername(),user.getEmail(),user.getPhoneNum(),user.getPassword())) {
            user.setId(IdUtil.fastSimpleUUID());
            user.setCreateTime(DateUtil.date().toSqlDate());
        }else {
            return new ModelAndView("");
        }
        userService.saveUser(user);
        return new ModelAndView("index");
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
