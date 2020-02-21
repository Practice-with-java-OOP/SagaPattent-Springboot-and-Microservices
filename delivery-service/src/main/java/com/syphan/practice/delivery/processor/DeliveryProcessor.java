package com.syphan.practice.delivery.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface DeliveryProcessor {
    String INPUT = "delivery-service";
    String OUTPUT = "coordinator-service";

    @Input(DeliveryProcessor.INPUT)
    SubscribableChannel input();

    @Output(DeliveryProcessor.OUTPUT)
    MessageChannel output();
}
