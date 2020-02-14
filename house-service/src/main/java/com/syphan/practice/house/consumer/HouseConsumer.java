package com.syphan.practice.house.consumer;

import com.syphan.practice.house.dto.HouseCreateLoggerDto;
import com.syphan.practice.house.processor.HouseProcessor;
import com.syphan.practice.house.service.BoardingHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(HouseProcessor.class)
public class HouseConsumer {

    @Autowired
    private BoardingHouseService houseService;

    @StreamListener(target = HouseProcessor.INPUT,
            condition = "headers['type'] == 'ROLLBACK_CREATED_HOUSE'")
    public void rollbackCreateHouse(@Payload HouseCreateLoggerDto dto) {
        houseService.rollbackCreateHouse(dto);
    }
}
