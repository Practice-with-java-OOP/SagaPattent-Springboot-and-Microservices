package com.syphan.practice.delivery.messaging;

import com.syphan.practice.delivery.processor.DeliveryProcessor;
import com.syphan.pratice.common.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * Created by <a href="mailto:pasquale.paola@gmail.com">Pasquale Paola</a> on 18/09/19.
 */
@Service
public class DeliveryPublisher {

    private final static String COORDINATOR_ORDER_CALLBACK = "orders.callback.publisher";

    @Autowired
    private DeliveryProcessor deliveryProcessor;

    public void orderCallbackEventPublisher(OrderDTO orderDTO) {
        deliveryProcessor.output().send(MessageBuilder.withPayload(orderDTO)
                .setHeader("type", COORDINATOR_ORDER_CALLBACK)
                .build());
    }

}
