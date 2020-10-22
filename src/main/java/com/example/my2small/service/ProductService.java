package com.example.my2small.service;

import com.example.my2small.dao.IProductDao;
import com.example.my2small.domain.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author：DongHai
 * @Date：2020/10/22
 * @Description:
 **/
@Service
public class ProductService {
    @Autowired
    private IProductDao productDao;

    public Products findById(int id){
        return productDao.findById(id);
    }
}
