package com.person.sell.service;

import com.person.sell.dto.OrderDTO;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * 微信推送模板消息给用户
 */
public interface PushMessageService {

    void orderStatus(OrderDTO orderDTO);

}
