package com.syphan.practice.order.service.impl;

import com.syphan.practice.order.dto.OrderCreateDto;
import com.syphan.practice.order.messaging.OrderServicePublisher;
import com.syphan.practice.order.model.Order;
import com.syphan.practice.order.model.OrderItem;
import com.syphan.practice.order.repository.OrderRepository;
import com.syphan.practice.order.service.OrderService;
import com.syphan.pratice.common.base.BaseServiceImpl;
import com.syphan.pratice.common.dto.HamburgerDTO;
import com.syphan.pratice.common.dto.OrderDTO;
import com.syphan.pratice.common.dto.type.OrderStatusType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl extends BaseServiceImpl<Order, Long> implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        super(orderRepository);
        this.orderRepository = orderRepository;
    }

    @Autowired
    private OrderServicePublisher orderServicePublisher;

    @Transactional
    @Override
    public Order createOrder(OrderCreateDto dto) {
        Order order = new Order();
        order.setNumber(dto.getNumber());
        order.setStreet(dto.getStreet());
        order.setOrderStatus(OrderStatusType.WAITING.getValue());
        order.setStatusDescription(OrderStatusType.WAITING.name());

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<HamburgerDTO> hamburgers = dto.getHamburgers();
        for (HamburgerDTO hamburger : hamburgers) {
            orderItems.add(OrderItem.builder()
                    .hamburgerType(hamburger.getHamburgerType())
                    .quantity(hamburger.getQuantity())
                    .order(order)
                    .build());
            totalPrice = totalPrice.add(hamburger.getPrice().multiply(BigDecimal.valueOf(hamburger.getQuantity())));
        }

        order.setTotalPrice(totalPrice);
        order.getOrderItems().addAll(orderItems);
        order = orderRepository.save(order);
//        sendOrderPublisher(order, hamburgers);
        return order;
    }

    //send message to queue
    private void sendOrderPublisher(Order order, List<HamburgerDTO> hamburgers) {
        OrderDTO orderDTO = OrderDTO.builder()
                .id(order.getId())
                .statusDescription(order.getStatusDescription())
                .cookingType(order.getCookingType())
                .orderStatus(order.getOrderStatus())
                .hamburgers(hamburgers)
                .number(order.getNumber())
                .street(order.getStreet())
                .totalPrice(order.getTotalPrice())
                .build();

        orderServicePublisher.sendOrder(orderDTO);
        log.info("Order with id " + order.getId() + " sent to kitchen service");
    }

    @Override
    public void callback(OrderDTO orderDTO) {
        Optional<Order> orderOptional = orderRepository.findById(orderDTO.getId());
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setStatusDescription(orderDTO.getStatusDescription());
            order.setOrderStatus(orderDTO.getOrderStatus());
            orderRepository.save(order);
        }
    }
}
