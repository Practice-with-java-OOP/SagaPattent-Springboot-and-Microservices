package com.syphan.practice.kitchen.messaging;

import com.syphan.practice.kitchen.processor.KitchenProcessor;
import com.syphan.pratice.common.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KitchenServicePublisher {

    private final static String COORDINATOR_ORDER_CALLBACK = "orders.callback.publisher";

    private final static String COORDINATOR_KITCHEN_VALIDATE = "kitchen.validated.publisher";

    @Autowired
    private KitchenProcessor kitchenProcessor;

    public void orderCallbackEventPublisher(OrderDTO orderDTO) {
        kitchenProcessor.output().send(MessageBuilder.withPayload(orderDTO)
                .setHeader("type", COORDINATOR_ORDER_CALLBACK)
                .build());
    }

    public void sendEventToDelivery(OrderDTO orderDTO) {
        kitchenProcessor.output().send(MessageBuilder.withPayload(orderDTO)
                .setHeader("type", COORDINATOR_KITCHEN_VALIDATE)
                .build());
    }
}
