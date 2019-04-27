package com.person.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.person.sell.dataobject.OrderDetail;
import com.person.sell.enums.OrderStatusEnum;
import com.person.sell.enums.PayStatusEnum;
import com.person.sell.utils.EnumUtil;
import com.person.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;
import org.aspectj.weaver.ast.Or;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
//通过该注解 让为空的属性 不传递给前端
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    /** 买家微信 openId */
    private String buyerOpenid;

    /** 订单总金额数 */
    private BigDecimal orderAmount;

    /** 订单状态  0: 默认新下单 */
    private Integer orderStatus;

    /** 支付状态，默认未支付：0 */
    private Integer payStatus;

    // java对象 序列化为json输出
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

    /*
        通过状态 获取状态信息(enum )
        添加注解 @JsonIgnore 是因为该对象若转为JSON格式传递给前端，
        以下两个方法会变成属性字段(name是方法名)，用注解忽略掉这两个方法
     */
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
}
