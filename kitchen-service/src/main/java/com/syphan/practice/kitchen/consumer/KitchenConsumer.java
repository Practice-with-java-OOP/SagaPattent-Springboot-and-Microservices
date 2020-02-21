package com.syphan.practice.kitchen.consumer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.syphan.practice.kitchen.messaging.KitchenServicePublisher;
import com.syphan.practice.kitchen.service.KitchenService;
import com.syphan.pratice.common.dto.OrderDTO;
import com.syphan.pratice.common.dto.type.OrderStatusType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class KitchenConsumer {

    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KitchenServicePublisher kitchenServicePublisher;

    @KafkaListener(topics = "coordinator.order.create.publisher")
    public void consumeMessage(String record) {
        log.info("receive message " + record);
        try {
            OrderDTO orderDTO = objectMapper.readValue(record, OrderDTO.class);
            boolean started = kitchenService.process(orderDTO);
            kitchenServicePublisher.orderCallbackEventPublisher(orderDTO);

            if (started) {
                log.info("Start cooking for order id " + orderDTO.getId() + " start");
                log.info("Packaging start");
                orderDTO.setOrderStatus(OrderStatusType.PACKAGING.getValue());
                orderDTO.setStatusDescription("Order in packaging");

                kitchenServicePublisher.orderCallbackEventPublisher(orderDTO);
                log.info("Callback to order service sent");
                kitchenServicePublisher.sendEventToDelivery(orderDTO);
                log.info("Order id " + orderDTO.getId() + " sent to delivery");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
