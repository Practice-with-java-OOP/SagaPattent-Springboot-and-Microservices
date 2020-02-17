package com.syphan.practice.kitchen.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface KitchenProcessor {
    String INPUT = "kitchen-service";

    @Input(KitchenProcessor.INPUT)
    SubscribableChannel input();
}
