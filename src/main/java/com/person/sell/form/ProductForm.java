package com.person.sell.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 卖家端添加商品信息对象
 */
@Data
public class ProductForm {


    private String productId;

    private String productName;

    private BigDecimal productPrice;

    /** 库存 */
    private Integer productStock;

    private String productDescription;

    /** 商品小图 */
    private String productIcon;

    private Integer categoryType;

}
