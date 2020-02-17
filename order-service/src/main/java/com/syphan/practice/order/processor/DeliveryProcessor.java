package com.syphan.practice.order.processor;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface DeliveryProcessor {
//    String INPUT = "order-service";
    String OUTPUT = "delivery-service";

//    @Input(DeliveryProcessor.INPUT)
//    SubscribableChannel input();

    @Output(DeliveryProcessor.OUTPUT)
    MessageChannel output();
}
