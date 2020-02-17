package com.syphan.pratice.common.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HamburgerDTO {

    private String hamburgerType;

    private int quantity;

    private BigDecimal price;
}
