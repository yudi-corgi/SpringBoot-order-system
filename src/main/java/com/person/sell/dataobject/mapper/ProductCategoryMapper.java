package com.person.sell.dataobject.mapper;

import com.person.sell.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Mybatis与Springboot整合，mapper 测试类
 */
public interface ProductCategoryMapper {

    /*
        用注解方式实现，编写原生SQL语句
     */
    //插入数据
    //插入多少条记录，返回数值就是多少，此处多个参数用map传递需要设置key与参数名对应
    @Insert("insert into product_category(category_name,category_type) values" +
            "(#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    int insertByMap(Map<String,Object> map);

    //通过对象注入参数,SQL语句的参数名要对应对象属性名称
    @Insert("insert into product_category(category_name,category_type) values" +
            "(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);


    //查询数据
    //查询后返回值要先设置好-》@Results：property 对应返回对象的属性
    @Select("select * from product_category where category_type = #{categoryType}")
    @Results({
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType")
    })
    ProductCategory findByCategoryType(Integer categoryType);

    //查询后结果是多条，则返回值应设置为集合
    @Select("select * from product_category where category_name = #{categoryName}")
    @Results({
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType")
    })
    List<ProductCategory> findByCategoryName(String categoryName);


    //更新数据
    //更新多少数据，返回值就是多少 --- 传递多个参数需要用注解 @Param 指定对应 SQl 语句参数的名称
    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName,@Param("categoryType") Integer categoryType);

    //通过对象传递参数更新数据
    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByObject(ProductCategory productCategory);


    //删除数据
    @Delete("delete from product_category where category_type = #{categoryType}")
    int deleteByCategoryType(Integer categoryType);


    /*
        编写函数式接口，采用 xml 方式编写 mapper 及 SQL
     */
    ProductCategory selectByCategoryType(Integer categoryType);
}
