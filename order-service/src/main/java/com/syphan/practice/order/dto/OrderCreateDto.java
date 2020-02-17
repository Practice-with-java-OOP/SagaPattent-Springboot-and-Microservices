package com.syphan.practice.order.dto;

import com.syphan.pratice.common.dto.HamburgerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDto {

    private List<HamburgerDTO> hamburgers;

    private String number;

    private String street;
}
