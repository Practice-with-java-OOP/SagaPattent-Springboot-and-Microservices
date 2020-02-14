package com.syphan.practice.house.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface HouseProcessor {
    String INPUT = "house-service";
    String OUTPUT = "registration-service";

    @Input(HouseProcessor.INPUT)
    SubscribableChannel input();

    @Output(HouseProcessor.OUTPUT)
    MessageChannel output();
}
