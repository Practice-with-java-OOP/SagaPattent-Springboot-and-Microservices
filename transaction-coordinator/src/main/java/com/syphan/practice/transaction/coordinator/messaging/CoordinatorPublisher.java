package com.syphan.practice.transaction.coordinator.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CoordinatorPublisher {

    private final static String COORDINATOR_ORDER_CREATE_TOPIC = "coordinator.order.create.publisher";
    private final static String COORDINATOR_ORDER_CALLBACK_TOPIC = "coordinator.order.callback.publisher";
    private final static String COORDINATOR_KITCHEN_VALIDATE = "coordinator.kitchen.validated.publisher";

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void orderCreateEvent(String orderDTO) {
        kafkaTemplate.send(COORDINATOR_ORDER_CREATE_TOPIC, orderDTO);
    }

    public void orderCallbackEvent(String orderDTO) {
        kafkaTemplate.send(COORDINATOR_ORDER_CALLBACK_TOPIC, orderDTO);
    }

    public void sendEventToDelivery(String orderDTO) {
        kafkaTemplate.send(COORDINATOR_KITCHEN_VALIDATE, orderDTO);
    }
}
