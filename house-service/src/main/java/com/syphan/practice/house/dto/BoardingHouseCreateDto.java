package com.syphan.practice.house.dto;

import com.syphan.pratice.common.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardingHouseCreateDto extends BaseDto {

    private String username;

    private String houseName;

    private String address;

    private int roomNum;
}
