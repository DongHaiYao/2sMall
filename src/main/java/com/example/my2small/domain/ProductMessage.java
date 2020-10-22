package com.example.my2small.domain;

import lombok.Data;

import java.sql.Date;

/**
 * @Author：DongHai
 * @Date：2020/10/21
 * @Description: 商品留言
 * @Param : modify 修改时间
 *          content 留言内容
 *          display 留言状态
 **/
@Data
public class ProductMessage {
    private int id;
    private Date modify;
    private int sid;
    private String content;
    private int display;
    private String uid;
}
