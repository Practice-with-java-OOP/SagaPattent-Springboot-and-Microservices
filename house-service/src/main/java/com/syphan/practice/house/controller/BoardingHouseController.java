package com.syphan.practice.house.controller;

import com.syphan.practice.house.dto.BoardingHouseCreateDto;
import com.syphan.practice.house.model.BoardingHouse;
import com.syphan.practice.house.service.BoardingHouseService;
import com.syphan.pratice.common.base.OpenApiWithDataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "House Management V1")
@RestController
@RequestMapping("/api/v1/boarding-houses")
public class BoardingHouseController {

    @Autowired
    private BoardingHouseService houseService;

    @ApiOperation("Create New House")
    @PostMapping
    public ResponseEntity<OpenApiWithDataResponse<BoardingHouse>> create(@Valid @RequestBody BoardingHouseCreateDto reqPram,
                                                                         BindingResult bindingResult) {
        return ResponseEntity.ok(new OpenApiWithDataResponse<>(houseService.create(reqPram)));
    }

}
