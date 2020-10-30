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
    * @Author: DongHai
    * @Date: 2020/10/26
    * @Param: [id] 用户唯一标识符
    * @Return:
     * 保存用户
    **/
    @Insert("insert into users (id,username,email,phoneNum,realName,dormitory,createTime,stuNumber,password,gender) value(#{id}," +
            "#{username},#{email},#{phoneNum},#{realName},#{dormitory},#{createTime},#{stuNumber},#{password},#{gender})")
    void saveUser(Users user);

    /**
     * @Author LingXiao
     * @Description //邮箱密码登录
     * @Date 21:16 2020/10/27 0027
     * @Param [email, password]
     * @return com.example.my2small.domain.Users
     **/
    @Select("select * from users where email=#{email} and password=#{password}")
    String loginByEmail(String email,String password);
}
