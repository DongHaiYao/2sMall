package com.example.myblog.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @Author：DongHai
 * @Date：2020/10/21
 * @Description:商品实体类
 * @Param : level 商品成色
 *          modify 修改时间
 *          remake 商品详情
 *          sort 商品类别
 *          display 是否下架
 *          transaction 交易方式
 *          image 商品图片
 **/
@Data
public class Products {
    private int id;
    private Date modify;
    private String productName;
    private String level;
    private String remake;
    private BigDecimal price;
    private String sort;
    private int count;
    private int display;
    private int transaction;
    private int salesNum;
    private String  uid;
    private byte[] image;
}
