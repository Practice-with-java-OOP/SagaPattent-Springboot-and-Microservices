package com.syphan.pratice.common.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

    private Long id;

    private String statusDescription;

    private String cookingType;

    private String orderStatus;

    private List<HamburgerDTO> hamburgers;

    private String number;

    private String street;

    private BigDecimal totalPrice;
}
