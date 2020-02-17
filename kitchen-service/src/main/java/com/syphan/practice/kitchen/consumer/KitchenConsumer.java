package com.syphan.practice.kitchen.consumer;


import com.syphan.practice.kitchen.messaging.KitchenServicePublisher;
import com.syphan.practice.kitchen.processor.DeliveryProcessor;
import com.syphan.practice.kitchen.processor.KitchenProcessor;
import com.syphan.practice.kitchen.processor.OrderProcessor;
import com.syphan.practice.kitchen.service.KitchenService;
import com.syphan.pratice.common.dto.OrderDTO;
import com.syphan.pratice.common.dto.type.OrderStatusType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@EnableBinding({KitchenProcessor.class, OrderProcessor.class, DeliveryProcessor.class})
@Slf4j
public class KitchenConsumer {

    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private KitchenServicePublisher kitchenPublisher;

    @StreamListener(target = KitchenProcessor.INPUT,
            condition = "headers['type'] == 'orderservice'")
    public void consumeMessage(@Payload OrderDTO orderDTO) {
        boolean started = kitchenService.process(orderDTO);
        kitchenPublisher.sendToOrderCallback(orderDTO);

        if (started) {
            log.info("Start cooking for order id " + orderDTO.getId() + " start");
            log.info("Packaging start");
            orderDTO.setOrderStatus(OrderStatusType.PACKAGING.getValue());
            orderDTO.setStatusDescription("Order in packaging");

            kitchenPublisher.sendToOrderCallback(orderDTO);
            log.info("Callback to order service sent");
            kitchenPublisher.sendToDelivery(orderDTO);
            log.info("Order id " + orderDTO.getId() + " sent to delivery");
        }
    }
}
