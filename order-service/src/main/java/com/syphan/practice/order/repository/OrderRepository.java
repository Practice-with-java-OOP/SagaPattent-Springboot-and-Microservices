package com.syphan.practice.order.repository;

import com.syphan.practice.order.model.Order;
import com.syphan.pratice.common.dao.JpaQueryRepository;

public interface OrderRepository extends JpaQueryRepository<Order, Long> {
}
