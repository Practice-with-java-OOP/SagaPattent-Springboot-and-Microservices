package com.syphan.practice.transaction.coordinator.consumer;

import com.syphan.practice.transaction.coordinator.messaging.CoordinatorPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CoordinatorConsumer {

    @Autowired
    private CoordinatorPublisher coordinatorPublisher;

    @KafkaListener(topics = "order.create.publisher")
    public void orderCreateEvent(String orderDTO) {
        log.info("receive message " + orderDTO);
        coordinatorPublisher.orderCreateEvent(orderDTO);
    }

    @KafkaListener(topics = "orders.callback.publisher")
    public void orderCallbackEvent(String orderDTO) {
        log.info("receive message " + orderDTO);
        coordinatorPublisher.orderCallbackEvent(orderDTO);
    }

    @KafkaListener(topics = "kitchen.validated.publisher")
    public void sendEventToDelivery(String orderDTO) {
        log.info("receive message " + orderDTO);
        coordinatorPublisher.sendEventToDelivery(orderDTO);
    }
}
