package com.person.sell.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.person.sell.enums.ProductStatusEnum;
import com.person.sell.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class ProductInfo {

    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    /** 库存 */
    private Integer productStock;

    private String productDescription;

    /** 商品小图 */
    private String productIcon;

    /** 0：正常 1：下架 */
    private Integer productStatus;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }

}
