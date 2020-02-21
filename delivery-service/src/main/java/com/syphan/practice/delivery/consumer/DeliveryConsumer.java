package com.syphan.practice.delivery.consumer;


import com.syphan.practice.delivery.messaging.DeliveryPublisher;
import com.syphan.practice.delivery.model.Delivery;
import com.syphan.practice.delivery.processor.OrderProcessor;
import com.syphan.practice.delivery.repository.DeliveryRepository;
import com.syphan.pratice.common.dto.OrderDTO;
import com.syphan.pratice.common.dto.type.OrderStatusType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding({OrderProcessor.class})
@Slf4j
public class DeliveryConsumer {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private DeliveryPublisher deliveryPublisher;

    @StreamListener(target = OrderProcessor.INPUT,
            condition = "headers['type'] == 'deliveryservice'")
    public void consumeMessage(OrderDTO orderDTO) {
        log.info("------########## receive message from kitchen service with data " + orderDTO.toString());
        Delivery delivery = new Delivery();
        delivery.setNumber(orderDTO.getNumber());
        delivery.setStreet(orderDTO.getStreet());
        delivery.setOrderId(orderDTO.getId());
        deliveryRepository.save(delivery);
        log.info("Processing delivery id " + delivery.getId() + " for order id " + orderDTO.getId());

        orderDTO.setOrderStatus(OrderStatusType.DELIVERED.getValue());
        orderDTO.setStatusDescription("Delivered");
        deliveryPublisher.sendToOrderCallback(orderDTO);
        log.info("Delivered order id " + orderDTO.getId());
    }
}
