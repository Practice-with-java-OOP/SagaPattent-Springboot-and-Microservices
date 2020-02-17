package com.syphan.practice.kitchen.service;

import com.syphan.practice.kitchen.model.Hamburger;
import com.syphan.practice.kitchen.repository.HamburgerRepository;
import com.syphan.pratice.common.dto.HamburgerDTO;
import com.syphan.pratice.common.dto.OrderDTO;
import com.syphan.pratice.common.dto.type.OrderStatusType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class KitchenService {

    @Autowired
    private HamburgerRepository hamburgerRepository;

    public synchronized boolean process(OrderDTO orderDTO) {
        List<HamburgerDTO> hamburgerDTOList = orderDTO.getHamburgers();
        List<Hamburger> hamburgers = new ArrayList<>();

        for (HamburgerDTO dto : hamburgerDTOList) {
            Hamburger hamburger = hamburgerRepository.findByHamburgerTypeIs(dto.getHamburgerType());
            if (hamburger != null && hamburger.getQuantity() >= dto.getQuantity()) {
                hamburger.setQuantity(hamburger.getQuantity() - dto.getQuantity());
                hamburgers.add(hamburger);
            } else {
                orderDTO.setOrderStatus(OrderStatusType.ABORTED.getValue());
                orderDTO.setStatusDescription(dto.getHamburgerType() + " finished, only ... in the fridge");
                log.info("Order aborted");
                return false;
            }
        }
        orderDTO.setOrderStatus(OrderStatusType.COOKING.getValue());
        orderDTO.setStatusDescription("Order in cooking");
        hamburgerRepository.saveAll(hamburgers);
        return true;
    }
}
