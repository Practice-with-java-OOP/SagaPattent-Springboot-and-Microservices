package com.syphan.practice.order.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface OrderProcessor {

    String INPUT = "order-service";
    String OUTPUT = "coordinator-service";

    @Input(OrderProcessor.INPUT)
    SubscribableChannel input();

    @Output(OrderProcessor.OUTPUT)
    MessageChannel output();
}
