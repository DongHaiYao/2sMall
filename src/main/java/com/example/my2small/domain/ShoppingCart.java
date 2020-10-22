package com.example.my2small.domain;

import lombok.Data;

import java.sql.Date;

/**
 * @Author：DongHai
 * @Date：2020/10/21
 * @Description: 用户购物车
 * @Param : modify 修改时间
 *          display 商品是否被删除
 *          uid 用户id
 *          sid 商品id
 *          quantity 商品数量
 **/
@Data
public class ShoppingCart {
    private int id;
    private Date modify;
    private int display;
    private String uid;
    private int sid;
    private int quantity;
}
