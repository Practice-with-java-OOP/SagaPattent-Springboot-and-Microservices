package com.syphan.pratice.common.dto.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by <a href="mailto:pasquale.paola@gmail.com">Pasquale Paola</a> on 09/09/19.
 */
@AllArgsConstructor
public enum HamburgerType {
    ANGUS("ANGUS"), SCOTTONA("SCOTTONA"), CHIANINA("CHIANINA"), KOBE("KOBE"), VITELLONE("VITELLONE");

    @Getter
    private String description;

//    HamburgerType(String description){
//        this.description=description;
//    }

//    public String getDescription() {
//        return description;
//    }
}
