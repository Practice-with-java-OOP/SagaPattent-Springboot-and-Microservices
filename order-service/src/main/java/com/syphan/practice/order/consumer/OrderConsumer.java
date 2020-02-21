package com.syphan.practice.order.consumer;

import com.syphan.practice.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderConsumer {

    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = "coordinator.order.callback.publisher")
    public void orderCallback(String orderDTO) {
        orderService.orderCallback(orderDTO);
    }
}
