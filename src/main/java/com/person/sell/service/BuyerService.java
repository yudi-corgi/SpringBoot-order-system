package com.person.sell.service;

import com.person.sell.dto.OrderDTO;

/**
 * 买家业务逻辑
 */
public interface BuyerService {

    OrderDTO findOrderOne(String openid,String orderId);

    OrderDTO cancelOrder(String openid,String orderId);

}
