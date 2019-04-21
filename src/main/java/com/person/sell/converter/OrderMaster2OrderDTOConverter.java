package com.person.sell.converter;

import com.person.sell.dataobject.OrderMaster;
import com.person.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 类型转换：OrderMaster -》 OrderDTOConverter
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO converter(OrderMaster orderMaster){

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> converter(List<OrderMaster> orderMasterList){

        return orderMasterList.stream().map(e -> converter(e)).collect(Collectors.toList());
    }
}
