package com.syphan.practice.house.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syphan.practice.house.client.UserClient;
import com.syphan.practice.house.dto.BoardingHouseCreateDto;
import com.syphan.practice.house.dto.HouseCreateLoggerDto;
import com.syphan.practice.house.dto.UserDto;
import com.syphan.practice.house.model.BoardingHouse;
import com.syphan.practice.house.model.SagaLogger;
import com.syphan.practice.house.processor.HouseProcessor;
import com.syphan.practice.house.repository.BoardingHouseRepository;
import com.syphan.practice.house.repository.SagaLoggerRepository;
import com.syphan.practice.house.service.BoardingHouseService;
import com.syphan.pratice.common.base.BaseServiceImpl;
import com.syphan.pratice.common.base.OpenApiWithDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class BoardingHouseServiceImpl extends BaseServiceImpl<BoardingHouse, Integer> implements BoardingHouseService {

    private BoardingHouseRepository repository;

    @Autowired
    public BoardingHouseServiceImpl(BoardingHouseRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Autowired
    private UserClient userClient;

    @Autowired
    private HouseProcessor houseProcessor;

    @Autowired
    private SagaLoggerRepository sagaLoggerRepository;

    @Transactional
    @Override
    public BoardingHouse create(BoardingHouseCreateDto data) {
        OpenApiWithDataResponse<UserDto> userData = userClient.getByUsername(data.getUsername());
        UserDto user = userData.getData();
        if (user == null || user.getId() == null) throw new RuntimeException("sai ten");
        BoardingHouse house = new BoardingHouse();
        house.setUserId(user.getId());
        house.setUsername(user.getFullName() != null ? user.getFullName() : user.getUsername());
        house.setUserPhone(user.getPhoneNum());
        house.setHouseName(data.getHouseName());
        house.setAddress(data.getAddress());
        house.setRoomNum(data.getRoomNum());
        BoardingHouse boardingHouse = repository.save(house);

        try {
            ObjectMapper mapper = new ObjectMapper();
//            SagaLogger sagaLogger = sagaLoggerRepository.save(SagaLogger.builder()
//                    .transactionId(UUID.randomUUID().toString())
//                    .className(BoardingHouse.class.getName())
//                    .dataJson(mapper.writeValueAsString(boardingHouse))
//                    .operation("c")
//                    .build());
            SagaLogger sagaLogger = SagaLogger.builder()
                    .transactionId(UUID.randomUUID().toString()).className(BoardingHouse.class.getName())
                    .dataJson(mapper.writeValueAsString(boardingHouse)).operation("c").build();
            houseProcessor.output().send(MessageBuilder.withPayload(HouseCreateLoggerDto.builder()
                    .id(user.getId()).transactionId(sagaLogger.getTransactionId()).username(user.getUsername())
                    .className(BoardingHouse.class.getSimpleName()).build())
                    .setHeader("type", "VALIDATE_ROLE_USER")
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        return repository.save(house);
        return new BoardingHouse();
    }

    @Transactional
    @Override
    public void rollbackCreateHouse(HouseCreateLoggerDto dto) {
        List<SagaLogger> sagaLogger = sagaLoggerRepository.findAllByTransactionIdAndClassName(dto.getTransactionId(), dto.getClassName());
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("test cai nhe!!!!!!");
    }
}
