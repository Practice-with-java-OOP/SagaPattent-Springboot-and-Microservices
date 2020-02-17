package com.syphan.practice.kitchen.messaging;

import com.syphan.practice.kitchen.processor.DeliveryProcessor;
import com.syphan.practice.kitchen.processor.OrderProcessor;
import com.syphan.pratice.common.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KitchenServicePublisher {

    private final static String TOPIC_ORDER_CALLBACK = "orderservicecallback";

    private final static String TOPIC_DELIVERY = "deliveryservice";

    @Autowired
    private OrderProcessor orderProcessor;

    @Autowired
    private DeliveryProcessor deliveryProcessor;

    public void sendToOrderCallback(OrderDTO orderDTO) {
        orderProcessor.output().send(MessageBuilder.withPayload(orderDTO)
                .setHeader("type", TOPIC_ORDER_CALLBACK)
                .build());
    }

    public void sendToDelivery(OrderDTO orderDTO) {
        deliveryProcessor.output().send(MessageBuilder.withPayload(orderDTO)
                .setHeader("type", TOPIC_DELIVERY)
                .build());
    }
}
