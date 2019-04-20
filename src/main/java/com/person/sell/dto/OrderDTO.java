package com.person.sell.dto;

import com.person.sell.dataobject.OrderDetail;
import com.person.sell.enums.OrderStatusEnum;
import com.person.sell.enums.PayStatusEnum;
import lombok.Data;
import org.aspectj.weaver.ast.Or;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
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

    private Date createTime;

    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
