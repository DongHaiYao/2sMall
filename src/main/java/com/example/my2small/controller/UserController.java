package com.example.my2small.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import com.example.my2small.domain.Avatar;
import com.example.my2small.domain.Users;
import com.example.my2small.service.UserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.example.my2small.utilTools.RegisterConfim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
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
    public String registerCheck(Model model,String jsonStr, RedirectAttributes attr, HttpServletResponse response) throws IOException {
        Users user = JSONUtil.toBean(jsonStr, Users.class);
        if (null == user) {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(JSONUtil.toJsonStr("错误"));
            return "signup";
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
        if(!isUsername){
            model.addAttribute("isUsername","用户名格式不正确,请确保没有特殊字符");
        }
        if(!isEmail){
            model.addAttribute("isEmail","邮箱格式不正确");
        }
        if(!isPhoneNum){
            model.addAttribute("isPhoneNum","请输入正确的手机号");
        }
        if(!isCompared){
            model.addAttribute("isCompared","请确保两次输入密码相同");
        }
        if (isCompared && isEmail && isPhoneNum && isUsername) {
            attr.addAttribute("user", user);
            return "redirect:/user";
        } else {
            //TODO
            return "signup";
        }
    }



    /**
    * @Author: DongHai
    * @Date: 2020/10/30
    * @Param: [user]
    * @Return: org.springframework.web.servlet.ModelAndView
     * TODO : 重定向后url的问题,失败后的返回问题
    **/
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String saveUser(Users user,HttpServletRequest request){
        if (RegisterConfim.registerConfim(request,user.getUsername(),user.getEmail(),user.getPhoneNum(),user.getPassword())) {
            user.setId(IdUtil.fastSimpleUUID());
            user.setCreateTime(DateUtil.date().toSqlDate());
            user.setModify(DateUtil.date().toSqlDate());
        }else {
            return "";
        }
        userService.saveUser(user);
        return "login";
    }

    @RequestMapping("/login")
    public String loginByPhoneNum(@RequestParam("phoneNum") String phoneNum,
                                  @RequestParam("password") String password,
                                   Boolean rememberMe,
                                  HttpServletRequest request,
                                  Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token;
        if(rememberMe!=null){
            token = new UsernamePasswordToken(phoneNum, password,rememberMe);
        }else {
            token = new UsernamePasswordToken(phoneNum, password);
        }
        HttpSession session=request.getSession();
        try {
            subject.login(token);
            Users user=userService.findByPhoneNum(phoneNum);
            session.setAttribute("avatarSrc","/userService/getAvatar?userId="+user.getId());
            return "redirect:/";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "该手机号暂未注册");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }
    @RequestMapping("/getAvatar")
    public void getAvatar(@RequestParam("userId") String userId,HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        response.setCharacterEncoding("UTF-8");
        Avatar avatar=userService.getAvatar(userId);
        OutputStream outputStream = response.getOutputStream();
        InputStream inputStream=new ByteArrayInputStream(avatar.getAvatar());
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = inputStream.read(buf, 0, 1024)) != -1) {
            outputStream.write(buf, 0, len);
        }
        outputStream.flush();
        outputStream.close();
    }
}
