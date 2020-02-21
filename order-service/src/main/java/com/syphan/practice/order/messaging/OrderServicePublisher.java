package com.syphan.practice.order.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syphan.pratice.common.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServicePublisher {

    private final static String COORDINATOR_ORDER_CREATE_TOPIC = "order.create.publisher";

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void orderCreateEvent(OrderDTO orderDTO) {
        try {
            kafkaTemplate.send(COORDINATOR_ORDER_CREATE_TOPIC, objectMapper.writeValueAsString(orderDTO));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }
}
