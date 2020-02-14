package com.syphan.practice.house.service.impl;

import com.syphan.practice.house.model.SagaLogger;
import com.syphan.practice.house.service.SagaLoggerService;
import com.syphan.pratice.common.base.BaseServiceImpl;
import com.syphan.pratice.common.dao.JpaQueryRepository;
import org.springframework.stereotype.Service;

@Service
public class SagaLoggerServiceImpl extends BaseServiceImpl<SagaLogger, Integer> implements SagaLoggerService {

    public SagaLoggerServiceImpl(JpaQueryRepository<SagaLogger, Integer> repository) {
        super(repository);
    }
}
