package com.syphan.practice.house.repository;

import com.syphan.practice.house.model.SagaLogger;
import com.syphan.pratice.common.dao.JpaQueryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SagaLoggerRepository extends JpaQueryRepository<SagaLogger, Integer> {
    List<SagaLogger> findAllByTransactionIdAndClassName(String transactionId, String className);
}
