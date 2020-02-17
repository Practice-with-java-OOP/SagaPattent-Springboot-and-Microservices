package com.syphan.practice.kitchen.processor;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface DeliveryProcessor {
//    String INPUT = "kitchen-service";
    String OUTPUT = "delivery-service";

//    @Input(DeliveryProcessor.INPUT)
//    SubscribableChannel input();

    @Output(DeliveryProcessor.OUTPUT)
    MessageChannel output();
}
