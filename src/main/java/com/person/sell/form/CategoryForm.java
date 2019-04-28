package com.person.sell.form;

import lombok.Data;

/**
 * 卖家端修改或添加类目传递的信息对象
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;

}
