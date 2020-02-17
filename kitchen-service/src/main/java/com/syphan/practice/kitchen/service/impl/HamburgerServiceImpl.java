package com.syphan.practice.kitchen.service.impl;

import com.syphan.practice.kitchen.model.Hamburger;
import com.syphan.practice.kitchen.repository.HamburgerRepository;
import com.syphan.practice.kitchen.service.HamburgerService;
import com.syphan.pratice.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class HamburgerServiceImpl extends BaseServiceImpl<Hamburger, Long> implements HamburgerService {

    public HamburgerServiceImpl(HamburgerRepository repository) {
        super(repository);
    }
}
