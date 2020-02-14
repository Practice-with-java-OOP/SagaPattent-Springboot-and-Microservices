package com.syphan.practice.user.service;

import com.syphan.practice.user.dto.AdminCreateUserDto;
import com.syphan.practice.user.dto.HouseCreateLoggerDto;
import com.syphan.practice.user.dto.UserCreateDto;
import com.syphan.practice.user.model.User;
import com.syphan.pratice.common.base.BaseService;

public interface UserService extends BaseService<User, Integer> {

    String sendUserSignUpMailCaptcha(String email);

    User signUp(UserCreateDto data);

    User adminCreateUser(AdminCreateUserDto data);

    User findByUsername(String username);

    void checkUserExisted(HouseCreateLoggerDto dto);
}
