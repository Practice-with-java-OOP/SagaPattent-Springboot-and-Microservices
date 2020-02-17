package com.syphan.practice.kitchen.repository;

import com.syphan.practice.kitchen.model.Hamburger;
import com.syphan.pratice.common.dao.JpaQueryRepository;

public interface HamburgerRepository extends JpaQueryRepository<Hamburger, Long> {

    Hamburger findByHamburgerTypeIs(String hamburgerType);
}
