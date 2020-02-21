package com.syphan.practice.kitchen.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syphan.pratice.common.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KitchenServicePublisher {

    private final static String COORDINATOR_ORDER_CALLBACK = "orders.callback.publisher";

    private final static String COORDINATOR_KITCHEN_VALIDATE = "kitchen.validated.publisher";

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void orderCallbackEventPublisher(OrderDTO orderDTO) {
        try {
            kafkaTemplate.send(COORDINATOR_ORDER_CALLBACK, objectMapper.writeValueAsString(orderDTO));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void sendEventToDelivery(OrderDTO orderDTO) {
        try {
            kafkaTemplate.send(COORDINATOR_KITCHEN_VALIDATE, objectMapper.writeValueAsString(orderDTO));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }
}
