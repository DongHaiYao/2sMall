package com.example.my2small.domain;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;

/**
 * @Author：DongHai
 * @Date：2020/10/21
 * @Description:用户实体类
 * @Param :modify  修改时间
 *         dormitory  宿舍号
 *         createTime   创建时间
 *         avatar 头像
 **/
@Data
public class Users implements Serializable {
    private String  id;
    private Date modify;
    private String username;
    private String password;
    private String  phoneNum;
    private String email;
    private String realName;
    private long stuNumber;
    private String dormitory;
    private String gender;
    private Date createTime;
    private byte[] avatar;
    private Favorites favorites;
    private ShoppingCart shoppingCart;

}
