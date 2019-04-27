package com.person.sell.controller;

import com.person.sell.dto.OrderDTO;
import com.person.sell.enums.ResultEnum;
import com.person.sell.exception.SellException;
import com.person.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.nio.ch.SelChImpl;

@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public void create(@RequestParam("orderId") String orderId,
                        @RequestParam("returnUrl") String returnUrl){

        //1. 查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //2. 发起支付
        //TODO
    }
}
