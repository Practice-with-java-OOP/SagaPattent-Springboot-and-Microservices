package com.syphan.practice.user.controller;

import com.syphan.practice.user.dto.AdminCreateUserDto;
import com.syphan.practice.user.dto.UserCreateDto;
import com.syphan.practice.user.model.User;
import com.syphan.practice.user.service.UserService;
import com.syphan.pratice.common.base.OpenApiWithDataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Api(tags = "User Join Management V1")
@RestController
@RequestMapping("/api/v1/users")
public class UserJoinController {

    @Autowired
    private UserService userService;

    @PostMapping("mail-captcha")
    public ResponseEntity<OpenApiWithDataResponse<String>> mailCaptcha(@NotBlank @Email @RequestParam String email) {
        return ResponseEntity.ok(new OpenApiWithDataResponse<>(userService.sendUserSignUpMailCaptcha(email)));
    }

    @ApiOperation("User SignUp")
    @PostMapping
    public ResponseEntity<OpenApiWithDataResponse<User>> signUp(@Valid @RequestBody UserCreateDto reqPram,
                                                                BindingResult bindingResult) {
        return ResponseEntity.ok(new OpenApiWithDataResponse<User>(userService.signUp(reqPram)));
    }

    @ApiOperation("Admin create user")
    @PostMapping("admin-create")
    public ResponseEntity<OpenApiWithDataResponse<User>> adminCreateUser(@Valid @RequestBody AdminCreateUserDto reqPram,
                                                                         BindingResult bindingResult) {
        return ResponseEntity.ok(new OpenApiWithDataResponse<User>(userService.adminCreateUser(reqPram)));
    }

    @GetMapping("by-username")
    public ResponseEntity<OpenApiWithDataResponse> getByUsername(@RequestParam("username") String username) {
        return ResponseEntity.ok(new OpenApiWithDataResponse<User>(userService.findByUsername(username)));
    }
}
