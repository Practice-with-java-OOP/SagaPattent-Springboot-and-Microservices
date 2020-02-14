package com.syphan.practice.user.consumer;

import com.syphan.practice.user.dto.HouseCreateLoggerDto;
import com.syphan.practice.user.processor.UserProcessor;
import com.syphan.practice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(UserProcessor.class)
public class UserConsumer {

    @Autowired
    private UserService userService;

    @StreamListener(target = UserProcessor.INPUT,
            condition = "headers['type'] == 'VALIDATE_ROLE_USER'")
    public void checkUserExisted(@Payload HouseCreateLoggerDto dto) {
        userService.checkUserExisted(dto);
    }
}
