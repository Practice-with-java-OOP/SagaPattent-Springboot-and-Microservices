package com.syphan.practice.order.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface OrderProcessor {
    String INPUT = "order-service";

    @Input(OrderProcessor.INPUT)
    SubscribableChannel input();
}
