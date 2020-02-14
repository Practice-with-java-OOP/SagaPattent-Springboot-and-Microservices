package com.syphan.practice.user.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface UserProcessor {
    String INPUT = "registration-service";
    String OUTPUT = "house-service";

    @Input(UserProcessor.INPUT)
    SubscribableChannel input();

    @Output(UserProcessor.OUTPUT)
    MessageChannel output();
}
