package com.syphan.practice.order.messaging;

import com.syphan.practice.order.processor.OrderProcessor;
import com.syphan.pratice.common.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderServicePublisher {

    private final static String COORDINATOR_ORDER_CREATE_TOPIC = "order.create.publisher";

    @Autowired
    private OrderProcessor orderProcessor;

    public void orderCreateEvent(OrderDTO orderDTO) {
        orderProcessor.output().send(MessageBuilder.withPayload(orderDTO)
                .setHeader("type", COORDINATOR_ORDER_CREATE_TOPIC)
                .build());
    }
}
