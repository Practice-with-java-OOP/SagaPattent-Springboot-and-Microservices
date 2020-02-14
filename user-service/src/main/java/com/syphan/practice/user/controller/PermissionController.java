package com.syphan.practice.user.controller;

import com.syphan.practice.user.dto.PermissionCreateDto;
import com.syphan.practice.user.model.Permission;
import com.syphan.practice.user.service.PermissionService;
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

@Api(tags = "Permission Management V1")
@RestController
@RequestMapping("/api/v1/permissions")
public class PermissionController {

    @Autowired
    private PermissionService service;

    @ApiOperation("Create New Permission")
    @PostMapping
    public ResponseEntity<OpenApiWithDataResponse<Permission>> create(@Valid @RequestBody PermissionCreateDto reqPram,
                                                                      BindingResult bindingResult) {
        return ResponseEntity.ok(new OpenApiWithDataResponse<Permission>(service.create(reqPram)));
    }

}
