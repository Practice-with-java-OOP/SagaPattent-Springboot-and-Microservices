package com.syphan.practice.delivery.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface OrderProcessor {
    String INPUT = "delivery-service";
    String OUTPUT = "order-service";

    @Input(OrderProcessor.INPUT)
    SubscribableChannel input();

    @Output(OrderProcessor.OUTPUT)
    MessageChannel output();
}
