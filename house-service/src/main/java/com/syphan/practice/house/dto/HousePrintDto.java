package com.syphan.practice.house.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HousePrintDto {
    private Integer userId;
    private String username;
    private String userPhone;
    private String houseName;
    private String address;
    private int roomNum;
}
