package com.syphan.practice.transaction.coordinator.consumer;

import com.syphan.practice.transaction.coordinator.messaging.CoordinatorPublisher;
import com.syphan.practice.transaction.coordinator.processor.CoordinatorProcessor;
import com.syphan.pratice.common.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@EnableBinding({CoordinatorProcessor.class})
@Slf4j
public class CoordinatorConsumer {

    @Autowired
    private CoordinatorPublisher coordinatorPublisher;

    @StreamListener(target = CoordinatorProcessor.INPUT,
            condition = "headers['type'] == 'orders.callback.publisher'")
    public void orderCallbackEvent(@Payload OrderDTO orderDTO) {
        log.info("receive message " + orderDTO.toString());
        coordinatorPublisher.orderCallbackEvent(orderDTO);
    }

    @StreamListener(target = CoordinatorProcessor.INPUT,
            condition = "headers['type'] == 'order.create.publisher'")
    public void orderCreateEvent(@Payload OrderDTO orderDTO) {
        log.info("receive message " + orderDTO.toString());
        coordinatorPublisher.orderCreateEvent(orderDTO);
    }

    @StreamListener(target = CoordinatorProcessor.INPUT,
            condition = "headers['type'] == 'kitchen.validated.publisher'")
    public void sendEventToDelivery(@Payload OrderDTO orderDTO) {
        log.info("receive message " + orderDTO.toString());
        coordinatorPublisher.sendEventToDelivery(orderDTO);
    }
}
