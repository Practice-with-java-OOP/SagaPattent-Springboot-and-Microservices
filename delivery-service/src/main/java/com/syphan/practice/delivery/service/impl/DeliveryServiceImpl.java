package com.syphan.practice.delivery.service.impl;

import com.syphan.practice.delivery.model.Delivery;
import com.syphan.practice.delivery.service.DeliveryService;
import com.syphan.pratice.common.base.BaseServiceImpl;
import com.syphan.pratice.common.dao.JpaQueryRepository;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl extends BaseServiceImpl<Delivery, Long> implements DeliveryService {
    public DeliveryServiceImpl(JpaQueryRepository<Delivery, Long> repository) {
        super(repository);
    }
}
