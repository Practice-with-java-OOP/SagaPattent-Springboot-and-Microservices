package com.syphan.practice.user.controller;

import com.syphan.practice.user.dto.RoleCreateDto;
import com.syphan.practice.user.model.Role;
import com.syphan.practice.user.service.RoleService;
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

@Api(tags = "Role Management V1")
@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("Create New Role")
    @PostMapping
    public ResponseEntity<OpenApiWithDataResponse<Role>> addRole(@Valid @RequestBody RoleCreateDto reqParam,
                                                                 BindingResult bindingResult) {
        return ResponseEntity.ok(new OpenApiWithDataResponse<Role>(roleService.create(reqParam)));
    }
}
