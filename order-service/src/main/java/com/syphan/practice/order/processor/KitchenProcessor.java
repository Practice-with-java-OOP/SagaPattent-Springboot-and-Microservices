package com.syphan.practice.order.processor;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface KitchenProcessor {
//    String INPUT = "order-service";
    String OUTPUT = "kitchen-service";

//    @Input(KitchenProcessor.INPUT)
//    SubscribableChannel input();

    @Output(KitchenProcessor.OUTPUT)
    MessageChannel output();
}
