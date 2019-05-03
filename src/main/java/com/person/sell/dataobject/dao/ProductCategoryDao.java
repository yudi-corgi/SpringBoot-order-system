package com.person.sell.dataobject.dao;


import com.person.sell.dataobject.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Dao测试类，对mapper文件的调用处理后再在 service 使用
 * 也可以直接在 service 层调用 mapper，只是简单的逻辑分层处理
 */
public class ProductCategoryDao {

    @Autowired
    private ProductCategoryMapper mapper;

    public int insertByMap(Map<String,Object> map){
        return mapper.insertByMap(map);
    }

}
