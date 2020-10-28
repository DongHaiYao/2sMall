package com.example.my2small.dao;

import com.example.my2small.domain.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author：DongHai
 * @Date：2020/10/22
 * @Description: 用户持久层
 **/
@Repository
public interface IUserDao {
    /***
    * @Author: DongHai
    * @Date: 2020/10/25
    * @Param: [id] 用户唯一标识符
    * @Return: com.example.my2small.domain.Users
     * 根据id查询用户
    **/
    @Select("select * from users where id=#{id}")
    Users findById(String id);

    /**
    *
     * @Author LingXiao
     * @Description //查询手机号是否已注册，每个手机号只能注册一个账号
     * @Date 20:44 2020/10/29 0029
     * @Param [phoneNum]
     * @return com.example.my2small.domain.Users
     **/
    @Select("select * from users where phoneNum=#{phoneNum}")
    Users findByPhoneNum(String phoneNum);
    /**
    * @Author: DongHai
    * @Date: 2020/10/26
    * @Param: [id] 用户唯一标识符
    * @Return: java.lang.String
     * 保存用户
    **/
    @Insert("insert into users (id,username,email,phoneNum,realName,dormitory,stuNumber,password,gender) value(#{id}," +
            "#{username},#{email},#{phoneNum},#{realName},#{dormitory},#{stuNumber},#{password},#{gender})")
    String saveUser(Users user);


}
