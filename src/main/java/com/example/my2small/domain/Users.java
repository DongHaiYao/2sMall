package com.example.my2small.domain;

import lombok.Data;

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
public class Users {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getModify() {
        return modify;
    }

    public void setModify(Date modify) {
        this.modify = modify;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public long getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(long stuNumber) {
        this.stuNumber = stuNumber;
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public Favorites getFavorites() {
        return favorites;
    }

    public void setFavorites(Favorites favorites) {
        this.favorites = favorites;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", modify=" + modify +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", realName='" + realName + '\'' +
                ", stuNumber=" + stuNumber +
                ", dormitory='" + dormitory + '\'' +
                ", gender='" + gender + '\'' +
                ", createTime=" + createTime +
                ", avatar=" + Arrays.toString(avatar) +
                ", favorites=" + favorites +
                ", shoppingCart=" + shoppingCart +
                '}';
    }
}
