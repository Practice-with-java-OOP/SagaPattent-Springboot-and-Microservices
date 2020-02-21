package com.syphan.practice.transaction.coordinator.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface CoordinatorProcessor {

    String INPUT = "coordinator-service";
    String ORDER_OUTPUT = "order-service";
    String KITCHEN_OUTPUT = "kitchen-service";
    String DELIVERY_OUTPUT = "delivery-service";

    @Input(CoordinatorProcessor.INPUT)
    SubscribableChannel input();

    @Output(CoordinatorProcessor.ORDER_OUTPUT)
    MessageChannel orderOutput();

    @Output(CoordinatorProcessor.KITCHEN_OUTPUT)
    MessageChannel kitchenOutput();

    @Output(CoordinatorProcessor.DELIVERY_OUTPUT)
    MessageChannel deliveryOutput();

}
