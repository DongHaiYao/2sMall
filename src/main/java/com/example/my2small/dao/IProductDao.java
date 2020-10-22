package com.example.my2small.dao;

import com.example.my2small.domain.Products;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author：DongHai
 * @Date：2020/10/22
 * @Description:商品持久层
 **/
@Repository
public interface IProductDao {
    /**
    * @Author: DongHai
    * @Date: 2020/10/22
    * @Param: [id] 商品id
    * @Return: com.example.my2small.domain.Products
    **/
    @Select("select 待定 from products where id=#{id}")
    Products findById(int id);
}
