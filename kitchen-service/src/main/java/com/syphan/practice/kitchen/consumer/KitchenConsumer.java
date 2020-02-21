package com.syphan.practice.kitchen.consumer;


import com.syphan.practice.kitchen.messaging.KitchenServicePublisher;
import com.syphan.practice.kitchen.processor.KitchenProcessor;
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
@EnableBinding({KitchenProcessor.class})
@Slf4j
public class KitchenConsumer {

    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private KitchenServicePublisher kitchenPublisher;

    @StreamListener(target = KitchenProcessor.INPUT,
            condition = "headers['type'] == 'coordinator.order.create.publisher'")
    public void consumeMessage(@Payload OrderDTO orderDTO) {
        log.info("receive message " + orderDTO.toString());
        boolean started = kitchenService.process(orderDTO);
        kitchenPublisher.orderCallbackEventPublisher(orderDTO);

        if (started) {
            log.info("Start cooking for order id " + orderDTO.getId() + " start");
            log.info("Packaging start");
            orderDTO.setOrderStatus(OrderStatusType.PACKAGING.getValue());
            orderDTO.setStatusDescription("Order in packaging");

            kitchenPublisher.orderCallbackEventPublisher(orderDTO);
            log.info("Callback to order service sent");
            kitchenPublisher.sendEventToDelivery(orderDTO);
            log.info("Order id " + orderDTO.getId() + " sent to delivery");
        }
    }
}
