package com.syphan.practice.order.controller;

import com.syphan.practice.order.dto.OrderCreateDto;
import com.syphan.practice.order.service.OrderService;
import com.syphan.pratice.common.base.OpenApiWithDataResponse;
import com.syphan.pratice.common.dto.OrderDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@Api("Order Service Management")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("Create Order")
    @PostMapping
    public ResponseEntity<OpenApiWithDataResponse<OrderDTO>> create(@RequestBody OrderCreateDto dto) {
        return ResponseEntity.ok(new OpenApiWithDataResponse<>(orderService.createOrder(dto)));
    }

}
