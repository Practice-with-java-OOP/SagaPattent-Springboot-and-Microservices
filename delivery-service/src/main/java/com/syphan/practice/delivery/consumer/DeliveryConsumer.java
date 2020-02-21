package com.syphan.practice.delivery.consumer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.syphan.practice.delivery.messaging.DeliveryPublisher;
import com.syphan.practice.delivery.model.Delivery;
import com.syphan.practice.delivery.repository.DeliveryRepository;
import com.syphan.pratice.common.dto.OrderDTO;
import com.syphan.pratice.common.dto.type.OrderStatusType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class DeliveryConsumer {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private DeliveryPublisher deliveryPublisher;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "coordinator.kitchen.validated.publisher")
    public void consumeMessage(String record) {
        try {
            OrderDTO orderDTO = objectMapper.readValue(record, OrderDTO.class);
            log.info("receive message " + orderDTO.toString());
            Delivery delivery = new Delivery();
            delivery.setNumber(orderDTO.getNumber());
            delivery.setStreet(orderDTO.getStreet());
            delivery.setOrderId(orderDTO.getId());
            deliveryRepository.save(delivery);
            log.info("Processing delivery id " + delivery.getId() + " for order id " + orderDTO.getId());

            orderDTO.setOrderStatus(OrderStatusType.DELIVERED.getValue());
            orderDTO.setStatusDescription("Delivered");
            deliveryPublisher.orderCallbackEventPublisher(orderDTO);
            log.info("Delivered order id " + orderDTO.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
