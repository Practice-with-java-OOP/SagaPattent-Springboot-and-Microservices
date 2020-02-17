package com.syphan.practice.order.consumer;

import com.syphan.practice.order.processor.DeliveryProcessor;
import com.syphan.practice.order.processor.KitchenProcessor;
import com.syphan.practice.order.processor.OrderProcessor;
import com.syphan.practice.order.service.OrderService;
import com.syphan.pratice.common.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@EnableBinding({OrderProcessor.class, KitchenProcessor.class, DeliveryProcessor.class})
@Slf4j
public class OrderConsumer {

    private final static String TOPIC_ORDER_CALLBACK = "orderservicecallback";

    @Autowired
    private OrderService orderService;

    @StreamListener(target = OrderProcessor.INPUT,
            condition = "headers['type'] == 'orderservicecallback'")
    public void callback(@Payload OrderDTO orderDTO) {
        orderService.callback(orderDTO);
    }
}
