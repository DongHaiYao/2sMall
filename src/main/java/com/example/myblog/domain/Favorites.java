package com.example.myblog.domain;

import lombok.Data;

import java.sql.Date;

/**
 * @Author：DongHai
 * @Date：2020/10/21
 * @Description: 用户收藏夹
 * @Param : display 收藏状态
 **/
@Data
public class Favorites {
    private int id;
    private Date modify;
    private int sid;
    private int display;
    private String uid;
}
