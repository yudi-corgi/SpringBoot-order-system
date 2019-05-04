package com.person.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 返回给前端的商品类目数据
 */

@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = -788146092916104498L;
    // 该注解是将属性名称 序列化 注解中的名称
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
