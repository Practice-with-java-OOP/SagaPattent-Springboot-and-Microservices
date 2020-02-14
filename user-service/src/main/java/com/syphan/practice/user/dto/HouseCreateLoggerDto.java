package com.syphan.practice.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HouseCreateLoggerDto {
    private Integer id;
    private String username;
    private String transactionId;
    private String className;
}
