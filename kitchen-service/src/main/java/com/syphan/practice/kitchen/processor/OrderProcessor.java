package com.syphan.practice.kitchen.processor;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OrderProcessor {
//    String INPUT = "kitchen-service";
    String OUTPUT = "order-service";

//    @Input(OrderProcessor.INPUT)
//    SubscribableChannel input();

    @Output(OrderProcessor.OUTPUT)
    MessageChannel output();
}
