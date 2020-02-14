package com.syphan.practice.house.service;

import com.syphan.practice.house.dto.BoardingHouseCreateDto;
import com.syphan.practice.house.dto.HouseCreateLoggerDto;
import com.syphan.practice.house.model.BoardingHouse;
import com.syphan.pratice.common.base.BaseService;

public interface BoardingHouseService extends BaseService<BoardingHouse, Integer> {

    BoardingHouse create(BoardingHouseCreateDto data);

    void rollbackCreateHouse(HouseCreateLoggerDto dto);
}
