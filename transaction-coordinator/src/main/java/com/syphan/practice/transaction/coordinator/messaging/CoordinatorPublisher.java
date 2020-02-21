package com.syphan.practice.transaction.coordinator.messaging;

import com.syphan.practice.transaction.coordinator.processor.CoordinatorProcessor;
import com.syphan.pratice.common.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class CoordinatorPublisher {

    private final static String COORDINATOR_ORDER_CREATE_TOPIC = "coordinator.order.create.publisher";
    private final static String COORDINATOR_ORDER_CALLBACK_TOPIC = "coordinator.order.callback.publisher";
    private final static String COORDINATOR_KITCHEN_VALIDATE = "coordinator.kitchen.validated.publisher";

    @Autowired
    private CoordinatorProcessor coordinatorProcessor;

    public void orderCreateEvent(OrderDTO orderDTO) {
        coordinatorProcessor.kitchenOutput().send(MessageBuilder.withPayload(orderDTO)
                .setHeader("type", COORDINATOR_ORDER_CREATE_TOPIC)
                .build());
    }

    public void orderCallbackEvent(OrderDTO orderDTO) {
        coordinatorProcessor.orderOutput().send(MessageBuilder.withPayload(orderDTO)
                .setHeader("type", COORDINATOR_ORDER_CALLBACK_TOPIC)
                .build());
    }

    public void sendEventToDelivery(OrderDTO orderDTO) {
        coordinatorProcessor.deliveryOutput().send(MessageBuilder.withPayload(orderDTO)
                .setHeader("type", COORDINATOR_KITCHEN_VALIDATE)
                .build());
    }
}
