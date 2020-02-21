package com.syphan.practice.kitchen.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KitchenProcessor {
    String INPUT = "kitchen-service";
    String OUTPUT = "coordinator-service";

    @Input(KitchenProcessor.INPUT)
    SubscribableChannel input();

    @Output(KitchenProcessor.OUTPUT)
    MessageChannel output();
}
