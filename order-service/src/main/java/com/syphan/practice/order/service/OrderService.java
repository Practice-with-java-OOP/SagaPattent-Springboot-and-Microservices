package com.syphan.practice.order.service;

import com.syphan.practice.order.dto.OrderCreateDto;
import com.syphan.practice.order.model.Order;
import com.syphan.pratice.common.base.BaseService;
import com.syphan.pratice.common.dto.OrderDTO;

public interface OrderService extends BaseService<Order, Long> {
    OrderDTO createOrder(OrderCreateDto orderDTO);

    void callback(OrderDTO orderDTO);
}
