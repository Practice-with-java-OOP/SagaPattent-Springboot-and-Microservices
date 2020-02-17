package com.syphan.pratice.common.dto.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by <a href="mailto:pasquale.paola@gmail.com">Pasquale Paola</a> on 10/09/19.
 */
@AllArgsConstructor
public enum OrderStatusType {
    WAITING("WAITING"),
    COOKING("COOKING"),
    PACKAGING("PACKAGING"),
    DELIVERED("DELIVERED"),
    ABORTED("ABORTED");

    @Getter
    private String value;
}
