package com.example.my2small.utilTools;

import cn.hutool.core.lang.Validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author：DongHai
 * @Date：2020/10/30
 * @Description:
 **/
public class RegisterConfim {
    public static boolean registerConfim(HttpServletRequest request,String username, String email, String phoneNum,String password){
        String regexForUsername = "^[\\u4e00-\\u9fa5]{1,7}$|^[\\dA-Za-z_]{1,14}$";
        Matcher matcher = Pattern.compile(regexForUsername).matcher(username);
        boolean isUsername = matcher.find();
        boolean isEmail = Validator.isEmail(email);
        boolean isPhoneNum = Validator.isNumber(phoneNum);
        boolean isCompared = password.equals(request.getParameter("passwordCheck"));
        return isEmail&&isUsername&&isPhoneNum&&isCompared;
    }
}
