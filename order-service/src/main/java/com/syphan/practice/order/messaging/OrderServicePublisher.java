package com.syphan.practice.order.messaging;

import com.syphan.practice.order.processor.KitchenProcessor;
import com.syphan.pratice.common.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderServicePublisher {

    private final static String TOPIC = "orderservice";

    @Autowired
    private KitchenProcessor kitchenProcessor;

    public void sendOrder(OrderDTO orderDTO) {
        kitchenProcessor.output().send(MessageBuilder.withPayload(orderDTO)
                .setHeader("type", TOPIC)
                .build());
    }
}
