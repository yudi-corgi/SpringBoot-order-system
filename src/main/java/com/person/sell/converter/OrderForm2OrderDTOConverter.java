package com.person.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.person.sell.dataobject.OrderDetail;
import com.person.sell.dto.OrderDTO;
import com.person.sell.enums.ResultEnum;
import com.person.sell.exception.SellException;
import com.person.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 将前端订单数据类型转换为后端数据处理类型：OrderForm -> OrderDTO
 */

@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerPhone(orderForm.getPhone());

        Gson gson = new Gson();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                         new TypeToken<List<OrderDetail>>() {}.getType());
        }catch (Exception e){
            log.info("【对象转换】错误，string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;

    }
}
