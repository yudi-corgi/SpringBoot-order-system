package com.person.sell.service.impl;

import com.person.sell.dataobject.OrderDetail;
import com.person.sell.dataobject.OrderMaster;
import com.person.sell.dataobject.ProductInfo;
import com.person.sell.dto.CartDTO;
import com.person.sell.dto.OrderDTO;
import com.person.sell.enums.OrderStatusEnum;
import com.person.sell.enums.PayStatusEnum;
import com.person.sell.enums.ResultEnum;
import com.person.sell.exception.SellException;
import com.person.sell.repository.OrderDetailRepository;
import com.person.sell.repository.OrderMaterRepository;
import com.person.sell.service.OrderService;
import com.person.sell.service.ProductInfoService;
import com.person.sell.utils.KeyUtil;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMaterRepository orderMaterRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();

        //声明订单总价变量
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        //List<CartDTO> cartDTOS = new ArrayList<>();

        //1. 查询商品（数量，价格）
        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2. 计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //3.1 订单详情入库
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetailRepository.save(orderDetail);


            //将订单中商品数量添加到集合，以便于减库存
            //CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
            //cartDTOS.add(cartDTO);
        }

        //3. 写入订单数据（orderMaster）
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaterRepository.save(orderMaster);

        //4. 减库存
        List<CartDTO> cartDTOList = new ArrayList<>();
        orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);


        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
