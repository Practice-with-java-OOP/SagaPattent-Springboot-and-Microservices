package com.syphan.practice.house.repository;

import com.syphan.practice.house.model.BoardingHouse;
import com.syphan.pratice.common.dao.JpaQueryRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardingHouseRepository extends JpaQueryRepository<BoardingHouse, Integer> {
}
